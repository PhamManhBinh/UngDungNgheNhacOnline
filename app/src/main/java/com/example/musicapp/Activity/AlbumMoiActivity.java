package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicapp.Adapter.AlbumMoiAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumMoiActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_moi);

        AnhXa();

        LoadingDialog loadingDialog = LoadingDialog.Companion.get(this).show();
        APIService.getService().getListAlbum().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                loadingDialog.hide();
                ArrayList<Album> arrayList = (ArrayList<Album>) response.body();
                AlbumMoiAdapter adapter = new AlbumMoiAdapter(AlbumMoiActivity.this, arrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(AlbumMoiActivity.this, 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                loadingDialog.hide();
                Toast.makeText(AlbumMoiActivity.this,"Vui lòng kiểm tra kết nối Internet và thử lại",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerviewList);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album Mới Phát Hành");
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