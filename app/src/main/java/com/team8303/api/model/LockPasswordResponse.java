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

    public List<String> getActiveDays() {
        return activeDays;
    }

    public void setActiveDays(List<String> activeDays) {
        this.activeDays = activeDays;
    }

    public List<String> getActiveTimes() {
        return activeTimes;
    }

    public void setActiveTimes(List<String> activeTimes) {
        this.activeTimes = activeTimes;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
