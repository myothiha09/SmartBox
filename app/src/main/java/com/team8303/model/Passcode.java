package com.team8303.model;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public class Passcode {
    String name;
    int usedCount;
    String creationTime;
    boolean enabled;
    String number;
    PasscodeType type;
    boolean[] daysOfWeek = new boolean[7];
    String startDate;
    String endDate;
    String startTime;
    String endTime;

    public Passcode(String name, int usedCount, String creationTime,
                    boolean enabled, String number, PasscodeType type) {
        this.name = name;
        this.usedCount = usedCount;
        this.creationTime = creationTime;
        this.enabled = enabled;
        this.number = number;
        this.type = type;
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public PasscodeType getType() { return this.type; }

    public void setType(PasscodeType type) { this.type = type; }

    public boolean[] getDaysOfWeek() { return this.daysOfWeek; }

    public void setDaysOfWeek(boolean sun, boolean mon, boolean tues,
                              boolean wed, boolean thurs, boolean fri,
                              boolean sat) {
        daysOfWeek[0] = sun;
        daysOfWeek[1] = mon;
        daysOfWeek[2] = tues;
        daysOfWeek[3] = wed;
        daysOfWeek[4] = thurs;
        daysOfWeek[5] = fri;
        daysOfWeek[6] = sat;

    }

    public String getStartDate() {return this.startDate; }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() { return this.endDate; }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() { return this.startTime; }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() { return this.endTime; }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
