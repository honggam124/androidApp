package com.example.englishapp.View.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.englishapp.R;
import com.example.englishapp.Service.UserService;

public class Register extends AppCompatActivity {
    private EditText etName,etEmail,etPass,etRepass;
    private Button btnSignup;
    private TextView tvLogin;
    UserService firebaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((etPass.getText().toString()).equals(etRepass.getText().toString())){
                    firebaseService.addData(etName.getText().toString(),etPass.getText().toString(),etEmail.getText().toString());
                    gotoLogin();
                }
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });
    }
    public void gotoLogin(){
        Intent intent = new Intent(Register.this, login.class);
        finish();
        startActivity(intent);
    }
    public void initView(){
        etName= findViewById(R.id.etUserLogin);
        etEmail= findViewById(R.id.etEmailLogin);
        etPass= findViewById(R.id.etPassLogin);
        etRepass= findViewById(R.id.etCfPassLogin);
        btnSignup = findViewById(R.id.btnSignup);
        tvLogin = findViewById(R.id.tv_login);
        firebaseService = new UserService();
    }
}