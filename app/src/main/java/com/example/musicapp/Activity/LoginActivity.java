package com.example.musicapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Model.User;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;
import com.github.loadingview.LoadingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    EditText email,matkhau;
    Button login;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=getApplicationContext().getSharedPreferences("login",Context.MODE_PRIVATE);
        textView = findViewById(R.id.textViewSignUp);
        email=findViewById(R.id.inputEmail);
        matkhau=findViewById(R.id.inputPassword);
        login=findViewById(R.id.btnLogin);

        if (sharedPreferences.getString("username",null)!=null && sharedPreferences.getString("email",null)!=null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getError()!=null || email.getText().toString().trim().length()==0){
                    email.requestFocus();
                    return;
                }
                if(matkhau.getError()!=null || matkhau.getText().toString().trim().length()==0){
                    matkhau.requestFocus();
                    return;
                }

                LoadingDialog loadingDialog = LoadingDialog.Companion.get(LoginActivity.this).show();
                APIService.getService().DangNhap(email.getText().toString(),matkhau.getText().toString()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        loadingDialog.hide();

                        User user=(User)response.body();
                        if(user.getStatus().equals("ok")){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("username",user.getFullname());
                            editor.putString("email",user.getEmail());
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                        }else
                            Dialog("Th??ng b??o","Email ho???c m???t kh???u kh??ng ch??nh x??c");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        loadingDialog.hide();
                        Dialog("Kh??ng th??? k???t n???i ?????n m??y ch???","Vui l??ng ki???m tra k???t n???i Internet v?? th??? l???i");
                    }
                });
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().matches("^(.+)@(.+)$"))
                    email.setError(null);
                else
                    email.setError("Email kh??ng h???p l???");
            }
        });

        matkhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if((s.toString().trim().length()>=6))
                    matkhau.setError(null);
                else
                    matkhau.setError("M???t kh???u ph???i l???n h??n 6 k?? t???");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
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
}