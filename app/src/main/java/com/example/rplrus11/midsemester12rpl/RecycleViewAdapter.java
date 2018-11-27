package com.example.rplrus11.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    Context context;
    String url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    ArrayList<item_Object> ItemObjectArraylist;
    public String TAG = RecycleViewAdapter.class.getSimpleName();

    public RecycleViewAdapter(Context context, ArrayList<item_Object> itemObjectArraylist) {
        this.context = context;
        ItemObjectArraylist = itemObjectArraylist;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        final item_Object item_object = ItemObjectArraylist.get(position);
        Log.d("erorku", "onBindViewHolder: " + holder.nama);
        Glide.with(context)
                .load(item_object.getGambar())
                .into(holder.img_photo);
        holder.nama.setText(item_object.getNama());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Nama = item_object.getNama();
                final String Gambar =  item_object.getGambar();
                final String Deskripsi = item_object.getDeskripsi();
                Log.e(TAG, "deskripsi " + Deskripsi);
                Intent i = new Intent(context.getApplicationContext(), detail_group.class);
                i.putExtra("Nama", Nama);
                i.putExtra("Gambar", Gambar);
                i.putExtra("Deskripsi", Deskripsi);
                Log.e(TAG, "onClick: "+Gambar );
                context.startActivity(i);
            }
        });
        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(ACTION_SEND);
                sendIntent.putExtra(EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemObjectArraylist.size();
    }

}