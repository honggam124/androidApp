package com.example.englishapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.R;

public class intro1 extends AppCompatActivity {
    Button btnStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}