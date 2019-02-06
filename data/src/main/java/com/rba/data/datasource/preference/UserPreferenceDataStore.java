package com.rba.data.datasource.preference;

import android.content.Context;

import com.rba.data.datasource.UserDataStore;
import com.rba.domain.repository.UserRepositoryCallback;

public class UserPreferenceDataStore implements UserDataStore {

    private final Context context;

    public UserPreferenceDataStore(Context context) {
        this.context = context;
    }


    @Override
    public void getUser(String user, UserRepositoryCallback userRepositoryCallback) {

    }
}
