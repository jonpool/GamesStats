package dirceubelem.GamesStats.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dirceubelem.GamesStats.to.TOUsuario;

public class BD {
	private SQLiteDatabase bd;
	
	public BD(Context context){
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}
	
	
	public void inserir(TOUsuario usuario){
		ContentValues valores = new ContentValues();
		valores.put("SteamId", usuario.getSteamid());
		valores.put("nick", usuario.getPersonaname());
		valores.put("avatar", usuario.getAvatarmedium());
		
		bd.insert("usuario", null, valores);
	}
	public void deletar(int id){
		bd.delete("usuario", "_id = "+id, null);

	}
	
	
	public List<TOUsuario> buscar(){
		List<TOUsuario> list = new ArrayList<TOUsuario>();
		String[] colunas = new String[]{"_id","SteamId", "nick","avatar"};
		
		Cursor cursor = bd.query("usuario", colunas, null, null, null, null,null);
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{
				
				TOUsuario u = new TOUsuario();
                u.setLoccityid(cursor.getString(0));
				u.setSteamid(cursor.getString(1));
				u.setPersonaname(cursor.getString(2));
				u.setAvatarmedium(cursor.getString(3));
				list.add(u);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}
