package com.example.englishapp.View.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.englishapp.R;

public class FlashcardActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    private Button bt_FlipCard;
    private int CurrentProgress = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        progressBar = findViewById(R.id.progress_bar);
        bt_FlipCard = findViewById(R.id.bt_FlipCard);

        bt_FlipCard.setOnClickListener(view -> {
            CurrentProgress = CurrentProgress + 10;
            progressBar.setProgress(CurrentProgress);
            progressBar.setMax(100);
        });
    }
}