package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapp.Adapter.DanhSachBaiHatAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<BaiHat> listBaiHat;
    DanhSachBaiHatAdapter adapter;
    ExtendedFloatingActionButton btnPlayAll;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView image;
    //TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        AnhXa();
        Intent intent=getIntent();
        if(intent.hasExtra("Playlist")) {

            Playlist playlist = (Playlist) intent.getSerializableExtra("Playlist");
            collapsingToolbarLayout.setTitle(playlist.getTen());
            //title.setText(playlist.getTen());
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));
            collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
            Picasso.with(DanhSachBaiHatActivity.this).load(playlist.getHinhNen()).into(image);

            APIService.getService().getListBaiHatByIDPlaylist(Integer.parseInt(playlist.getIdPlaylist())).enqueue(new Callback<List<BaiHat>>() {
                @Override
                public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {

                    listBaiHat = (ArrayList<BaiHat>) response.body();
                    adapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listBaiHat);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<List<BaiHat>> call, Throwable t) {

                }
            });

        }

        btnPlayAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listBaiHat==null)
                    return;
                Intent intent=new Intent(DanhSachBaiHatActivity.this,MusicPlayer.class);
                intent.putExtra("listbaihat",listBaiHat);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.listsong);
        btnPlayAll=findViewById(R.id.fab);
        image=findViewById(R.id.imageHinhAnh);
        //title=findViewById(R.id.textViewTitle);
        collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.bringToFront();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}