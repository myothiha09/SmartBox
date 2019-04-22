package com.team8303.events;

import com.team8303.api.model.NoResponse;

public class DeleteLockPasswordEvent {

    private NoResponse response;
    private boolean isSuccessful;

    public DeleteLockPasswordEvent(NoResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public NoResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
