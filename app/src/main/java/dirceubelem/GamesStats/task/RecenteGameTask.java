package dirceubelem.GamesStats.task;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import dirceubelem.GamesStats.fw.Constant;
import dirceubelem.GamesStats.fw.Util;
import dirceubelem.GamesStats.to.TOfeedRecents;

/**
 * Created by joaop_000 on 25/06/2015.
 */
public class RecenteGameTask extends AsyncTask<String, Void, TOfeedRecents> implements Serializable {


    @Override
    protected TOfeedRecents doInBackground(String... params) {
        TOfeedRecents games = null;
        Context context;
        try{
            URL url = new URL(Constant.API.OBTER_RECENTEGAMES+params[0]/*idSteam*/);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            //define a propriedade da url solicitada
            http.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            InputStream i = new BufferedInputStream(http.getInputStream());
            String response = Util.readString(i);
            JSONObject json = new JSONObject(response);
            String result = (json.getString("response"));
            games = TOfeedRecents.createByJson(result, TOfeedRecents.class);
        }
        catch (Exception e)
        {
            games=null;
        }
        return games;
    }
}