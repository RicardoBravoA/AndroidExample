package com.rba.data.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
