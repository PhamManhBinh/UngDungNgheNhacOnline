package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.MusicPlayer;
import com.example.musicapp.Adapter.DanhSachDangPhatAdapter;
import com.example.musicapp.Adapter.PlaylistAdapter;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.Model.Playlist;
import com.example.musicapp.R;

import java.util.ArrayList;

public class DanhSachDangPhatFragment extends Fragment {
    RecyclerView recyclerView;
    DanhSachDangPhatAdapter PlayingAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach_dang_phat,container,false);
        recyclerView=view.findViewById(R.id.recyclerviewDanhsachdangphat);
        PlayingAdapter=new DanhSachDangPhatAdapter(getActivity(), MusicPlayer.mangBaiHat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(PlayingAdapter);
        return view;
    }
    public void notifyAdapterChange(){
        PlayingAdapter.notifyDataSetChanged();
    }

}
