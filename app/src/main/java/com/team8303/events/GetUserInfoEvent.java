package com.team8303.events;

import com.team8303.api.model.UserResponse;

public class GetUserInfoEvent {

    private UserResponse response;
    private boolean isSuccessful;

    public GetUserInfoEvent(UserResponse response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public UserResponse getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
