package com.example.rplrus11.midsemester12rpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class twoFragment extends Fragment {
    public ArrayList<Results> resultsArrayList ;
    RecyclerView recyclerView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.two_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Rview_up);
        load_data_upcoming();
        return view;
    }

    private void load_data_upcoming() {
        JsonApi service = Retrofitinstance.getRetrofitInstance().create(JsonApi.class);
        Call<jsonRespond> call = service.getJsonUpcoming();
        call.enqueue(new Callback<jsonRespond>() {
            @Override
            public void onResponse(Call<jsonRespond> call, Response<jsonRespond> response) {
                jsonRespond jsonRespond = response.body();
                resultsArrayList = new ArrayList<>(Arrays.asList(jsonRespond.getResults()));
                Log.d("responku", "onResponse: " + jsonRespond.getResults());
                recycleAdapterRetro adapter = new recycleAdapterRetro(view.getContext(),resultsArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<jsonRespond> call, Throwable t) {
                Log.d("responku", "onFailure: gagal");
            }
        });
    }
}