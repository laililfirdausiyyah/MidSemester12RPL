package com.example.rplrus11.midsemester12rpl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rplrus11.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus11.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;
import static android.support.constraint.Constraints.TAG;

public class recycleAdapterRetro extends RecyclerView.Adapter<RecycleViewHolder> {

    private ArrayList<Results> itemObjectArraylist ;
    Context context;


    public recycleAdapterRetro(Context context, ArrayList<Results> itemObjectArraylist) {
        this.context = context;
        this.itemObjectArraylist = itemObjectArraylist;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, final int position) {
        final Results item_Object = itemObjectArraylist.get(position);
        Glide.with(context)
                .load(only_url.url + item_Object.getPosterPath())
                .into(holder.img_photo);
        holder.nama.setText(itemObjectArraylist.get(position).getTitle());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nama = itemObjectArraylist.get(position).getTitle();
                final String Gambar = itemObjectArraylist.get(position).getPosterPath();
                final String Deskripsi = itemObjectArraylist.get(position).getOverview();
                final String id = String.valueOf(itemObjectArraylist.get(position).getId());
                Log.e("TAG", "deskripsi " + Deskripsi);
                Intent i = new Intent(context.getApplicationContext(), detail_group.class);
                i.putExtra("Nama", Nama);
                i.putExtra("Gambar", only_url.url + Gambar);
                i.putExtra("Deskripsi", Deskripsi);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.e("TAG", "onClick: " + Gambar);
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
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        context);
                builder.setTitle("Delete");
                builder.setCancelable(true);
                builder.setMessage("are you sure to delete this item?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        itemObjectArraylist.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, itemObjectArraylist.size());
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+itemObjectArraylist.size());
        return itemObjectArraylist.size();
    }
}

