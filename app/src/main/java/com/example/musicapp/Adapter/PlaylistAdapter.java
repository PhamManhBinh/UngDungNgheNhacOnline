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
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.NghegiViewHolder> {
    Context context;
    ArrayList<Playlist> dsPlaylist;

    public PlaylistAdapter(Context context, ArrayList<Playlist> dsPlaylist) {
        this.context = context;
        this.dsPlaylist = dsPlaylist;
    }

    @NonNull
    @Override
    public NghegiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_item_playlist,parent,false);
        return new NghegiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NghegiViewHolder holder, int position) {
        holder.textViewTenPlaylist.setText(dsPlaylist.get(position).getTen());
        Picasso.with(context).load(dsPlaylist.get(position).getHinhNen()).into(holder.imgPlaylist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("Playlist",dsPlaylist.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dsPlaylist.size();
    }

    public static class NghegiViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenPlaylist;
        ImageView imgPlaylist;
        public NghegiViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTenPlaylist=itemView.findViewById(R.id.textviewNghegihomnay_playlist);
            imgPlaylist=itemView.findViewById(R.id.imageviewNghegihomnay_playlist);
        }
    }
}
