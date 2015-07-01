package dirceubelem.GamesStats;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.List;

import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.Dao.BD;
import dirceubelem.GamesStats.adapter.FeedSalvosAdapter;
import dirceubelem.GamesStats.to.TOUsuario;

/**
 * Created by joaop_000 on 28/06/2015.
 */
public class PerfisSalvosActivity  extends ActionBarActivity {
    private ListView listFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfis_salvos);
        listFeed = (ListView) findViewById(R.id.listSalvos);
        BD bd = new BD(this);
        List<TOUsuario> list = bd.buscar();
        FeedSalvosAdapter adapter = new FeedSalvosAdapter(PerfisSalvosActivity.this, list);
        listFeed.setAdapter(adapter);

    }



}
