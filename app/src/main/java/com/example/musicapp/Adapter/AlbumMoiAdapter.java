package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.DanhSachBaiHatActivity;
import com.example.musicapp.Activity.DanhSachBaiHatAlbumActivity;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumMoiAdapter extends RecyclerView.Adapter<AlbumMoiAdapter.AlbumViewHolder> {
    Context context;
    ArrayList<Album> listAlbum;

    public AlbumMoiAdapter(Context context, ArrayList<Album> listAlbum) {
        this.context = context;
        this.listAlbum = listAlbum;
    }

    @NonNull
    @Override
    public AlbumMoiAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_item_albummoi,parent,false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumMoiAdapter.AlbumViewHolder holder, int position) {
        holder.tenAlbum.setText(listAlbum.get(position).getTenAlbum());
        holder.tenCasiAlbum.setText(listAlbum.get(position).getTenCasiAlbum());
        Picasso.with(context).load(listAlbum.get(position).getHinhAlbum()).into(holder.hinhAlbum);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DanhSachBaiHatAlbumActivity.class);
                intent.putExtra("Album",listAlbum.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAlbum.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{
        TextView tenAlbum;
        TextView tenCasiAlbum;
        ImageView hinhAlbum;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tenAlbum=itemView.findViewById(R.id.textviewTenAlbumMoi);
            tenCasiAlbum=itemView.findViewById(R.id.textviewTenCaSiAlbumMoi);
            hinhAlbum=itemView.findViewById(R.id.imageviewHinhAlbumMoi);
        }
    }
}
