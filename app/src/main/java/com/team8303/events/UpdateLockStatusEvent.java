package com.team8303.events;

import com.team8303.api.model.PutUserLockStatusResponse;
import com.team8303.api.model.PutLockStatusArgs;

public class UpdateLockStatusEvent {
    //private PutUserLockStatusResponse response;
    private PutLockStatusArgs response;
    private boolean isSuccessful;

    public UpdateLockStatusEvent(PutLockStatusArgs response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    public PutLockStatusArgs getResponse() {
        return response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
