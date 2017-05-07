package com.jec.cpalak.jec_cs_noticeboard;
import android.content.SharedPreferences;
import android.content.Intent;
import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences.Editor;
public class saveSharedPreference {
        SharedPreferences pref;
        Editor editor;
        Context _context;
        int PRIVATE_MODE = 0;
        private static final String PREF_NAME = "JecPref";
        private static final String IS_LOGIN = "IsLoggedIn";
        public static final String KEY_NAME = "name";
        public saveSharedPreference(Context context){
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
            editor.apply();
        }
        public void createLoginSession(String name){
            editor.putBoolean(IS_LOGIN, true);
            editor.putString(KEY_NAME, name);
            editor.commit();
        }
        public void checkLogin(){
            if(!this.isLoggedIn()){
                Intent i = new Intent(_context, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(i);
            }
            else{
                Intent i = new Intent(_context, main.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                _context.startActivity(i);
            }
        }
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();
            user.put(KEY_NAME, pref.getString(KEY_NAME, null));
            return user;
        }
        public void logoutUser(){
            editor.clear();
            editor.commit();
            Intent i = new Intent(_context, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK|
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        public boolean isLoggedIn(){
            return pref.getBoolean(IS_LOGIN, false);
        }
}