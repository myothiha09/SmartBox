package com.team8303.events;

import com.team8303.smartbox.active_smartboxes.Smartbox;

import java.util.List;

public class ModelLockListEvent {
    private List<Smartbox> locks;

    public List<Smartbox> getLocks() {
        return locks;
    }

    public void setLocks(List<Smartbox> locks) {
        this.locks = locks;
    }
}
