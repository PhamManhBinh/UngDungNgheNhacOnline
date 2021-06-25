package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.MusicPlayer;
import com.example.musicapp.Fragment.DanhSachDangPhatFragment;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachDangPhatAdapter extends RecyclerView.Adapter<DanhSachDangPhatAdapter.BaiHatPlayingViewHolder> {
    Context context;
    ArrayList<BaiHat> baihats;

    public DanhSachDangPhatAdapter(Context context, ArrayList<BaiHat> baihats) {
        this.context = context;
        this.baihats = baihats;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==MusicPlayer.dangPhat)
            return 1;
        return 0;
    }

    @NonNull
    @Override
    public BaiHatPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_baihat_playing_1,parent,false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_baihat_playing,parent,false);
        return new BaiHatPlayingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiHatPlayingViewHolder holder, int position) {
        holder.textViewTenBai.setText(baihats.get(position).getTenBaiHat());
        holder.textViewTenCaSi.setText(baihats.get(position).getCaSy());
        Picasso.with(context).load(baihats.get(position).getHinhBaiHat()).into(holder.imgHinhAnhBaiHat);

        if(holder.getItemViewType()==0) {
            holder.btnPopupMenu=holder.itemView.findViewById(R.id.btnpopup);
            holder.btnPopupMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HienThiPopupMenu(holder.btnPopupMenu, position);
                }
            });
        }

        holder.viewBaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position!=MusicPlayer.dangPhat)
                    ((MusicPlayer)context).chuyenBai(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return baihats.size();
    }

    private void HienThiPopupMenu(ImageView btnMenu,int position){
        PopupMenu popupMenu= new PopupMenu(context,btnMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_song_playing,popupMenu.getMenu());
        if(MusicPlayer.dangPhat==position)
            popupMenu.getMenu().findItem(R.id.menuNext).setVisible(false);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuLove:
                        Thich(position);
                        break;
                    case R.id.menuNext:
                        BaiHat temp=MusicPlayer.mangBaiHat.get(position);
                        MusicPlayer.mangBaiHat.remove(position);
                        if(position<MusicPlayer.dangPhat)
                            MusicPlayer.dangPhat=MusicPlayer.dangPhat-1;
                        MusicPlayer.mangBaiHat.add(MusicPlayer.dangPhat+1,temp);
                        notifyDataSetChanged();
                        break;
                    case R.id.menuXoa:
                        XoaBaiHat(position);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void XoaBaiHat(int position) {
        MusicPlayer.mangBaiHat.remove(position);
        if(position==MusicPlayer.dangPhat) {
            ((MusicPlayer) context).chuyenBai(position);
        }else{
            if(position<MusicPlayer.dangPhat) MusicPlayer.dangPhat--;
            notifyDataSetChanged();
        }
    }
    private void Thich(int position){
        APIService.getService().Like(baihats.get(position).getIdBaiHat()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("success")) {
                    Toast.makeText(context, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context,"Có lỗi!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public static class BaiHatPlayingViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAnhBaiHat;
        TextView textViewTenBai;
        TextView textViewTenCaSi;
        View viewBaiHat;
        ImageView btnPopupMenu;
        public BaiHatPlayingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAnhBaiHat=itemView.findViewById(R.id.imageviewbaihat_playing);
            textViewTenBai=itemView.findViewById(R.id.textviewtenbaihat_playing);
            textViewTenCaSi=itemView.findViewById(R.id.textviewtencasi_playing);
            viewBaiHat=itemView.findViewById(R.id.viewItemBaiHatDangPhat);
        }
    }
}
