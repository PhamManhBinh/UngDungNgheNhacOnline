package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Adapter.DanhSachDangPhatAdapter;
import com.example.musicapp.Adapter.MusicPlayerAdapter;
import com.example.musicapp.Fragment.CD_Fragment;
import com.example.musicapp.Fragment.DanhSachDangPhatFragment;
import com.example.musicapp.Fragment.LoiBaiHatFragment;
import com.example.musicapp.Model.BaiHat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MusicPlayer extends AppCompatActivity {
    Toolbar toolbar;
    ImageView background;
    ImageView tim;
    TextView textviewSongName,textViewCaSi;
    ImageView btnPlay,btnNext,btnPrevious,btnShuffle,btnRepeat;
    TextView txtPlaying, txtTimeTotal;
    //BaiHat baiHat;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ViewPager2 viewPager2;
    CircleIndicator3 indicator3;
    MusicPlayerAdapter playerAdapter;
    CD_Fragment cd_fragment;
    DanhSachDangPhatFragment danhSachDangPhatFragment;
    LoiBaiHatFragment loiBaiHatFragment;
    public static ArrayList<BaiHat> mangBaiHat=new ArrayList<>();
    public static int dangPhat;
    boolean isRepeat=false;
    boolean isShuffle=false;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        GetDataFromIntent();
        KhoiTao();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().setTitle(mangBaiHat.get(dangPhat).getTenBaiHat());
        textviewSongName.setText(mangBaiHat.get(dangPhat).getTenBaiHat());
        textViewCaSi.setText(mangBaiHat.get(dangPhat).getCaSy());
        Picasso.with(this).load(mangBaiHat.get(dangPhat).getHinhBaiHat()).transform(new BlurTransformation(this,40)).into(background);

        PlayMp3(dangPhat);

        XuLySuKien();


    }


    private void KhoiTao() {
        toolbar=findViewById(R.id.toolbar);
        background=findViewById(R.id.imgBackground);
        tim=findViewById(R.id.activity_music_player_love);
        textviewSongName = findViewById(R.id.txtsn);
        textViewCaSi=findViewById(R.id.txtcasi);
        //imgSong = findViewById(R.id.imageviewSongPlayer);
        btnPlay = findViewById(R.id.btnplay);
        btnPrevious= findViewById(R.id.btnprevious);
        btnNext = findViewById(R.id.btnnext);
        btnShuffle=findViewById(R.id.btnshuffle);
        btnRepeat=findViewById(R.id.btnrepeat);
        txtPlaying = findViewById(R.id.txtstart);
        txtTimeTotal = findViewById(R.id.txtstop);
        seekBar=findViewById(R.id.seekbar);
        viewPager2=findViewById(R.id.viewpagerPlayer);
        indicator3=findViewById(R.id.indicator);
        viewPager2.setOffscreenPageLimit(2);
        //anim = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
        // anim.setRepeatCount(Animation.INFINITE);
        //anim.setRepeatMode(Animation.RESTART);
        //imgSong.startAnimation(anim);
        cd_fragment=new CD_Fragment();
        loiBaiHatFragment=new LoiBaiHatFragment();
        danhSachDangPhatFragment=new DanhSachDangPhatFragment();
        playerAdapter = new MusicPlayerAdapter(this);
        playerAdapter.addFragment(danhSachDangPhatFragment);
        playerAdapter.addFragment(cd_fragment);
        playerAdapter.addFragment(loiBaiHatFragment);
        viewPager2.setAdapter(playerAdapter);
        viewPager2.setCurrentItem(1);
        indicator3.setViewPager(viewPager2);
        danhSachDangPhatFragment= (DanhSachDangPhatFragment) playerAdapter.getItem(0);
        cd_fragment= (CD_Fragment) playerAdapter.getItem(1);
        loiBaiHatFragment= (LoiBaiHatFragment) playerAdapter.getItem(2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.bringToFront();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private void GetDataFromIntent(){
        Intent intent = getIntent();
        mangBaiHat.clear();

        if (intent != null) {
            if (intent.hasExtra("baihat")) {
                mangBaiHat.add((BaiHat) intent.getSerializableExtra("baihat"));
            }
            if (intent.hasExtra("listbaihat")) {
                mangBaiHat = (ArrayList<BaiHat>) intent.getSerializableExtra("listbaihat");
            }
            dangPhat=0;
        }

    }

    private void XuLySuKien() {


        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIService.getService().Like(mangBaiHat.get(dangPhat).getIdBaiHat()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("success")) {
                            Toast.makeText(MusicPlayer.this, "Bạn đã thích bài hát này", Toast.LENGTH_SHORT).show();
                            tim.setImageResource(R.drawable.heart);
                            tim.setEnabled(false);
                        }
                        else
                            Toast.makeText(MusicPlayer.this,"Có lỗi!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

        //sự kiện nút play, pause
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null)
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        btnPlay.setImageResource(R.drawable.ic_play1);
                        cd_fragment.pauseAnimation();
                    } else {
                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.ic_pause);
                        cd_fragment.resumeAnimation();
                    }
            }
        });

        //sự kiện nút next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRepeat){
                    //seek lại về 0
                    mediaPlayer.seekTo(0);
                }else if(isShuffle){
                    dangPhat=new Random().nextInt(mangBaiHat.size());
                    chuyenBai(dangPhat);
                }else{
                    dangPhat++;
                    if (dangPhat > mangBaiHat.size() - 1) dangPhat = 0;
                    chuyenBai(dangPhat);
                }
            }
        });

        //sự kiện nút previous
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRepeat){
                    //seek lại về 0
                    mediaPlayer.seekTo(0);
                }else if(isShuffle){
                    dangPhat=new Random().nextInt(mangBaiHat.size());
                    chuyenBai(dangPhat);
                }else {
                    dangPhat--;
                    if (dangPhat < 0) dangPhat = mangBaiHat.size() - 1;
                    chuyenBai(dangPhat);
                }
            }
        });

        //sự kiện nút trộn nhạc
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShuffle) {
                    btnShuffle.setImageResource(R.drawable.ic_suffer);
                    isShuffle = false;
                }
                else {
                    btnShuffle.setImageResource(R.drawable.ic_suffer_selected);
                    isShuffle = true;
                }
            }
        });

        //sự kiện nút repeat
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRepeat) {
                    btnRepeat.setImageResource(R.drawable.ic_loop);
                    isRepeat = false;
                }
                else {
                    btnRepeat.setImageResource(R.drawable.ic_loop_selected);
                    isRepeat = true;
                }
                mediaPlayer.setLooping(isRepeat);
            }
        });

        //sự kiện thanh seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser)
                    mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    public void chuyenBai(int songPosition) {
        dangPhat=songPosition;
        getSupportActionBar().setTitle(mangBaiHat.get(dangPhat).getTenBaiHat());
        textviewSongName.setText(mangBaiHat.get(dangPhat).getTenBaiHat());
        textViewCaSi.setText(mangBaiHat.get(dangPhat).getCaSy());
        Picasso.with(this).load(mangBaiHat.get(dangPhat).getHinhBaiHat()).transform(new BlurTransformation(this,30)).into(background);
        tim.setEnabled(true);
        tim.setImageResource(R.drawable.iconlove);
        btnPlay.setImageResource(R.drawable.ic_pause);
        cd_fragment.resumeAnimation();
        danhSachDangPhatFragment.notifyAdapterChange();

        PlayMp3(dangPhat);
    }

    private void PlayMp3(int position){
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mangBaiHat.get(position).getLinkBaiHat());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    if (isRepeat || mangBaiHat.size() == 1) {
                        isRepeat = true;
                        mediaPlayer.setLooping(isRepeat);
                    }
                    //sự kiện phát xong 1 bài hát thì phát bài tiếp theo (tùy chế độ)
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            if (isRepeat) {
                                //do nothing
                            } else if (isShuffle) {
                                dangPhat = new Random().nextInt(mangBaiHat.size());
                                chuyenBai(dangPhat);
                            } else {
                                dangPhat++;
                                if (dangPhat > mangBaiHat.size() - 1) dangPhat = 0;
                                chuyenBai(dangPhat);
                            }
                        }
                    });
                    XuLyThoiGian();
                }
            });
            mediaPlayer.prepareAsync();
        }catch (Exception ex){

        }
    }

    private void XuLyThoiGian(){
        cd_fragment.setImage(mangBaiHat.get(dangPhat).getHinhBaiHat());
        loiBaiHatFragment.setLyric(mangBaiHat.get(dangPhat).getLoiBaiHat());

        txtTimeTotal.setText(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getDuration())));
        seekBar.setMax(mediaPlayer.getDuration());
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                txtPlaying.setText(new SimpleDateFormat("mm:ss").format(new Date(mediaPlayer.getCurrentPosition())));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(runnable,1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        finish();
    }

}
