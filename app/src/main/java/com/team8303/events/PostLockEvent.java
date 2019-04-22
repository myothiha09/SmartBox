package com.team8303.events;

import com.team8303.api.model.UserLockResponse;

public class PostLockEvent {
    private UserLockResponse response;
    private boolean isSuccessful;

    public PostLockEvent(UserLockResponse response, boolean isSuccessful) {
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
