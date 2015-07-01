package dirceubelem.GamesStats.task;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import dirceubelem.GamesStats.fw.Constant;
import dirceubelem.GamesStats.fw.Util;
import dirceubelem.GamesStats.to.TOPerfil;
import dirceubelem.GamesStats.to.TOUsuario;

/**
 * Created by joaop_000 on 23/06/2015.
 */
public class PerfilTask extends AsyncTask<String, Void, TOPerfil> {
    @Override
    protected TOPerfil doInBackground(String... params) {
        TOPerfil usuario = null;

        try{
            URL url = new URL(Constant.API.OBTER_DADOSUSUARIO+params[0]/*idSteam*/);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            //define a propriedade da url solicitada
            http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            InputStream i = new BufferedInputStream(http.getInputStream());
            String response = Util.readString(i);
            JSONObject json = new JSONObject(response);
            String result = (json.getString("response"));
            usuario = TOUsuario.createByJson(result, TOPerfil.class);
        }
        catch (Exception e)
        {
            usuario=null;
        }
        return usuario;
    }
}
