package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView username,email;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadingDialog loadingDialog=LoadingDialog.Companion.get(this).show();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.hide();
            }
        }, 5000);

        sharedPreferences=getApplicationContext().getSharedPreferences("login",Context.MODE_PRIVATE);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigation);
        username=navigationView.getHeaderView(0).findViewById(R.id.username);
        email=navigationView.getHeaderView(0).findViewById(R.id.email);

        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        username.setText(sharedPreferences.getString("username",null));
        email.setText(sharedPreferences.getString("email",null));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //x??? l?? s??? ki???n click tr??n navigation
                switch (item.getItemId()){
                    case R.id.nav_home:
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(MainActivity.this,TimKiemActivity.class));
                        break;
                    case R.id.nav_logout:
                        sharedPreferences.edit().putString("username",null).apply();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                        break;
                    case R.id.nav_exit:
                        DialogThoat();
                        break;
                    case R.id.nav_info:
                        Dialog("Th??ng Tin","???ng d???ng ???????c ph??t tri???n b???i: B??nh & Hi???u");
                        break;
                    case R.id.nav_change_password:
                        DoiMatKhau();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void DoiMatKhau() {
        View viewChangePassWord = LayoutInflater.from(this).inflate(R.layout.view_change_password,null);
        EditText oldP=viewChangePassWord.findViewById(R.id.inputOldPassword);
        EditText newP=viewChangePassWord.findViewById(R.id.inputNewPassword);
        EditText confirmP=viewChangePassWord.findViewById(R.id.inputConfirmNewPassword);

        //validate
        oldP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().length()>=6)
                    oldP.setError(null);
                else
                    oldP.setError("M???t kh???u ph???i l???n h??n 6 k?? t???");
            }
        });
        newP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().length()>=6)
                    newP.setError(null);
                else
                    newP.setError("M???t kh???u ph???i l???n h??n 6 k?? t???");
            }
        });
        confirmP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().length()>=6 && editable.toString().trim().equals(newP.getText().toString()))
                    confirmP.setError(null);
                else
                    confirmP.setError("X??c nh???n m???t kh???u kh??ng kh???p");
            }
        });
        //end validate

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("?????i m???t kh???u");
        dialog.setView(viewChangePassWord);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(oldP.getError()!=null || oldP.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"M???t kh???u c?? kh??ng h???p l???",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newP.getError()!=null || newP.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"M???t kh???u m???i kh??ng h???p l???",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(confirmP.getError()!=null || confirmP.getText().toString().trim().length()==0){
                    Toast.makeText(MainActivity.this,"X??c nh???n m???t kh???u m???i kh??ng h???p l???",Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailText=sharedPreferences.getString("email",null);
                String oldPass=oldP.getText().toString();
                String newPass=newP.getText().toString();

                LoadingDialog loadingDialog = LoadingDialog.Companion.get(MainActivity.this).show();
                APIService.getService().DoiMatKhau(emailText,oldPass,newPass).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        loadingDialog.hide();
                        if(response.body().equals("1")){
                            Toast.makeText(MainActivity.this,"?????i m???t kh???u th??nh c??ng!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(response.body().equals("0")){
                            Toast.makeText(MainActivity.this,"M???t kh???u c?? kh??ng ????ng",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(response.body().equals("2")){
                            Toast.makeText(MainActivity.this,"C?? l???i x???y ra! Vui l??ng th??? l???i sau",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loadingDialog.hide();
                        Toast.makeText(MainActivity.this,"Vui l??ng ki???m tra k???t n???i Internet v?? th??? l???i",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuTimKiem:
                startActivity(new Intent(this,TimKiemActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void DialogThoat(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("X??c Nh???n Tho??t");
        dialog.setMessage("B???n c?? mu???n tho??t kh??ng?");
        dialog.setPositiveButton("C??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        dialog.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void Dialog(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        DialogThoat();
    }
}