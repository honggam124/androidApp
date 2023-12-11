package com.example.englishapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.englishapp.R;
import com.example.englishapp.Service.DtbService;

public class Register extends AppCompatActivity {
    private EditText etName,etEmail,etPass,etRepass;
    private Button btnSignup, btnLogin;
    DtbService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((etPass.getText().toString()).equals(etRepass.getText().toString())){
                    service.insertData(etName.getText().toString(),etPass.getText().toString(),etEmail.getText().toString());
                    gotoLogin();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
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
        btnLogin = findViewById(R.id.btnLogin);
        service = new DtbService(this);
    }
}