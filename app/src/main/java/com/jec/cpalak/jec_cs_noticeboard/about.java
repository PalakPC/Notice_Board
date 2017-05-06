package com.jec.cpalak.jec_cs_noticeboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class about extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent();
        setContentView(R.layout.about);
    }
    public void aboutCSE(View c){
        setContentView(R.layout.about_cse);
    }
    public void aboutDev(View d) {
        setContentView(R.layout.about_dev);
    }
    public void aboutApp(View a){
        setContentView(R.layout.about_app);
    }
}