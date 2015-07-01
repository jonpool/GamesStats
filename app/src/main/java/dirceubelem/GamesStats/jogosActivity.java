package dirceubelem.GamesStats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import dirceubelem.GamesStats.Dao.BD;
import dirceubelem.GamesStats.adapter.FeedTodosJogos;
import dirceubelem.GamesStats.task.DownloadImageTask;
import dirceubelem.GamesStats.task.PerfilTask;
import dirceubelem.GamesStats.to.TOPerfil;
import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.task.JogosTask;
import dirceubelem.GamesStats.to.TOUsuario;
import dirceubelem.GamesStats.to.TOfeedGames;

/**
 * Created by joaop_000 on 26/06/2015.
 */
public class jogosActivity extends ActionBarActivity implements View.OnClickListener {
    private TOUsuario usuario = new TOUsuario();
    private TOUsuario t;
    private ProgressDialog pd;
    private ListView listFeed;
    TextView txtNick;
    TextView txtIdsteam;
    TextView txtAtividade;
    ImageView i;
    String steamid;
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jogos);
        Intent intent = getIntent();
        steamid = intent.getStringExtra("steamid");

        txtNick = (TextView) findViewById(R.id.txtNick);
        txtAtividade = (TextView) findViewById(R.id.txtAtividade);
        txtIdsteam =(TextView) findViewById(R.id.txtSteamid);
        listFeed = (ListView) findViewById(R.id.listRecentGames);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        Button btnJogos = (Button) findViewById(R.id.btnJogos);
        btnJogos.setOnClickListener(this);
        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
        BuscarId(steamid);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnJogos:
                abrePerfil();
                break;
            case R.id.btnSalvar:
                SalvarPerfil(v);
                break;
        }
    }
    public void BuscarId(final String idSteam) {
        pd = new ProgressDialog(this);
        pd.setMessage("Carregando lista de jogos...");
        pd.show();
        PerfilTask perf = new PerfilTask() {
            @Override
            protected void onPostExecute(TOPerfil touser) {
                if (touser != null) {
                    carregaPerfil(touser);
                    carregaListaJogos(touser, idSteam);

                }
            }
        };
        perf.execute(idSteam);
    }

    public void carregaPerfil(TOPerfil obPerfil)
    {

        int position = obPerfil.getPlayers().size()-1;
        t = obPerfil.getPlayers().get(position);
        txtNick.setText(t.getPersonaname());
        txtIdsteam.setText("SteamID:" + t.getSteamid());
        //Carrega imagem de perfil
        i = (ImageView)findViewById(R.id.imgAvatar);

        DownloadImageTask d = new DownloadImageTask(){
            @Override
            protected void onPostExecute(Bitmap bitmap){

                i.setImageBitmap(bitmap);
            }

        };
        d.execute(t.getAvatarfull());
        txtStatus.setText("Status: " + status(t));



    }
    public void carregaListaJogos(TOPerfil touser,String idSteam){

        JogosTask r = new JogosTask() {
            @Override
            protected void onPostExecute(TOfeedGames toFeed) {

                if (toFeed.getGame_count() != 0){

                    FeedTodosJogos adapter= new FeedTodosJogos(toFeed,jogosActivity.this);
                    listFeed.setAdapter(adapter);
                    txtAtividade.setText("Total de Games: "+toFeed.getGame_count());
                    pd.hide();

                }
                else {
                    pd.hide();
                    txtAtividade.setText("As informacoes desse Perfil sao privadas");
                }



            }
        };
        r.execute(idSteam);
        //pd.hide();*/

    }
    public void abrePerfil() {
        Intent i = new Intent(this, PerfilActivity.class);
        i.putExtra("steamid",steamid);
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

    public void SalvarPerfil(View view){

        usuario.setSteamid(t.getSteamid());
        usuario.setPersonaname(txtNick.getText().toString());
        usuario.setAvatarmedium(t.getAvatarmedium());

        BD bd = new BD(this);
        bd.inserir(usuario);

        Toast.makeText(this, "Perfil salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

}
