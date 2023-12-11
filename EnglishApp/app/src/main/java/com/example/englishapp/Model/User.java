package com.example.englishapp.Model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private String birth;
    private Bitmap avatar;
    public User(){

    }
    public User(String username,String password,String email,String phone, String gender,String birth,Bitmap avatar){
        this.username=username;
        this.password=password;
        this.phone = phone;
        this.email=email;
        this.gender=gender;
        this.birth=birth;
        this.avatar=avatar;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @NonNull
    @Override
    public String toString() {
        return "User: " + id +", "+ username+", "+ password+", "+email+", "+phone+", "+gender+", "+birth;
    }
}
