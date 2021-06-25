package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Adapter.AlbumMoiAdapter;
import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistChuDeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_chu_de);
        AnhXa();
        Intent intent=getIntent();
        if(intent.hasExtra("ChuDe")) {
            ChuDe chude = (ChuDe) intent.getSerializableExtra("ChuDe");

            getSupportActionBar().setTitle(chude.getTenChuDe());
            LoadingDialog loadingDialog = LoadingDialog.Companion.get(this).show();
            APIService.getService().getListPlaylistByChuDe(Integer.parseInt(chude.getIdChuDe())).enqueue(new Callback<List<Playlist>>() {
                @Override
                public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                    loadingDialog.hide();
                    ArrayList<Playlist> arrayList = (ArrayList<Playlist>) response.body();
                    PlaylistAdapter adapter = new PlaylistAdapter(PlaylistChuDeActivity.this, arrayList);
                    recyclerView.setLayoutManager(new GridLayoutManager(PlaylistChuDeActivity.this, 2));
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Playlist>> call, Throwable t) {
                    loadingDialog.hide();
                    Toast.makeText(PlaylistChuDeActivity.this,"Vui lòng kiểm tra kết nối Internet và thử lại",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerviewPlaylistChuDe);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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