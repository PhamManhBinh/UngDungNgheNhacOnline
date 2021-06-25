package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.AlbumMoiActivity;
import com.example.musicapp.Activity.NgheGiHomNayActivity;
import com.example.musicapp.Activity.PlaylistChuDeActivity;
import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ChuDeViewHolder> {
    Context context;
    ArrayList<ChuDe> listChuDe;

    public ChuDeAdapter(Context context, ArrayList<ChuDe> listChuDe) {
        this.context = context;
        this.listChuDe = listChuDe;
    }

    @NonNull
    @Override
    public ChuDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_item_chude,parent,false);
        return new ChuDeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuDeViewHolder holder, int position) {
        Picasso.with(context).load(listChuDe.get(position).getHinhChuDe()).into(holder.imgChude);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaylistChuDeActivity.class);
                intent.putExtra("ChuDe",listChuDe.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChuDe.size();
    }

    public static class ChuDeViewHolder extends RecyclerView.ViewHolder{
        ImageView imgChude;

        public ChuDeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChude=itemView.findViewById(R.id.imageViewItemChuDe);
        }
    }
}
