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
import java.util.HashMap;
import java.util.List;

import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import static android.Manifest.permission.READ_CONTACTS;
/**
 * Created by choud on 05/04/2017.
 */

public class main extends AppCompatActivity {

    String username;

    saveSharedPreference session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new saveSharedPreference(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(saveSharedPreference.KEY_NAME);

        setContentView(R.layout.main);
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_about:
                //Toast.makeText(main.this, "About is Selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(main.this, about.class);
                startActivity(myIntent);
                return true;

            case R.id.action_logout:
                /*Intent myIntent2 = new Intent(main.this, LoginActivity.class);
                myIntent2.putExtra("finish", true); // if you are checking for this in your other Activities
                myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent2);
                return true;*/session.logoutUser();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

