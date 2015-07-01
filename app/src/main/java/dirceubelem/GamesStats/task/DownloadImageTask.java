package dirceubelem.GamesStats.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import dirceubelem.GamesStats.fw.Util;

/**
 * Created by Administrador on 13/06/2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... params) {
        //passo 1
        //cria uma variavel do tipo bitmap
        Bitmap b = ObterImagem(params[0]);
        if (b == null) {
            try {

                InputStream in = new java.net.URL(params[0]).openStream();
                b = BitmapFactory.decodeStream(in);
                SalvarBitmap(b, params[0]);
            } catch (Exception e) {
                b = null;
            }
        }
        return b;
    }
    //salva a imagem localmente
    private void SalvarBitmap (Bitmap b,  String url)throws Exception {
        String id = Util.md5(url);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/images/";
        File dir = new File(filePath);
        dir.mkdir();
        Log.i("Imagem", "Salvando imagem: " + filePath + id + ".jpg");

        FileOutputStream out = new FileOutputStream(filePath + id + ".jpg");
        b.compress(Bitmap.CompressFormat.JPEG, 100, out);
        out.flush();
        out.close();
    }
    private Bitmap ObterImagem(String Url)
    {
        String id = Util.md5(Url);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +"/images/" + id + ".jpg";
        File f = new File(filePath);
        if (f.exists()){
            Log.i("imagem", "Obtendo Imagem: " + filePath);
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            return bitmap;
        }else{
            return null;
        }



    }
}

