package com.example.englishapp.View.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.englishapp.R;
import com.example.englishapp.View.Add.AddTopicFragment;
import com.example.englishapp.View.CommunicationFragment;
import com.example.englishapp.View.library.LibraryFragment;
import com.example.englishapp.View.setting.SettingFragment;
import com.example.englishapp.databinding.ActivityHomepageBinding;

public class Homepage extends AppCompatActivity {
    ActivityHomepageBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

//        binding.btFab.setOnClickListener(view -> {
//            replaceFragment(new AddTopicFragment());
//        });

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.menu_library:
                    replaceFragment(new LibraryFragment());
                    break;

//                case R.id.menu_add:
//                    replaceFragment(new AddFlashcardFragment());
//                    break;

                case R.id.menu_communication:
                    replaceFragment(new CommunicationFragment());
                    break;

                case R.id.menu_setting:
                    replaceFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
