package com.jec.cpalak.jec_cs_noticeboard;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class main extends AppCompatActivity {
    String username;
    private String TAG = main.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private static String url = "https://palakpc.github.io/json";
    ArrayList<HashMap<String, String>> noticelist;
    saveSharedPreference session;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new saveSharedPreference(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(saveSharedPreference.KEY_NAME);
        setContentView(R.layout.main);
        noticelist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new getNotice().execute();
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent myIntent = new Intent(main.this, about.class);
                startActivity(myIntent);
                return true;
            case R.id.action_logout:
                session.logoutUser();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private class getNotice extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(main.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray notice = jsonObj.getJSONArray("notices");
                    for (int i = 0; i < notice.length(); i++) {
                        JSONObject c = notice.getJSONObject(i);
                        String title = c.getString("title");
                        String date = c.getString("date");
                        String content = c.getString("content");
                        HashMap<String, String> notices = new HashMap<>();
                        notices.put("title", title);
                        notices.put("date", date);
                        notices.put("content", content);
                        noticelist.add(notices);
                    }
                }
                catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    main.this, noticelist,
                    R.layout.list_item, new String[]{"title", "date",
                    }, new int[]{R.id.title,
                    R.id.date});
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                    HashMap<String, String> val = (HashMap<String, String>) a.getItemAtPosition(i);
                    Intent mainIntent = new Intent(main.this, notice.class);
                    mainIntent.putExtra("map", val);
                    startActivity(mainIntent);
                }
            });

        }
    }
}