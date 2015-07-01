package dirceubelem.GamesStats.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dirceubelem.GamesStats.PerfisSalvosActivity;
import dirceubelem.exemplo5.R;
import dirceubelem.GamesStats.Dao.BD;
import dirceubelem.GamesStats.jogosActivity;
import dirceubelem.GamesStats.task.DownloadImageTask;
import dirceubelem.GamesStats.to.TOUsuario;
import dirceubelem.GamesStats.to.mensagem;

/**
 * Created by joaop_000 on 28/06/2015.
 */

public class FeedSalvosAdapter  extends BaseAdapter {
    private Context context;
    private List<TOUsuario> list;
    mensagem msg = new mensagem();

    public FeedSalvosAdapter (Context context, List<TOUsuario> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return Integer.parseInt(list.get(arg0).getSteamid());
    }

    @Override
    public View getView(final int position, View arg1, ViewGroup arg2) {
        final int auxPosition = position;
        TOUsuario t = list.get(position);
        final int id = Integer.parseInt(t.getLoccityid());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_list2, null);
        TextView nomeUser = (TextView) v.findViewById(R.id.txtProfilename);
        nomeUser.setText(t.getPersonaname());
        final ImageView i = (ImageView)v.findViewById(R.id.imgUser);
        DownloadImageTask d = new DownloadImageTask(){
            @Override
            protected void onPostExecute(Bitmap bitmap){
                i.setImageBitmap(bitmap);
            }
        };
        d.execute(t.getAvatarmedium());
        TextView nomeIdSteam = (TextView) v.findViewById(R.id.txtIdsteam);
        nomeIdSteam.setText(t.getSteamid());


        Button btnPerfil = (Button) v.findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, jogosActivity.class);
                intent.putExtra("steamid", list.get(auxPosition).getSteamid());
                context.startActivity(intent);
            }
        });

        Button deletarBt = (Button) v.findViewById(R.id.btnExcluir);
        deletarBt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                BD bd = new BD(context);
                bd.deletar(id);
                Toast.makeText(context,"Excluido com Sucesso!",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(context,PerfisSalvosActivity.class);
                refresh.setFlags(refresh.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(refresh);

                //context.startActivity(refresh, Bundle.).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
        });

        return v;
    }


}
