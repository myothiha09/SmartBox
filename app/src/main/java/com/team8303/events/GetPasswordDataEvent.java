package com.team8303.events;

import com.team8303.api.model.LockPasswordsResponse;

public class GetPasswordDataEvent {

    private LockPasswordsResponse response;
    private boolean isSuccessful;

    public GetPasswordDataEvent(LockPasswordsResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public LockPasswordsResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
