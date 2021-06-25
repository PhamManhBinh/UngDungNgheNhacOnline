package com.example.musicapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Adapter.BannerAdapter;
import com.example.musicapp.Model.Banner;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerFragment extends Fragment {

    ViewPager2 viewPager2;
    BannerAdapter adapter;
    ArrayList<Banner> listBanner;
    CircleIndicator3 indicator;
    Handler handler;
    Runnable runnable;
    int CurrentItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_banner, container, false);
        viewPager2=view.findViewById(R.id.viewpager);
        indicator = view.findViewById(R.id.indicator);

        DataService dataService= APIService.getService();
        dataService.getListBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {

                //Toast.makeText(getActivity(),"OK",Toast.LENGTH_SHORT).show();
                listBanner=(ArrayList<Banner>) response.body();
                adapter=new BannerAdapter(getActivity(),listBanner);
                viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                viewPager2.setAdapter(adapter);
                indicator.setViewPager(viewPager2);

                //auto slide
                handler=new Handler();
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        CurrentItem=viewPager2.getCurrentItem();
                        CurrentItem++;
                        if(CurrentItem>=viewPager2.getAdapter().getItemCount()){
                            CurrentItem=0;
                        }
                        viewPager2.setCurrentItem(CurrentItem,true);
                        handler.postDelayed(runnable,4000);
                    }
                };
                handler.postDelayed(runnable,4000);
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}