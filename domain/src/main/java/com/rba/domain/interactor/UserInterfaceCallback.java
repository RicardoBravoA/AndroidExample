package com.rba.domain.interactor;

import com.rba.domain.model.UserModel;

public interface UserInterfaceCallback {

    void onSuccess(UserModel userModel);

    void onError(String message);

}
