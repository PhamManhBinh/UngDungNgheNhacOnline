package com.example.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.MusicPlayer;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.TimKiemViewHolder> {
    Context context;
    ArrayList<BaiHat> listBaiHat;

    public TimKiemAdapter(Context context, ArrayList<BaiHat> listBaiHat) {
        this.context = context;
        this.listBaiHat = listBaiHat;
    }

    @NonNull
    @Override
    public TimKiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_tim_kiem,parent,false);
        return new TimKiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemViewHolder holder, int position) {
        holder.tenBaiHat.setText(listBaiHat.get(position).getTenBaiHat());
        holder.tenCaSi.setText(listBaiHat.get(position).getCaSy());
        Picasso.with(context).load(listBaiHat.get(position).getHinhBaiHat()).into(holder.imgBaiHat);
        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService.getService().Like(listBaiHat.get(position).getIdBaiHat()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("success")) {
                            Toast.makeText(context, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
                            holder.love.setImageResource(R.drawable.heart);
                            holder.love.setEnabled(false);
                        }
                        else
                            Toast.makeText(context,"Có lỗi!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MusicPlayer.class);
                intent.putExtra("baihat",listBaiHat.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBaiHat.size();
    }

    public static class TimKiemViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBaiHat;
        TextView tenBaiHat;
        TextView tenCaSi;
        ImageView love;
        public TimKiemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBaiHat=itemView.findViewById(R.id.imageViewhinhtimkiem);
            tenBaiHat=itemView.findViewById(R.id.textViewtentimkiem);
            tenCaSi=itemView.findViewById(R.id.textViewtencasitimkiem);
            love=itemView.findViewById(R.id.love_timkiem);
        }
    }
}
