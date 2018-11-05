package com.example.rplrus11.midsemester12rpl;

import android.content.ClipData;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecycleViewHolder extends RecyclerView.ViewHolder {
    public TextView nama;
    public ImageView img_photo;
    public TextView btndetail;
    public TextView btnshare;

    public RecycleViewHolder(View ItemView){
        super(ItemView);

     nama = (TextView) ItemView.findViewById(R.id.txtnama);
     img_photo = (ImageView) ItemView.findViewById(R.id.image1);
     btndetail = (Button) ItemView.findViewById(R.id.btndetail);
     btnshare = (Button) ItemView.findViewById(R.id.btnshare);
    }
}
