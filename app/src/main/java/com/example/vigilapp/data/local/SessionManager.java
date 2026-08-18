package com.example.vigilapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vigilapp.data.model.usuario;
import com.google.gson.Gson;

public class SessionManager {
    private static final String PREF_NAME = "VigilAppPrefs";
    private static final String KEY_TOKEN = "auth_token";
    private static final String KEY_USER = "user_data";
    private static final String KEY_LOGGED_IN = "is_logged_in";

    private SharedPreferences prefs;
    private Gson gson;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveToken(String token) {
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public void saveUser(usuario usuario) {
        String json = gson.toJson(usuario);
        prefs.edit()
                .putString(KEY_USER, json)
                .putBoolean(KEY_LOGGED_IN, true)
                .apply();
    }

    public usuario getUser() {
        String json = prefs.getString(KEY_USER, null);
        if (json == null) return null;
        return gson.fromJson(json,usuario.class);
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false) && getToken() != null;
    }

    public void clear() {
        prefs.edit().clear().apply();
    }

    public String getAuthHeader() {
        String token = getToken();
        return token != null ? "Bearer " + token : null;
    }
}