package com.example.englishapp.Service;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.example.englishapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserService {// Get a reference to the Firebase Realtime Database
    private ImageService imageService = new ImageService();
    public interface UserCallback {
        void onLoginResult(boolean success,String id);
    }
    public interface UserByIdCallback {
        void onUserRetrieved(User user);
        void onError(String errorMessage);
    }

    public void addData(String username, String pass,String email) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
            User newUser = new User();
            newUser.setPassword(pass);
            newUser.setEmail(email);
            newUser.setUsername(username);
            String newUserId = "user" + (System.currentTimeMillis() / 1000);
            usersRef.child(newUserId).setValue(newUser);
    }
    public void checkUser(String email, String pass, UserCallback callback) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean LOGIN_FLAG = false;
                String id="null";
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String userEmail = data.child("email").getValue(String.class);
                        String userPass = data.child("password").getValue(String.class);
                        if (userEmail != null && userEmail.equals(email) && userPass != null && userPass.equals(pass)) {
                            LOGIN_FLAG = true;
                            id = data.getKey();
                            break;
                        }
                    }
                }
                callback.onLoginResult(LOGIN_FLAG,id);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Database Error: " + error.getMessage());
                callback.onLoginResult(false,null); // Notify callback about failure due to error
            }
        });
    }
    public void updateUser(User user,String id){
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        Bitmap imageToBitmap = user.getAvatar();
        String imageAsBase64 = imageService.convertBitmapToBase64(imageToBitmap);

        Map<String, Object> updatedUserData = new HashMap<>();
        updatedUserData.put("username", user.getUsername());
        updatedUserData.put("phone", user.getPhone());
        updatedUserData.put("avatar", imageAsBase64);
        updatedUserData.put("birth", user.getBirth());
        updatedUserData.put("gender", user.getGender());
        usersRef.child(id).updateChildren(updatedUserData)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("User data updated successfully");
                })
                .addOnFailureListener(e -> {
                    // Failed to update data
                    System.err.println("Error updating user data: " + e.getMessage());
                });
    }
    public void getUserById(String userId, UserByIdCallback callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = new User();
                    user.setUsername(dataSnapshot.child("username").getValue(String.class));
                    user.setEmail(dataSnapshot.child("email").getValue(String.class));
                    user.setPassword(dataSnapshot.child("password").getValue(String.class));
                    user.setBirth(dataSnapshot.child("birth").getValue(String.class));
                    user.setPhone(dataSnapshot.child("phone").getValue(String.class));
                    user.setGender(dataSnapshot.child("gender").getValue(String.class));
                    if(!dataSnapshot.child("avatar").getValue(String.class).isEmpty()){
                        user.setAvatar(imageService.convertBase64ToBitmap(dataSnapshot.child("avatar").getValue(String.class)));
                    }
                    if (user != null) {
                        callback.onUserRetrieved(user); // Pass retrieved user data via callback
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
}
