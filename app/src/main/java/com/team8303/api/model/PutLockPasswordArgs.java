package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PutLockPasswordArgs {

    @SerializedName("activeDays")
    private List<String> activeDays;
    @SerializedName("activeTimes")
    private List<String> activeTimes;
    @SerializedName("expiration")
    private int expiration;
    @SerializedName("password")
    private String password;

    public void setLockPasswordArgs(List<String> activeDays, List<String> activeTimes, int expiration, String password) {
        this.activeDays = activeDays;
        this.activeTimes = activeTimes;
        this.expiration = expiration;
        this.password = password;
    }
}
