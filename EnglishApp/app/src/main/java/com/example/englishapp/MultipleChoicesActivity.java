package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishapp.Model.Vocab;
import com.example.englishapp.Service.VocabService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MultipleChoicesActivity extends AppCompatActivity  implements View.OnClickListener {

    private String choice;
    private Button bt_showAnswer,btA1,btA2,btA3,btA4;
    private TextView tvVocab,tvTopic;
    private ArrayList<Button> listBtn;
    private int currentProgress = 0;
    private VocabService vocabService;
    private Vocab result;
    private ArrayList<Vocab> listWordbyTopic;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choices);
        init();
        initQuestion();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bt_answer1:
                clickEvent(btA1);
                break;
            case R.id.bt_answer2:
                clickEvent(btA2);
                break;
            case R.id.bt_answer3:
                clickEvent(btA3);
                break;
            case R.id.bt_answer4:
                clickEvent(btA4);
                break;
            case R.id.bt_showAnswer:
                System.out.println("choosen: "+choice +"||"+ result);
                if(choice.equals(result.getVi())){
                    Toast.makeText(this, "Correct",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "False",
                            Toast.LENGTH_LONG).show();
                }
                currentProgress++;
                if (currentProgress < listWordbyTopic.size()) {
                    loadNextQuestion();
                } else {
                }
                break;
        }
    }
    public void clickEvent(Button btn){
        btn.setBackgroundTintList(getResources().getColorStateList(R.color.blueColor));
        for(Button i: listBtn){
            if(i!=btn)
                i.setBackgroundTintList(getResources().getColorStateList(R.color.darkBlue));
        }
        choice = btn.getText().toString();
    }
    public void init(){
        vocabService = new VocabService();
        listBtn = new ArrayList<>();
        listWordbyTopic = new ArrayList<>();
        bt_showAnswer = findViewById(R.id.bt_showAnswer);
        btA1 = findViewById(R.id.bt_answer1);
        btA2 = findViewById(R.id.bt_answer2);
        btA3 = findViewById(R.id.bt_answer3);
        btA4 = findViewById(R.id.bt_answer4);
        listBtn.add(btA1);
        listBtn.add(btA2);
        listBtn.add(btA3);
        listBtn.add(btA4);
        bt_showAnswer.setOnClickListener(this);
        btA1.setOnClickListener(this);
        btA2.setOnClickListener(this);
        btA3.setOnClickListener(this);
        btA4.setOnClickListener(this);
        tvVocab = findViewById(R.id.tv_word);
        tvTopic = findViewById(R.id.tv_tittleTopicMulti);
        initListWordByTopic(tvVocab.getText().toString().toLowerCase());
    }
    public void initListWordByTopic(String topic){
        vocabService.getWordsByTopic(topic, new VocabService.VocabMultiChoices() {
            @Override
            public void onUserRetrieved(ArrayList<Vocab> vocabs, String en) {
                listWordbyTopic.addAll(vocabs);
            }
            @Override
            public void onError(String errorMessage) {

            }
        });
    }
    public void initQuestion(){
        if(currentProgress==0){
            vocabService.getWordsByTopic("fruit", new VocabService.VocabMultiChoices() {
                @Override
                public void onUserRetrieved(ArrayList<Vocab> vocabs, String en) {
                    vocabService.getRandomWordsIncludingGiven(vocabs.get(0).getEn(), new VocabService.VocabMultiChoices() {
                        @Override
                        public void onUserRetrieved(ArrayList<Vocab> vocabs, String en) {
                            for(Vocab i:vocabs){
                                if(tvVocab.getText().toString().toLowerCase().equals(i.getEn().toLowerCase()))
                                    result = i;
                            }
                            btA1.setText(vocabs.get(0).getVi());
                            btA2.setText(vocabs.get(1).getVi());
                            btA3.setText(vocabs.get(2).getVi());
                            btA4.setText(vocabs.get(3).getVi());
                        }

                        @Override
                        public void onError(String errorMessage) {
                            System.out.println("Error: " + errorMessage);
                        }
                    });

                }
                @Override
                public void onError(String errorMessage) {
                }
            });
        }
        else{
            vocabService.getRandomWordsIncludingGiven(listWordbyTopic.get(currentProgress).getEn(), new VocabService.VocabMultiChoices() {
                @Override
                public void onUserRetrieved(ArrayList<Vocab> vocabs, String en) {
                    for(Vocab i:vocabs){
                        if(tvVocab.getText().toString().toLowerCase().equals(i.getEn().toLowerCase()))
                            result = i;
                    }
                    btA1.setText(vocabs.get(0).getVi());
                    btA2.setText(vocabs.get(1).getVi());
                    btA3.setText(vocabs.get(2).getVi());
                    btA4.setText(vocabs.get(3).getVi());
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println("Error: " + errorMessage);
                }
            });

        }
    }
    public void loadNextQuestion() {
        clearButtonBackgrounds();
        initQuestion();
    }

    public void clearButtonBackgrounds() {
        for (Button i : listBtn) {
            i.setBackgroundTintList(getResources().getColorStateList(R.color.darkBlue));
        }
        choice = ""; // Reset choice variable
    }

}