package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostLockPasswordArgs {

    @SerializedName("activeDays")
    private List<String> activeDays;
    @SerializedName("activeTimes")
    private List<String> activeTimes;
    @SerializedName("expiration")
    private int expiration;

    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private String type;

    public void setPostLockPasswordArgs(List<String> activeDays, List<String> activeTimes, int expiration,
                                     String password, String type) {
        this.activeDays = activeDays;
        this.activeTimes = activeTimes;
        this.expiration = expiration;
        this.password = password;
        this.type = type;
    }
}
