package com.jec.cpalak.jec_cs_noticeboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by choud on 05/07/2017.
 */

public class notice extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>)intent.getSerializableExtra("map");
        setContentView(R.layout.notice);
        TextView Title = (TextView) findViewById(R.id.title2);
        TextView Date = (TextView) findViewById(R.id.date2);
        TextView Content = (TextView) findViewById(R.id.content2);
        Title.setText(hashMap.get("title"));
        Date.setText(hashMap.get("date"));
        Content.setText(hashMap.get("content"));
    }
}
