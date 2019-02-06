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

    public UserResponse transformUserResponse(UserResponse userResponse) {
        UserResponse userResponse1 = new UserResponse();
        if (userResponse == null) {
            return userResponse1;
        }

        userResponse1.setName(userResponse.getName() != null ? userResponse.getName() : EMPTY);
        userResponse1.setLogin(userResponse.getLogin() != null ? userResponse.getLogin() : EMPTY);
        userResponse1.setAvatarUrl(userResponse.getAvatarUrl() != null ? userResponse.getAvatarUrl() : EMPTY);

        return userResponse1;
    }

}
