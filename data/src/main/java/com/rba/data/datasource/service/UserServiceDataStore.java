package com.rba.data.datasource.service;

import com.rba.data.api.ApiManager;
import com.rba.data.datasource.UserDataStore;
import com.rba.data.model.UserResponse;
import com.rba.data.util.Constant;
import com.rba.domain.repository.UserRepositoryCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceDataStore implements UserDataStore {

    public UserServiceDataStore() {
        //Do nothing
    }


    @Override
    public void getUser(String user, final UserRepositoryCallback userRepositoryCallback) {

        Call<UserResponse> call = ApiManager.apiManager().user(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null) {
                        userRepositoryCallback.onSuccess(userResponse);
                    } else {
                        userRepositoryCallback.onError(Constant.ERROR_MESSAGE);
                    }
                } else {
                    userRepositoryCallback.onError(Constant.ERROR_MESSAGE);
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
