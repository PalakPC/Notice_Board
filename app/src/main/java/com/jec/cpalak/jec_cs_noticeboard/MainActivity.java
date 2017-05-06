package com.jec.cpalak.jec_cs_noticeboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    saveSharedPreference session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new saveSharedPreference(getApplicationContext());
        session.checkLogin();
    }
}
