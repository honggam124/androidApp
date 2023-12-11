package com.example.englishapp.View.setting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.englishapp.Model.User;
import com.example.englishapp.R;
import com.example.englishapp.Service.UserService;
import com.example.englishapp.Service.ImageService;
import com.example.englishapp.Service.SessionService;
import com.example.englishapp.View.main.Homepage;

public class account_setting extends AppCompatActivity {
    EditText etUsername, etEmail, etPhone, etGender, etBirth;
    ImageView ivAvatar;
    Button btDone;
    SessionService snService;
    ImageService imageService;
    UserService firebaseService;
    final int PICK_IMAGE_REQUEST = 100;
    private Uri imagePath;
    private Bitmap imageStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        init();
        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseService.updateUser(getDataUpdate(),snService.getId());
                gotoHomepage();
            }
        });
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseAvatar();
            }
        });
    }
    public void init(){firebaseService = new UserService();
        snService = new SessionService(this);
        firebaseService = new UserService();
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etGender = findViewById(R.id.et_gender);
        etBirth = findViewById(R.id.et_birth);
        ivAvatar = findViewById(R.id.iv_avt);
        imageService = new ImageService();
        btDone = findViewById(R.id.btDone);
        firebaseService.getUserById(snService.getId(), new UserService.UserByIdCallback() {
            @Override
            public void onUserRetrieved(User user) {
                if (user != null) {
                    System.out.println("User ne dm : "+user);
                    etUsername.setText(user.getUsername());
                    etEmail.setText(user.getEmail());
                    etPhone.setText(user.getPhone());
                    etGender.setText(user.getGender());
                    etBirth.setText(user.getBirth());
                    ImageService imageService = new ImageService();
                    Bitmap bitmap = user.getAvatar();
                    ivAvatar.setImageBitmap(bitmap);
                } else {
                    System.out.println("User not found");
                }
            }
            @Override
            public void onError(String errorMessage) {
                System.out.println("Error: " + errorMessage);
                // Handle error case
            }
        });

    }
    public User getDataUpdate(){
        User update = new User();
        update.setUsername(etUsername.getText().toString());
        update.setEmail(etEmail.getText().toString());
        update.setPhone(etPhone.getText().toString());
        update.setGender(etGender.getText().toString());
        update.setBirth(etBirth.getText().toString());
        update.setAvatar(imageService.convertImageViewToBitmap(ivAvatar));
        return update;
    }
    public void gotoHomepage(){
        Intent intent = new Intent(account_setting.this, Homepage.class);
        finish();
        startActivity(intent);
    }
    public void chooseAvatar(){
        try{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode== PICK_IMAGE_REQUEST && resultCode == RESULT_OK){
                imagePath = data.getData();
                imageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                ivAvatar.setImageBitmap(imageStore);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}