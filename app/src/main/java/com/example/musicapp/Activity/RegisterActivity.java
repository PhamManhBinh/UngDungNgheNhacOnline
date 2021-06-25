package com.example.musicapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.github.loadingview.LoadingDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView textView;

    private EditText inputFullname, inputEmail, inputPassword, inputConformPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ
        textView = findViewById(R.id.alreadyHaveAccount);
        inputFullname = findViewById(R.id.inputFullName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConformPassword = findViewById(R.id.inputConformPassword);
        register=findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputFullname.getError()!=null || inputFullname.getText().toString().trim().length()==0){
                    inputFullname.requestFocus();
                    return;
                }
                if(inputEmail.getError()!=null || inputEmail.getText().toString().trim().length()==0){
                    inputEmail.requestFocus();
                    return;
                }
                if(inputPassword.getError()!=null || inputPassword.getText().toString().trim().length()==0){
                    inputPassword.requestFocus();
                    return;
                }
                if(inputConformPassword.getError()!=null || inputConformPassword.getText().toString().trim().length()==0){
                    inputConformPassword.requestFocus();
                    return;
                }

                LoadingDialog loadingDialog=LoadingDialog.Companion.get(RegisterActivity.this).show();
                APIService.getService().DangKy(inputEmail.getText().toString(),inputPassword.getText().toString(),inputFullname.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        loadingDialog.hide();

                        if(response.body().toString().equals("success")){
                            Dialog("Chúc mừng","Bạn đã đăng ký thành công!");
                            //Toast.makeText(RegisterActivity.this,"Đăng ký thành công !",Toast.LENGTH_SHORT).show();
                        }else{
                            Dialog("Thông báo","Tài khoản đã tồn tại!");
                            //Toast.makeText(RegisterActivity.this,"Đăng ký thất bại !",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        loadingDialog.hide();
                        Dialog("Không thể kết nối đến máy chủ","Vui lòng kiểm tra kết nối Internet và thử lại");
                    }
                });
            }
        });

        inputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //pattern
                if(s.toString().trim().matches("^(.+)@(.+)$"))
                    inputEmail.setError(null);
                else
                    inputEmail.setError("Email không hợp lệ");
            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>=6)
                    inputPassword.setError(null);
                else
                    inputPassword.setError("Mật khẩu phải lớn hơn 6 ký tự");
            }
        });

        inputConformPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length()>=6 && s.toString().trim().equals(inputPassword.getText().toString()))
                    inputConformPassword.setError(null);
                else
                    inputConformPassword.setError("Xác nhận mật khẩu không khớp");
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
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