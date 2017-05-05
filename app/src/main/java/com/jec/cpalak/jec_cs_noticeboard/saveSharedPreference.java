package com.jec.cpalak.jec_cs_noticeboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by choud on 05/05/2017.
 */

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class saveSharedPreference {
        // Shared Preferences
        SharedPreferences pref;

        // Editor for Shared preferences
        Editor editor;

        // Context
        Context _context;

        // Shared pref mode
        int PRIVATE_MODE = 0;

        // Sharedpref file name
        private static final String PREF_NAME = "JecPref";

        // All Shared Preferences Keys
        private static final String IS_LOGIN = "IsLoggedIn";

        // User name (make variable public to access from outside)
        public static final String KEY_NAME = "name";

        // Email address (make variable public to access from outside)

        // Constructor
        public saveSharedPreference(Context context){
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
            editor.apply();
        }

        /**
         * Create login session
         * */
        public void createLoginSession(String name){
            // Storing login value as TRUE
            editor.putBoolean(IS_LOGIN, true);

            // Storing name in pref
            editor.putString(KEY_NAME, name);

            // Storing email in pref

            // commit changes
            editor.commit();
        }

        /**
         * Check login method wil check user login status
         * If false it will redirect user to login page
         * Else won't do anything
         * */
        public void checkLogin(){
            // Check login status
            if(!this.isLoggedIn()){
                // user is not logged in redirect him to Login Activity
                Intent i = new Intent(_context, LoginActivity.class);
                // Closing all the Activities
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                _context.startActivity(i);
            }
            else{
                Intent i = new Intent(_context, main.class);
                // Closing all the Activities
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                _context.startActivity(i);
            }

        }



        /**
         * Get stored session data
         * */
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();
            // user name
            user.put(KEY_NAME, pref.getString(KEY_NAME, null));

            // user email id

            // return user
            return user;
        }

        /**
         * Clear session details
         * */
        public void logoutUser(){

            /*Intent myIntent2 = new Intent(main.this, LoginActivity.class);
                myIntent2.putExtra("finish", true); // if you are checking for this in your other Activities
                myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent2);*/

            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();

            // After logout redirect user to Loing Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK|
                    Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

        /**
         * Quick check for login
         * **/
        // Get Login State
        public boolean isLoggedIn(){
            return pref.getBoolean(IS_LOGIN, false);
        }

}
