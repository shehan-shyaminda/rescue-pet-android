package com.codelabs_coding.petrescue.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static final String PREF_NAME = "SHARED_RESCUE_PET";

    public static final String KEY_USER_OBJECT = "key_user_object";
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_USERID = "key_userid";
    public static final String KEY_IS_LOGGED_IN = "key_is_logged_in";
    public static final String KEY_AUTH_TOKEN = "key_auth_token";
    public static final String FCM_TOKEN = "key_fcm_token";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SpUtils(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveBoolean(String Key, boolean value) {

        editor.putBoolean(Key, value);
        editor.apply();
    }

    public boolean getBoolean(String Key) {
        return preferences.getBoolean(Key, false);
    }

    public void saveString(String Key, String value) {
        editor.putString(Key, value);
        editor.apply();
    }

    public String getString(String Key) {
        return preferences.getString(Key, "");
    }

    public void saveInt(String Key, int value) {
        editor.putInt(Key, value);
        editor.apply();
    }

    public int getInt(String Key) {
        return preferences.getInt(Key, 0);
    }

    public void clear() {
        editor.remove(SpUtils.KEY_USER_OBJECT);
        editor.remove(SpUtils.KEY_USERNAME);
        editor.remove(SpUtils.KEY_USERID);
        editor.remove(SpUtils.KEY_IS_LOGGED_IN);
        editor.remove(SpUtils.KEY_AUTH_TOKEN);
        editor.apply();
    }
}
