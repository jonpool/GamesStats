package dirceubelem.GamesStats.fw;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

/**
 * Created by dirceu on 14/05/2015.
 */

public final class SharedPreferencesHelper {

    public static Map<String, ?> readAll(Context context, String file) {
        Map<String, ?> prefs = null;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            prefs = sharedPrefs.getAll();
        } catch (Exception e) {
            prefs = null;
            Log.e("READ_ALL", e.getLocalizedMessage(), e);
        }

        return prefs;
    }

    public static Boolean readBoolean(Context context, String file, String key,
                                      Boolean defaultValue) {
        Boolean value = null;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e("READ", e.getLocalizedMessage(), e);
        }

        return value;
    }

    public static boolean read(Context context, String file, String key,
                               boolean defaultValue) {
        boolean value = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sharedPrefs.getBoolean(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e("READ", e.getLocalizedMessage(), e);
        }

        return value;
    }

    public static String read(Context context, String file, String key,
                              String defaultValue) {
        String value = null;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e("READ", e.getLocalizedMessage(), e);
        }

        return value;
    }

    public static long read(Context context, String file, String key,
                            long defaultValue) {
        long value = Long.MIN_VALUE;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sharedPrefs.getLong(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e("READ", e.getLocalizedMessage(), e);
        }

        return value;
    }

    public static int read(Context context, String file, String key,
                           int defaultValue) {
        int value = Integer.MIN_VALUE;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            value = sharedPrefs.getInt(key, defaultValue);
        } catch (Exception e) {
            value = defaultValue;
            Log.e("READ", e.getLocalizedMessage(), e);
        }

        return value;
    }

    public static boolean write(Context context, String file, String key,
                                boolean value) {
        boolean sucess = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.putBoolean(key, value);
            editor.commit();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e("WRITE", e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public static boolean write(Context context, String file, String key,
                                String value) {
        boolean sucess = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.putString(key, value);
            editor.commit();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e("WRITE", e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public static boolean write(Context context, String file, String key,
                                int value) {
        boolean sucess = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.putInt(key, value);
            editor.commit();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e("WRITE", e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public static boolean write(Context context, String file, String key,
                                long value) {
        boolean sucess = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.putLong(key, value);
            editor.commit();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e("WRITE", e.getLocalizedMessage(), e);
        }

        return sucess;
    }

    public static boolean remove(Context context, String file, String key) {
        boolean sucess = false;

        try {
            SharedPreferences sharedPrefs = context.getApplicationContext()
                    .getSharedPreferences(file, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            editor.remove(key);
            editor.commit();

            sucess = true;
        } catch (Exception e) {
            sucess = false;
            Log.e("REMOVE", e.getLocalizedMessage(), e);
        }

        return sucess;
    }

}