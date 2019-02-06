package com.rba.androidexample.user;

import com.rba.domain.model.UserModel;

public interface UserView {

    void onUserSuccess(UserModel userModel);

    void onUserError(String error);

    void showLoading();

    void hideLoading();


}
