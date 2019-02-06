package com.rba.data.datasource.service;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rba.data.api.ApiManager;
import com.rba.data.api.ErrorUtil;
import com.rba.data.datasource.UserDataStore;
import com.rba.data.datasource.preference.SharedPreferencesUtil;
import com.rba.data.mapper.UserMapper;
import com.rba.data.model.ErrorResponse;
import com.rba.data.model.UserResponse;
import com.rba.data.util.Constant;
import com.rba.domain.repository.UserRepositoryCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceDataStore implements UserDataStore {

    private Context context;
    private UserMapper userMapper;

    public UserServiceDataStore(Context context) {
        this.context = context;
        userMapper = new UserMapper();
    }

    @Override
    public void getUser(String user, final UserRepositoryCallback userRepositoryCallback) {

        Call<UserResponse> call = ApiManager.apiManager().user(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null) {
                        SharedPreferencesUtil.setUser(context, userMapper.transformUserResponse(userResponse));
                        userRepositoryCallback.onSuccess(userResponse);
                    } else {
                        userRepositoryCallback.onError(Constant.ERROR_MESSAGE);
                    }
                } else {
                    ErrorResponse error = ErrorUtil.parseError(response);
                    userRepositoryCallback.onError(error.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                userRepositoryCallback.onError(t.getMessage() != null && !t.getMessage().isEmpty() ?
                        t.getMessage() : Constant.ERROR_MESSAGE);
            }
        });


    }
}
