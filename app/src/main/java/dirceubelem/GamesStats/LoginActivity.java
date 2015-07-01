package dirceubelem.GamesStats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dirceubelem.GamesStats.fw.SharedPreferencesHelper;
import dirceubelem.GamesStats.to.ToLogin;
import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.task.LoginTask;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        Button btnCadastro = (Button) findViewById(R.id.btnCadastrar);
        btnCadastro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                autentica();
                break;
            case R.id.btnCadastrar:
                cadastro();
                break;
        }

    }
    public void cadastro(){
        Intent i = new Intent(LoginActivity.this, JoinActivity.class);
        startActivity(i);
        finish();
    }
    public void autentica() {
        TextView txtUsuario = (TextView) findViewById(R.id.edtUsuario);
        TextView txtSenha  = (TextView) findViewById(R.id.edtSenha);

        final String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        pd = new ProgressDialog(this);
        pd.setMessage("Autenticando...");
        pd.show();

        LoginTask t = new LoginTask() {
            @Override
            protected void onPostExecute(ToLogin toUsuario) {

                pd.hide();

                if (toUsuario.getNome() != null) {

                    SharedPreferencesHelper.write(LoginActivity.this, "user_preferences",
                            "user", usuario.toString());

                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                     startActivity(i);
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "Usuário e/ou senha inválidos",
                            Toast.LENGTH_LONG).show();

                }

            }
        };

        t.execute(usuario, senha);


    }


}
