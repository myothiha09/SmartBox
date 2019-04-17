package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LockPasswordResponse {

    @SerializedName("activeDays")
    private List<String> activeDays;
    @SerializedName("activeTimes")
    private List<String> activeTimes;
    @SerializedName("createdAt")
    private Long createdAt;
    @SerializedName("expiration")
    private Long expiration;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;

    public void LockPasswordResponse(List<String> activeDays, List<String> activeTimes, Long createdAt, Long expiration,
                                     String id, String type) {
        this.activeDays = activeDays;
        this.activeTimes = activeTimes;
        this.createdAt = createdAt;
        this.expiration = expiration;
        this.id = id;
        this.type = type;
    }


}
