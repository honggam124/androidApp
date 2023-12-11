package com.example.englishapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


public class InfoPopularTopicFragment extends Fragment {
    RadioButton rb_info, rb_words;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_info_popular_topic, container, false);
        rb_info = rootview.findViewById(R.id.rb_info);
        rb_words = rootview.findViewById(R.id.rb_words);
        boolean isSelected = ((RadioButton) rootview).isChecked();
        rb_info.setOnClickListener(view ->{
            if(isSelected){
                rb_info.setTextColor(Color.BLUE);
                rb_words.setTextColor(Color.WHITE);
            }
        });

        rb_words.setOnClickListener(view ->{
            if(isSelected){
                rb_words.setTextColor(Color.BLUE);
                rb_info.setTextColor(Color.WHITE);
            }
        });
        return rootview;
    }
}