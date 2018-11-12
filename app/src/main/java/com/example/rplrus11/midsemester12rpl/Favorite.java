package com.example.rplrus11.midsemester12rpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rplrus11.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus11.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {
    private MahasiswaHelper mahasiswaHelper;
    private LinearLayoutManager Layout;
    RecyclerView recyclerView;
    private ArrayList<MahasiswaModel>models;
    ModelAdapter adapter;
    TextView textdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mahasiswaHelper = new MahasiswaHelper(this);
        models = mahasiswaHelper.getAllData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ModelAdapter(getApplicationContext(),models);
        recyclerView.setAdapter(adapter);
        }

    public void onBackPressed (){
        super.onBackPressed();
        finish();
    }
}