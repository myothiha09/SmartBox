package com.team8303.events;

import com.team8303.api.model.UserLockResponse;

public class LockListEvent {
    private UserLockResponse response;
    private boolean isSuccessful;

    public LockListEvent(UserLockResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public UserLockResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
