package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("displayName")
    private String displayName;
    @SerializedName("email")
    private String email;
    @SerializedName("id")
    private String id;

    public UserResponse(String displayName, String email, String id) {
        this.displayName = displayName;
        this.email = email;
        this.id = id;
    }
}
