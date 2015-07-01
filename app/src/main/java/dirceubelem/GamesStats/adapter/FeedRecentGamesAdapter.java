package dirceubelem.GamesStats.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.task.DownloadImageTask;
import dirceubelem.GamesStats.to.TOfeedRecents;
import dirceubelem.GamesStats.to.TOrecentes;

/**
 * Created by joaop_000 on 23/06/2015.
 */
public class FeedRecentGamesAdapter extends BaseAdapter{
    private TOfeedRecents recentes;
    private Context context;


    public FeedRecentGamesAdapter(TOfeedRecents recentes, Context context){
        this.recentes = recentes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recentes.getGames().size();
    }

    @Override
    public Object getItem(int position) {
        return recentes.getGames().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TOrecentes t = recentes.getGames().get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_list, null);
        TextView nomeGame = (TextView) v.findViewById(R.id.nameGame);
        nomeGame.setText(t.getName());
        final ImageView i = (ImageView)v.findViewById(R.id.imgGame );
        DownloadImageTask d = new DownloadImageTask(){
            @Override
            protected void onPostExecute(Bitmap bitmap){
                i.setImageBitmap(bitmap);
            }
        };
        d.execute("http://media.steampowered.com/steamcommunity/public/images/apps/"+t.getAppid()+"/"+t.getImg_logo_url()+".jpg");
        TextView horasJogadas = (TextView) v.findViewById(R.id.horasJogadas);
        float horas = Float.valueOf(t.getPlaytime_forever())/60;
        String hotasTotais = String.format("%.02f",horas);
        horasJogadas.setText("Horas de jogo: "+hotasTotais);
        return v;
    }
}
