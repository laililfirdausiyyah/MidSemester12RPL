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
    LinearLayout recycleviewdata;
    TextView textdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        textdata = findViewById(R.id.textdata);
        recyclerView = findViewById(R.id.recycleview);
        Layout = new LinearLayoutManager(Favorite.this);
        recyclerView.setLayoutManager(Layout);

        if (mahasiswaHelper.getAllData()== null){
            recyclerView.setVisibility(View.VISIBLE);
            textdata.setVisibility(View.VISIBLE);
        }else if (mahasiswaHelper.getAllData()!= null){
            textdata.setVisibility(View.GONE);
            mahasiswaHelper = new MahasiswaHelper(getApplicationContext());
            mahasiswaHelper.open();
            models = mahasiswaHelper.getAllData();
            adapter = new ModelAdapter(getApplicationContext(),models);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            //recyclerView.setAdapter(adapter);
            Log.d("database_failed", "onCreate: " + "Success");
        }

    }


}

