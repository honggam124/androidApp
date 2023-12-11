package com.example.englishapp.View.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;
import com.example.englishapp.Service.SessionService;
import com.example.englishapp.View.main.Homepage;

public class intro1 extends AppCompatActivity {
    Button btnStarted;
    SessionService snService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        snService = new SessionService(this);
        if(snService.isLoggedIn())
            gotoHomepage();
        setContentView(R.layout.activity_intro1);
        btnStarted=findViewById(R.id.btnStarted);
        btnStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(intro1.this, login.class);
                finish();
                startActivity(intent);
            }
        });
    }
    public void gotoHomepage(){
        Intent intent = new Intent(intro1.this, Homepage.class);
        finish();
        startActivity(intent);
    }
}