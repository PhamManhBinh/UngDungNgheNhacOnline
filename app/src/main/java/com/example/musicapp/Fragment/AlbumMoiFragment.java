package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Activity.AlbumMoiActivity;
import com.example.musicapp.Adapter.AlbumMoiAdapter;
import com.example.musicapp.Model.Album;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumMoiFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Album> listAlbum;
    AlbumMoiAdapter adapter;
    View title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_album_moi, container, false);
        recyclerView=view.findViewById(R.id.recyclerviewAlbumMoi);
        title=view.findViewById(R.id.Albummoi);


        APIService.getService().getListAlbumLimit(4).enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                listAlbum= (ArrayList<Album>) response.body();
                adapter=new AlbumMoiAdapter(getContext(),listAlbum);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AlbumMoiActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}