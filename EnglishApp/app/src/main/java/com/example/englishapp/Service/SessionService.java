package com.example.englishapp.Service;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionService {
    private static final String PREF_NAME = "LoginSession";
    private static final String KEY_ID = "ID";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public SessionService(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }
    public void createLoginSession(String email) {
        editor.putString(KEY_ID, email);
        editor.apply();
    }
    public String getId() {
        return pref.getString(KEY_ID, null);
    }
    public void logout() {
        editor.clear();
        editor.apply();
    }
    public boolean isLoggedIn() {
        return pref.contains(KEY_ID);
    }
}
