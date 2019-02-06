package com.rba.data.api;

import com.rba.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users/{user}")
    Call<UserResponse> user(@Path("user") String user);

}
