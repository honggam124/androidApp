package com.example.englishapp.View.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;
import com.example.englishapp.Service.UserService;
import com.example.englishapp.Service.SessionService;
import com.example.englishapp.View.main.Homepage;

public class login extends AppCompatActivity {
    private EditText etUser, etPass;
    private Button btnLogin;
    private TextView tvRegister;
    private CheckBox cbRemember;
    SessionService snService;
    UserService firebaseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.checkUser(etUser.getText().toString(), etPass.getText().toString(), new UserService.UserCallback() {
                    @Override
                    public void onLoginResult(boolean success,String id) {
                        if (success) {
                            snService.createLoginSession(id);
                            gotoHomepage();
                        } else {
                            toastLog("Wrong email or password!");
                        }
                    }
                });
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Register.class);
                finish();
                startActivity(intent);
            }
        });
    }

    public void initView(){
        snService = new SessionService(this);
        firebaseService = new UserService();
        etUser=findViewById(R.id.etUserLogin);
        etPass=findViewById(R.id.etPassLogin);
        btnLogin=findViewById(R.id.btLogin);
        cbRemember=findViewById(R.id.cbRemember);
        tvRegister=findViewById(R.id.tv_register);
    }
    public void toastLog(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
    public void gotoHomepage(){
        Intent intent = new Intent(login.this, Homepage.class);
        finish();
        startActivity(intent);
    }

}