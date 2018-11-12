package com.example.rplrus11.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rplrus11.midsemester12rpl.database.DatabaseHelper;
import com.example.rplrus11.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus11.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;

public class ModelAdapter extends RecyclerView.Adapter<RecycleViewHolder> {
    public ArrayList<MahasiswaModel> mahasiswaModelArrayList;
    Context context;
    MahasiswaHelper mahasiswaHelper;

    public ModelAdapter(Context context, ArrayList<MahasiswaModel> mahasiswaModelArrayList) {
        this.context = context;
        this.mahasiswaModelArrayList = mahasiswaModelArrayList;
        mahasiswaHelper = new MahasiswaHelper(context);
    }
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
        final MahasiswaModel model = mahasiswaModelArrayList.get(position);
        Glide.with(context)
                .load(model.getUrl())
                .into(holder.img_photo);
        holder.nama.setText(mahasiswaModelArrayList.get(position).getName());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Nama = mahasiswaModelArrayList.get(position).getName();
                final String Gambar = mahasiswaModelArrayList.get(position).getNim();
                final String Deskripsi = mahasiswaModelArrayList.get(position).getUrl();
                final String id = String.valueOf(mahasiswaModelArrayList.get(position).getId());
                Log.e("TAG", "deskripsi " + Deskripsi);
                Intent i = new Intent(context.getApplicationContext(), detail_group.class);
                i.putExtra("Nama", Nama);
                i.putExtra("Gambar", Gambar);
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
                final int position2 = position;
                final String name = model.getId();
                mahasiswaHelper.delete(name);
                mahasiswaModelArrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mahasiswaModelArrayList.size());

            }
        });
    }
            @Override
            public int getItemCount() {
                return mahasiswaModelArrayList.size();
    }
}
