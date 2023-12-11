package com.example.englishapp.Service;

import android.content.Context;
import android.widget.Toast;

import com.example.englishapp.Model.User;
import com.example.englishapp.Model.Vocab;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class VocabService {
    public interface VocabByIDCallback {
        void onUserRetrieved(Vocab vocab);
        void onError(String errorMessage);
    }
    public interface VocabMultiChoices {
        void onUserRetrieved(ArrayList<Vocab> vocabs,String en);
        void onError(String errorMessage);
    }
    public void addData(String en, String vi,String topic, Context context) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("vocab");
        Vocab newVocab = new Vocab();
        newVocab.setEn(en);
        newVocab.setVi(vi);
        newVocab.setTopic(topic);
        getVocabById(en, new VocabByIDCallback() {
            @Override
            public void onUserRetrieved(Vocab vocab) {
                if(en.equals(vocab.getEn())){
                    Toast.makeText(context, "Từ này đã tồn tại trong data", Toast.LENGTH_SHORT).show();
                }
                else{
                    usersRef.child(en).setValue(en);
                    usersRef.child(en).child("vi").setValue(vi);
                    usersRef.child(en).child("topic").setValue(topic);
                }
            }
            @Override
            public void onError(String errorMessage) {
                System.out.println("Error on getVocabById: VocabService");
            }
        });
    }
    public void getVocabById(String en, VocabService.VocabByIDCallback callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("vocab").child(en);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Vocab vocab = new Vocab();
                    vocab.setEn(en);
                    vocab.setTopic(dataSnapshot.child("topic").getValue(String.class));
                    vocab.setVi(dataSnapshot.child("vi").getValue(String.class));
                    if (vocab != null) {
                        callback.onUserRetrieved(vocab);
                    } else {
                        callback.onError("User data is null");
                    }
                } else {
                    callback.onError("User not found");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage()); // Pass error message via callback
            }
        });
    }
    public void getRandomWordsIncludingGiven(String givenWord, VocabService.VocabMultiChoices callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("vocab");
        ArrayList<Vocab> list = new ArrayList<>();

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Vocab vocab = new Vocab();
                            vocab.setEn(data.getKey());
                            vocab.setVi(data.child("vi").getValue(String.class));
                            vocab.setTopic(data.child("topic").getValue(String.class));
                            System.out.println(vocab.toString());
                            if (vocab.getEn().equals(givenWord) || list.size() < 4) {
                                list.add(vocab);
                            }
                    }
                    Collections.shuffle(list);
                    callback.onUserRetrieved(list, givenWord);
                } else {
                    callback.onError("Data not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }
    public void getWordsByTopic(String topic, VocabService.VocabMultiChoices callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("vocab");
        ArrayList<Vocab> wordsByTopic = new ArrayList<>();

        usersRef.orderByChild("topic").equalTo(topic).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Vocab vocab = new Vocab();
                        vocab.setEn(data.getKey());
                        vocab.setVi(data.child("vi").getValue(String.class));
                        vocab.setTopic(data.child("topic").getValue(String.class));
                        wordsByTopic.add(vocab);
                    }
                    callback.onUserRetrieved(wordsByTopic, topic);
                } else {
                    callback.onError("No words found for this topic");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });
    }

}
