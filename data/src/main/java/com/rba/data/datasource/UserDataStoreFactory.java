package com.rba.data.datasource;

import android.content.Context;

import com.rba.data.datasource.preference.UserPreferenceDataStore;
import com.rba.data.datasource.service.UserServiceDataStore;
import com.rba.data.util.NetworkUtil;

public class UserDataStoreFactory {

    public static final int CLOUD = 0;
    public static final int PREFERENCES = 1;

    private final Context context;

    public UserDataStoreFactory(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        this.context = context;
    }

    public UserDataStore create() {
        UserDataStore userDataStore;

        int value = NetworkUtil.isInternet(context) ? CLOUD : PREFERENCES;

        if (CLOUD == value) {
            userDataStore = new UserServiceDataStore(context);
        } else {
            userDataStore = new UserPreferenceDataStore(context);
        }

        return userDataStore;
    }

}
