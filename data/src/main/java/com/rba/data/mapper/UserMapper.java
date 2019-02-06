package com.rba.data.mapper;

import com.rba.data.model.UserResponse;
import com.rba.domain.model.UserModel;

public class UserMapper {

    private static final String EMPTY = "";

    public UserModel transformResponse(UserResponse userResponse) {
        UserModel userModel = new UserModel();
        if (userResponse == null) {
            return userModel;
        }

        userModel.setName(userResponse.getName() != null ? userResponse.getName() : EMPTY);
        userModel.setLogin(userResponse.getLogin() != null ? userResponse.getLogin() : EMPTY);
        userModel.setAvatarUrl(userResponse.getAvatarUrl() != null ? userResponse.getAvatarUrl() : EMPTY);

        return userModel;
    }

}
