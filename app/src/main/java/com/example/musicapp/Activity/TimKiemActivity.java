package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.musicapp.Adapter.TimKiemAdapter;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView searchView;
    RecyclerView recyclerView;
    TimKiemAdapter adapter;
    ArrayList<BaiHat> baiHats;
    ExtendedFloatingActionButton playAll;
    LinearLayout noResult;
    ImageView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        AnhXa();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                TimKiemBaiHat(newText);
                return false;
            }
        });

        playAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TimKiemActivity.this, MusicPlayer.class);
                intent.putExtra("listbaihat",baiHats);
                startActivity(intent);
            }
        });
    }

    private void TimKiemBaiHat(String newText) {
        loading.setVisibility(View.VISIBLE);
        playAll.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        noResult.setVisibility(View.INVISIBLE);
        APIService.getService().TimKiem(newText).enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                loading.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                baiHats= (ArrayList<BaiHat>) response.body();
                adapter=new TimKiemAdapter(TimKiemActivity.this,baiHats);
                recyclerView.setLayoutManager(new LinearLayoutManager(TimKiemActivity.this,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);

                if(baiHats.size()>0) {
                    playAll.setVisibility(View.VISIBLE);
                    noResult.setVisibility(View.INVISIBLE);
                }
                else {
                    playAll.setVisibility(View.INVISIBLE);
                    noResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbar=findViewById(R.id.toolbar);
        searchView=findViewById(R.id.searchViewTimKiem);
        recyclerView=findViewById(R.id.recyclerviewTimKiem);
        loading=findViewById(R.id.loadingGif);
        noResult=findViewById(R.id.noSearchResults);
        playAll=findViewById(R.id.floatbtnPlayAll);
        playAll.setVisibility(View.INVISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tìm Kiếm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setQueryHint("Nhập Tên Bài Hát, Nghệ Sĩ,...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}