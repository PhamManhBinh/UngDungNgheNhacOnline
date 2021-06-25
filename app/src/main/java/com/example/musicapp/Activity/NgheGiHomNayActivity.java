package com.example.musicapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NgheGiHomNayActivity  extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nghe_gi_hom_nay);

        AnhXa();
        LoadingDialog loadingDialog = LoadingDialog.Companion.get(this).show();
        APIService.getService().getListPlaylist().enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                loadingDialog.hide();
                ArrayList<Playlist> arrayList = (ArrayList<Playlist>) response.body();
                PlaylistAdapter adapter = new PlaylistAdapter(NgheGiHomNayActivity.this, arrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(NgheGiHomNayActivity.this, 2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                loadingDialog.hide();
                Toast.makeText(NgheGiHomNayActivity.this,"Vui lòng kiểm tra kết nối Internet và thử lại",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerviewNghegihomnay);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nghe Gì Hôm Nay ?");
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
