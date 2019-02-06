package com.rba.data.datasource.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rba.data.model.UserResponse;
import com.rba.data.util.Constant;

public class SharedPreferencesUtil {

    public static void setUser(Context context, UserResponse userResponse) {
        clearUser(context);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.AVATAR, userResponse.getAvatarUrl());
        editor.putString(Constant.NAME, userResponse.getName());
        editor.putString(Constant.USER, userResponse.getLogin());
        editor.apply();
    }

    public static UserResponse getUser(Context context, String user) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        UserResponse userResponse = new UserResponse();

        String nickName = sharedPreferences.getString(Constant.USER, Constant.EMPTY);

        if (user.equalsIgnoreCase(nickName)) {
            userResponse.setLogin(nickName);
            userResponse.setAvatarUrl(sharedPreferences.getString(Constant.AVATAR, Constant.EMPTY));
            userResponse.setName(sharedPreferences.getString(Constant.NAME, Constant.EMPTY));
        }
        return userResponse;
    }

    public static void clearUser(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constant.AVATAR);
        editor.remove(Constant.NAME);
        editor.remove(Constant.USER);
        editor.apply();
    }

}
