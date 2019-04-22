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

    public List<LockPasswordResponse> getOtp() {
        return otp;
    }

    public void setOtp(List<LockPasswordResponse> otp) {
        this.otp = otp;
    }

    public List<LockPasswordResponse> getPermanent() {
        return permanent;
    }

    public void setPermanent(List<LockPasswordResponse> permanent) {
        this.permanent = permanent;
    }
}
