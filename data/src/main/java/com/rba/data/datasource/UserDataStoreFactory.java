package com.rba.data.datasource;

import android.content.Context;

import com.rba.data.datasource.preference.UserPreferenceDataStore;
import com.rba.data.datasource.service.UserServiceDataStore;

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

    public UserDataStore create(int dataSource) {
        UserDataStore userDataStore = null;

        if (CLOUD == dataSource) {
            userDataStore = new UserServiceDataStore();
        } else {
            userDataStore = new UserPreferenceDataStore(context);
        }

        return userDataStore;
    }

}
