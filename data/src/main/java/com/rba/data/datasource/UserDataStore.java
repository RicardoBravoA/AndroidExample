package com.rba.data.datasource;

import com.rba.domain.repository.UserRepositoryCallback;

public interface UserDataStore {

    void getUser(String user, UserRepositoryCallback userRepositoryCallback);

}
