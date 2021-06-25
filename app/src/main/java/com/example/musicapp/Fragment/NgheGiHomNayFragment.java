package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Activity.NgheGiHomNayActivity;
import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NgheGiHomNayFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Playlist> dsPlaylist;
    PlaylistAdapter adapter;
    View title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nghe_gi_hom_nay, container, false);
        title=view.findViewById(R.id.Nghegihomnay);
        recyclerView=view.findViewById(R.id.recyclerviewNghegihomnay);



        APIService.getService().getListPlaylistLimit(4).enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                dsPlaylist = (ArrayList<Playlist>) response.body();
                adapter =new PlaylistAdapter(getActivity(),dsPlaylist);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NgheGiHomNayActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}