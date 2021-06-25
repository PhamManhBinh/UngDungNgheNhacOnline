package com.example.musicapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;

public class MusicPlayerAdapter extends FragmentStateAdapter {
    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public MusicPlayerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void addFragment(Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
