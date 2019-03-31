package com.team8303.smartbox.smartbox_history;

public enum LockStatus {
    LOCKED("Locked"), UNLOCKED("Unlocked"), ATTEMPTED_UNLOCK("Attempted Unlock");

    private final String LOCK;
    LockStatus(String LOCK) {
        this.LOCK = LOCK;
    }
}
