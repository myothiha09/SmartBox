package com.team8303.events;

import com.team8303.api.model.LockHistoryResponse;

public class GetLockHistoryEvent {

    private LockHistoryResponse response;
    private boolean isSuccessful;

    public GetLockHistoryEvent(LockHistoryResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public LockHistoryResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
