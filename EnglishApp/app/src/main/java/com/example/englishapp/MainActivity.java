package com.example.englishapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.View.intro.intro1;
import com.example.englishapp.View.setting.account_setting;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Đống này để gọi tới activity code chức năng cho lẹ
        testIntent(MultipleChoicesActivity.class);


//        Comment dòng trên, chạy app.

//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, intro1.class);
//                finish();
//                startActivity(intent);
//            }
//        }, 3000); // 3 seconds
    }
    public void testIntent(Class act){
        Intent intent = new Intent(MainActivity.this, act);
        finish();
        startActivity(intent);
    }
}