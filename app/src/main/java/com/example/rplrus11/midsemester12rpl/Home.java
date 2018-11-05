package com.example.rplrus11.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public ArrayList<item_Object> itemObjectArrayList;
    RecyclerView rv_rich;
    public String TAG = Home.class.getSimpleName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_keluar:
                SharedPreferences myPrefs = getSharedPreferences("isi", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv_rich = findViewById(R.id.rv_rich);
        new LogData().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class LogData extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;

            try {
                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=d63e33e85fff1cc246603deae467eb33";
                System.out.println(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);

            } catch (Exception e) {
                Log.e("Error nya", "doInBackground: " + e.toString());
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("hasil json", "onPostExecute: " + jsonObject.toString());
            if (jsonObject != null) {
                try {
                    itemObjectArrayList = new ArrayList<item_Object>();
                    JSONArray aktualData = jsonObject.getJSONArray("results");
                    for (int x = 0; x < aktualData.length(); x++) {
                        item_Object item_object = new item_Object();
                        item_object.setNama(aktualData.getJSONObject(x).getString("title"));
                        item_object.setGambar(aktualData.getJSONObject(x).getString("poster_path"));
                        item_object.setDeskripsi(aktualData.getJSONObject(x).getString("overview"));
                        itemObjectArrayList.add(item_object);
                    }

                    rv_rich.setLayoutManager(new LinearLayoutManager(Home.this));
                    RecycleViewAdapter adapter = new RecycleViewAdapter(Home.this, itemObjectArrayList);
                    rv_rich.setAdapter(adapter);
                } catch (Exception ignored) {
                    Log.d("hasil json", "onPostExecute: " + ignored.toString());
                }
            }
            else {
            }
        }
    }
}