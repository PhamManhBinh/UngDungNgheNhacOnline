package com.example.musicapp.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CD_Fragment extends Fragment {
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cd,container,false);
        circleImageView=view.findViewById(R.id.imageviewSongPlayer);
        objectAnimator=ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(7000);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;
    }

    public void setImage(String link){
        Picasso.with(getActivity()).load(link).into(circleImageView);
        //Toast.makeText(getActivity(),link,Toast.LENGTH_SHORT).show();
    }

    public void pauseAnimation(){
        if(objectAnimator.isRunning())
            objectAnimator.pause();
    }

    public void resumeAnimation(){
        if(objectAnimator.isPaused())
            objectAnimator.resume();
    }

}
