package dirceubelem.GamesStats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dirceubelem.GamesStats.task.BuscaIdTask;
import dirceubelem.GamesStats.to.TOId;
import dirceubelem.GamesStats.to.mensagem;
import dirceubelem.exemplo5.R;


public class HomeActivity extends ActionBarActivity {

    private ProgressDialog pd;
    mensagem msg = new mensagem();
    EditText txtBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        txtBusca = (EditText) findViewById(R.id.txtbusca);
        Button btnBuscar = (Button) findViewById(R.id.btnProcurar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarId();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_perfSaves:
                openSaves();
                 break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void BuscarId()
    {
        pd = new ProgressDialog(this);
        pd.setMessage("Buscando...");
        pd.show();
        BuscaIdTask r = new BuscaIdTask() {
            TOId toId;

            @Override
            protected void onPostExecute(TOId toId) {
                if (toId != null) {
                    if (toId.getSteamid() != null) {
                        pd.hide();
                        String texto = toId.getSteamid();
                        abrePerfil(texto);
                    } else {
                        pd.hide();
                        int duracao = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(getApplicationContext(), "Usuario nao encontrado", duracao);
                        toast.show();
                    }
                } else {
                    pd.hide();
                    int duracao = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), "Campo de busca nao pode estar em branco", duracao);
                    toast.show();
                }
            }
        };
        r.execute(txtBusca.getText().toString());



    }
    public void abrePerfil(String idSteam) {
        Intent i = new Intent(this, jogosActivity.class);
        i.putExtra("steamid", idSteam);
        startActivity(i);
    }
    public void openSaves(){
        Intent i = new Intent(this, PerfisSalvosActivity.class);
        startActivity(i);
    }


}




