package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;
import com.team8303.events.LockEvent;

import java.util.List;

public class LockHistoryResponse {

    @SerializedName("events")
    private List<LockEvent> events;

    public void setLockHistoryResponse(List<LockEvent> events) {
        this.events = events;
    }

    public List<LockEvent> getLockHistoryResponse() {
        return events;
    }
}
