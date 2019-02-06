package com.rba.domain.interactor;

import com.rba.domain.repository.UserRepository;

public class UserInteractor {

    private final UserRepository userRepository;

    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUser(String user, UserInterfaceCallback userInterfaceCallback) {
        userRepository.getUser(user, userInterfaceCallback);
    }

}
