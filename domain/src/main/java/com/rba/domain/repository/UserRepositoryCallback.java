package com.rba.domain.repository;

public interface UserRepositoryCallback<T> {

    void onSuccess(T t);

    void onError(String error);

}
