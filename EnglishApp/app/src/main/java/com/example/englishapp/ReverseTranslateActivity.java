package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class ReverseTranslateActivity extends AppCompatActivity {


    private ProgressBar progressBar;

    private Button bt_showAnswer;
    private int CurrentProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_translare);

        progressBar = findViewById(R.id.progress_bar);
        bt_showAnswer = findViewById(R.id.bt_showAnswer);

        bt_showAnswer.setOnClickListener(view -> {
            CurrentProgress = CurrentProgress + 10;
            progressBar.setProgress(CurrentProgress);
            progressBar.setMax(100);
        });
    }
}