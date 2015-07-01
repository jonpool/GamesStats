package dirceubelem.GamesStats.task;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import dirceubelem.GamesStats.fw.Constant;
import dirceubelem.GamesStats.fw.Util;
import dirceubelem.GamesStats.to.TOId;


/**
 * Created by joaop_000 on 22/06/2015.
 */
public class BuscaIdTask extends AsyncTask<String, Void, TOId> {
    @Override
    protected TOId doInBackground(String... params) {
        TOId t = null;

        try{
        String nick = params[0];
        URL url = new URL(Constant.API.OBTER_IDUSUARIO+params[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            //define a propriedade da url solicitada
            http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

            InputStream i = new BufferedInputStream(http.getInputStream());
            String result = Util.readString(i);
            JSONObject json = new JSONObject(result);
            String response = (json.getString("response"));
            //String steamId = (json.getString("steamid"));
            //String success = (json.getString("success"));
            t = TOId.createByJson(response, TOId.class);
        }
        catch (Exception e)
        {
           t=null;
        }
        return t;
    }
}
