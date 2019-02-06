package com.rba.androidexample.user;

import com.rba.domain.interactor.UserInteractor;
import com.rba.domain.interactor.UserInterfaceCallback;
import com.rba.domain.model.UserModel;

public class UserPresenter implements UserInterfaceCallback {

    private UserView userView;

    private final UserInteractor userInteractor;

    public UserPresenter(UserInteractor userInteractor) {
        this.userInteractor = userInteractor;
    }

    public void getUser(String user) {
        this.userView.showLoading();
        userInteractor.getUser(user, this);
    }

    public void attach(UserView userView) {
        this.userView = userView;
    }

    public void detach() {
        this.userView = null;
    }


    @Override
    public void onSuccess(UserModel userModel) {
        this.userView.hideLoading();
        this.userView.onUserSuccess(userModel);

    }

    @Override
    public void onError(String message) {
        this.userView.hideLoading();
        this.userView.onUserError(message);
    }
}
