package com.example.englishapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;
import com.example.englishapp.Service.DtbService;

public class login extends AppCompatActivity {
    private EditText etUser, etPass;
    private Button btnLogin,btnRegister;
    private CheckBox cbRemember;
    DtbService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(service.checkUser(etUser.getText().toString(),etPass.getText().toString()))
                    toastLog("successful");
                toastLog("failed");
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Register.class);
                finish();
                startActivity(intent);
            }
        });
    }
    public void initView(){
        etUser=findViewById(R.id.etUserLogin);
        etPass=findViewById(R.id.etPassLogin);
        btnLogin=findViewById(R.id.btLogin);
        cbRemember=findViewById(R.id.cbRemember);
        btnRegister=findViewById(R.id.btnRegister);
        service = new DtbService(this);
    }
    public void toastLog(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
    public boolean checkUser(String username,String password){
        return service.checkUser(username,password);
    }
}