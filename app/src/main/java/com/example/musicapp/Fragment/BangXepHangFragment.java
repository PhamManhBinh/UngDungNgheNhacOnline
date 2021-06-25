package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Activity.DanhSachBaiHatActivity;
import com.example.musicapp.Activity.MusicPlayer;
import com.example.musicapp.Adapter.BaiHatGoiYAdapter;
import com.example.musicapp.Adapter.BangXepHangAdapter;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BangXepHangFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<BaiHat> baiHats;
    BangXepHangAdapter adapter;
    FloatingActionButton play;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bang_xep_hang, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewBangXepHang);
        play=view.findViewById(R.id.fbtnPlayBXH);

        APIService.getService().getBangXepHang().enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats= (ArrayList<BaiHat>) response.body();
                adapter = new BangXepHangAdapter(getActivity(),baiHats);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);

                play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(), MusicPlayer.class);
                        intent.putExtra("listbaihat",baiHats);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

        return view;
    }
}