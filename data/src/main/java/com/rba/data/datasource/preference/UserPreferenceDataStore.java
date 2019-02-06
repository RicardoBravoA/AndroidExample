package com.rba.data.datasource.preference;

import android.content.Context;

import com.rba.data.datasource.UserDataStore;
import com.rba.data.model.UserResponse;
import com.rba.data.util.Constant;
import com.rba.domain.repository.UserRepositoryCallback;

public class UserPreferenceDataStore implements UserDataStore {

    private final Context context;

    public UserPreferenceDataStore(Context context) {
        this.context = context;
    }


    @Override
    public void getUser(String user, UserRepositoryCallback userRepositoryCallback) {

        UserResponse userResponse = SharedPreferencesUtil.getUser(context, user);

        if (userResponse != null && userResponse.getName() != null && !userResponse.getName().isEmpty()) {
            userRepositoryCallback.onSuccess(userResponse);
        } else {
            userRepositoryCallback.onError(Constant.USER_NOT_FOUND);
        }

    }
}
