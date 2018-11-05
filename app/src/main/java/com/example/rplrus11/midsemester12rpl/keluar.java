package com.example.rplrus11.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class keluar extends AppCompatActivity {
    public static final String data = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("isi", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");
        if (user.equals("")){
            Intent intent = new Intent(keluar.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(keluar.this,Home.class);
            startActivity(intent);
            finish();
        }
    }
}
