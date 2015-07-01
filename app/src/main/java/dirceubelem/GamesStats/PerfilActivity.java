package dirceubelem.GamesStats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import dirceubelem.GamesStats.adapter.FeedRecentGamesAdapter;
import dirceubelem.GamesStats.task.DownloadImageTask;
import dirceubelem.GamesStats.task.PerfilTask;
import dirceubelem.GamesStats.task.RecenteGameTask;
import dirceubelem.GamesStats.to.TOPerfil;
import dirceubelem.GamesStats.to.TOUsuario;
import dirceubelem.GamesStats.to.mensagem;
import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.to.TOfeedRecents;

/**
 * Created by joaop_000 on 23/06/2015.
 */
public class PerfilActivity extends ActionBarActivity implements  View.OnClickListener{

private ProgressDialog pd;
    private ListView listFeed;
    TextView txtNick;
    TextView txtIdsteam;
    TextView txtAtividade;
    TextView txtNome;
    TextView txtLocal;
    TextView txtStatus;
    String steamid;
    mensagem msg;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Intent intent = getIntent();
        final String steamid = intent.getStringExtra("steamid");
        this.steamid = steamid;
        BuscarId(steamid);
        txtNick = (TextView) findViewById(R.id.txtNick);
        txtAtividade = (TextView) findViewById(R.id.txtAtividade);
        txtIdsteam =(TextView) findViewById(R.id.txtSteamid);
        listFeed = (ListView) findViewById(R.id.listRecentGames);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtLocal = (TextView) findViewById(R.id.txtLocal);
        txtStatus = (TextView) findViewById(R.id.txtStatus);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnJogos:
                abrePerfil();
                break;
        }

    }
    public void BuscarId(final String idSteam) {
        pd = new ProgressDialog(this);
        pd.setMessage("Carregando atividades recentes...");
        pd.show();
        PerfilTask perf = new PerfilTask() {
            @Override
            protected void onPostExecute(TOPerfil touser) {
                if (touser != null) {
                    carregaPerfil(touser);
                    carregaListaRecentes(touser, steamid);

                }
            }
        };
        perf.execute(idSteam);
    }
    public void carregaPerfil(TOPerfil obPerfil)
    {
        int position = obPerfil.getPlayers().size()-1;
        TOUsuario t = obPerfil.getPlayers().get(position);
        txtNick.setText(t.getPersonaname());
        txtIdsteam.setText("SteamID:" + t.getSteamid());
        //Carrega imagem de perfil
        final ImageView i = (ImageView)findViewById(R.id.imgAvatar);

        DownloadImageTask d = new DownloadImageTask(){
            @Override
            protected void onPostExecute(Bitmap bitmap){

                i.setImageBitmap(bitmap);
            }

        };
        d.execute(t.getAvatarfull());
        txtNome.setText("Nome: "+t.getRealname());
        txtLocal.setText("País: "+t.getLoccountrycode());
        txtStatus.setText("Status: "+status(t));
    }
    public void carregaListaRecentes(TOPerfil obPerfil, String idSteam){
        RecenteGameTask r = new RecenteGameTask()
        {
            @Override
            protected void onPostExecute(TOfeedRecents toFeed) {

                if (toFeed.getTotal_count() != 0){

                     FeedRecentGamesAdapter adapter= new FeedRecentGamesAdapter(toFeed,PerfilActivity.this);
                     listFeed.setAdapter(adapter);
                     txtAtividade.setText("Atividades Recentes: "+toFeed.getTotal_count());
                     pd.hide();



                }
                else {
                    pd.hide();
                    txtAtividade.setText("As informacoes desse Perfil sao privadas");
                }
            }
        };
        r.execute(idSteam);
    }
    public void abrePerfil() {
        Intent i = new Intent(this, jogosActivity.class);
        i.putExtra("steamid", steamid);
        startActivity(i);
    }
    public String status(TOUsuario touser)
    {
            if (touser.getPersonastate() == "0") {
                return "Offline";
            }
            else if (touser.getPersonastate()=="1")
            {
                return "OnLine";
            }
            else if (touser.getPersonastate()=="2")
            {
                return "Ocupado";
            }
            else if (touser.getPersonastate()=="3")
            {
                return "Ausente";
            }
            else if (touser.getPersonastate()=="4")
            {
                return "Querendo Jogar";
            }
            else if (touser.getPersonastate()=="5")
            {
                return "Querendo Trocar";
            }
            else if (touser.getPersonastate()=="6")
            {
                return "Escolhendo Jogo";
            }
                return "Perfil Privado";
    }

}
