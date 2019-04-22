package com.team8303.events;

import com.team8303.model.Passcode;
import com.team8303.model.PasscodeType;

import java.util.List;

public class ModelPasscodeListEvent {

    private List<Passcode> requestedPasscodes;
    private PasscodeType typeRequested;

    public List<Passcode> getRequestedPasscodes() {
        return requestedPasscodes;
    }

    public void setRequestedPasscodes(List<Passcode> requestedPasscodes) {
        this.requestedPasscodes = requestedPasscodes;
    }

    public PasscodeType getTypeRequested() {
        return typeRequested;
    }

    public void setTypeRequested(PasscodeType typeRequested) {
        this.typeRequested = typeRequested;
    }

}
