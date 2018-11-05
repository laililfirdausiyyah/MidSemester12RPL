package com.example.rplrus11.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText edt_email, edt_pass;
    Button btnlogin;
    TextView txt_daftar;
    SharedPreferences sharedPreferences;

    private int MAIN_ACTIVITY_REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_pass = (EditText) findViewById(R.id.edt_pass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txt_daftar = (TextView) findViewById(R.id.txt_daftar);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_email.getText().toString().equals("admin") && edt_pass.getText().toString().equals("admin")) { ;
                    Toast.makeText(getApplicationContext(), "berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    String username = edt_email.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("isi",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.commit();
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data2){
        sharedPreferences = sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean stateValue = sharedPreferences.getBoolean("", false);
        if (requestCode == MAIN_ACTIVITY_REQUEST_CODE){
            if (! stateValue){
                finish();
            }else {
                updateLoginState(false);
                super.onActivityResult(requestCode, resultCode, data2);
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data2);
        }
    }
    private void updateLoginState(boolean b){
        
    }
}