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

import com.example.musicapp.Activity.MusicPlayer;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.Model.Banner;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    Context context;
    ArrayList<Banner> listBanner;

    public BannerAdapter(Context context, ArrayList<Banner> listBanner) {
        this.context = context;
        this.listBanner = listBanner;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_item_banner,parent,false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner=listBanner.get(position);
        holder.songTitle.setText(banner.getTenBaiHat());
        holder.bannerDescription.setText(banner.getNoiDung());
        Picasso.with(context).load(banner.getHinhAnh()).into(holder.bannerImage);
        Picasso.with(context).load(banner.getHinhBaiHat()).into(holder.songImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService.getService().getBaiHat(listBanner.get(position).getIdBaiHat()).enqueue(new Callback<BaiHat>() {
                    @Override
                    public void onResponse(Call<BaiHat> call, Response<BaiHat> response) {
                        Intent intent=new Intent(context, MusicPlayer.class);
                        intent.putExtra("baihat",(BaiHat)response.body());
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<BaiHat> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBanner.size();
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder{
        ImageView bannerImage;
        TextView bannerDescription;
        ImageView songImage;
        TextView songTitle;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerImage=itemView.findViewById(R.id.imageViewBanner);
            bannerDescription=itemView.findViewById(R.id.textViewBannerDescription);
            songImage=itemView.findViewById(R.id.imageViewSongImage);
            songTitle=itemView.findViewById(R.id.textViewSongTitle);

        }
    }
}
