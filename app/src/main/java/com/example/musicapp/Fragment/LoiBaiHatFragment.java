package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.R;

public class LoiBaiHatFragment extends Fragment {
    TextView lyric;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loi_bai_hat,container,false);
        lyric = view.findViewById(R.id.textviewLoiBaiHat);
        return view;
    }
    public void setLyric(String s){
        lyric.setText(s);
        //Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
    }
}
