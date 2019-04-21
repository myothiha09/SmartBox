package com.team8303.events;

import com.team8303.api.model.UserLockStatusResponse;

public class LockStatusEvent {
    private UserLockStatusResponse response;
    private boolean isSuccessful;

    public LockStatusEvent(UserLockStatusResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public UserLockStatusResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
