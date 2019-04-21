package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

public class PutUserLockStatusResponse {

    @SerializedName("providedPasswordDisabled")
    private Boolean providedPasswordDisabled;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }
}
