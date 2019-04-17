package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LockPasswordsResponse {
    @SerializedName("otp")
    private List<LockPasswordResponse> otp;
    @SerializedName("permanent")
    private List<LockPasswordResponse> permanent;

    public void LockPasswordsResponse(List<LockPasswordResponse> otp, List<LockPasswordResponse> permanent) {
        this.otp = otp;
        this.permanent = permanent;
    }
}
