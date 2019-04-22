package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

public class PutLockStatusArgs {

    @SerializedName("password")
    private String password;
    @SerializedName("status")
    private String status;

    public void setLockStatusArgs(String password, String status) {
        this.password = password;
        this.status = status;
    }
}