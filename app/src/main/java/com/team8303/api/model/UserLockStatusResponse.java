package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

public class UserLockStatusResponse {


    @SerializedName("status")
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
