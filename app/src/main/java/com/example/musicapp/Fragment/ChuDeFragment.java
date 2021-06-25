package com.example.musicapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Adapter.ChuDeAdapter;
import com.example.musicapp.Model.ChuDe;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChuDeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ChuDe> listChuDe;
    ChuDeAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chu_de, container, false);
        recyclerView=view.findViewById(R.id.recyclerviewChuDe);

        APIService.getService().getListChuDe().enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                listChuDe= (ArrayList<ChuDe>) response.body();
                adapter=new ChuDeAdapter(getActivity(),listChuDe);

                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });

        return view;
    }
}