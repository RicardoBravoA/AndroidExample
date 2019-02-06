package com.rba.domain.repository;

import com.rba.domain.interactor.UserInterfaceCallback;

public interface UserRepository {

    void getUser(String user, UserInterfaceCallback userInterfaceCallback);

}
