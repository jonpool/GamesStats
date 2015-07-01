package dirceubelem.GamesStats.to;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by joaop_000 on 22/06/2015.
 */
public class mensagem {

    public void toast(Context context, String texto) {
                int duracao = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, texto, duracao);
        toast.show();
    }
}
