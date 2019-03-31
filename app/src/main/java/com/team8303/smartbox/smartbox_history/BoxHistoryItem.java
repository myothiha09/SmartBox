package com.team8303.smartbox.smartbox_history;

import com.saber.stickyheader.stickyData.StickyMainData;
import com.team8303.model.Passcode;

import java.util.concurrent.locks.Lock;

public class BoxHistoryItem implements StickyMainData {
    private String date;
    private String time;
    private String unlocker;
    private LockStatus lockStatus;
    private Passcode passcode;
    boolean expanded = false;
    public BoxHistoryItem() {
        unlocker = "Unlocker " + (int) (Math.random() * 99);
    }
    public BoxHistoryItem(String date, String time, LockStatus lockStatus, String unlocker, Passcode passcode) {
        this.date = date;
        this.time = time;
        this.lockStatus = lockStatus;
        this.unlocker = unlocker;
        this.passcode = passcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnlocker() {
        return unlocker;
    }

    public void setUnlocker(String unlocker) {
        this.unlocker = unlocker;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Passcode getPasscode() {
        return passcode;
    }

    public void setPasscode(Passcode passcode) {
        this.passcode = passcode;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void toggleExpanded() {
        expanded = !expanded;
    }
    public boolean isExpanded() {
        return expanded;
    }
}
