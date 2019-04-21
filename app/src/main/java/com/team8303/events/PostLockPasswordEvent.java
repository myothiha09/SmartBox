package com.team8303.events;

import com.team8303.api.model.LockPasswordResponse;

public class PostLockPasswordEvent {


    private LockPasswordResponse response;
    private boolean isSuccessful;

    public PostLockPasswordEvent(LockPasswordResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public LockPasswordResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
