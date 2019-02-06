package com.rba.data.repository;

import com.rba.data.datasource.UserDataStore;
import com.rba.data.datasource.UserDataStoreFactory;
import com.rba.data.mapper.UserMapper;
import com.rba.data.model.UserResponse;
import com.rba.domain.interactor.UserInterfaceCallback;
import com.rba.domain.model.UserModel;
import com.rba.domain.repository.UserRepository;
import com.rba.domain.repository.UserRepositoryCallback;

public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserMapper userMapper;

    public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
        this.userMapper = new UserMapper();
    }

    @Override
    public void getUser(String user, final UserInterfaceCallback userInterfaceCallback) {
        UserDataStore userDataStore = userDataStoreFactory.create(UserDataStoreFactory.CLOUD);
        userDataStore.getUser(user, new UserRepositoryCallback() {
            @Override
            public void onSuccess(Object o) {
                UserResponse userResponse = (UserResponse) o;
                UserModel userModel = userMapper.transformResponse(userResponse);
                userInterfaceCallback.onSuccess(userModel);
            }

            @Override
            public void onError(String error) {
                userInterfaceCallback.onError(error);
            }
        });
    }
}
