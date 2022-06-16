package fpt.aptech.projectcard.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static SessionManager jInstance;
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    private SessionManager(Context context) {
        prefs = context.getSharedPreferences("username_pref", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static synchronized SessionManager getInstance(Context context) {
        if (jInstance != null) {
            return jInstance;
        } else {
            jInstance = new SessionManager(context);
            return jInstance;
        }
    }

    public void setUsername(String username){
        editor.putString("username",username);
        editor.apply();
        editor.commit();
    }
    public String getUsername(){
        return prefs.getString("username","");
    }
}
