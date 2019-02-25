package com.team8303.model;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public class Passcode {
    String name;
    int usedCount;
    String validPeriod;
    String creationTime;
    boolean enabled;

    public Passcode(String name, int usedCount, String validPeriod, String creationTime, boolean enabled) {
        this.name = name;
        this.usedCount = usedCount;
        this.validPeriod = validPeriod;
        this.creationTime = creationTime;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public String getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(String validPeriod) {
        this.validPeriod = validPeriod;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
