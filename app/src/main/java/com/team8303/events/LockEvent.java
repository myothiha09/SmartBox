package com.team8303.events;

public class LockEvent {

    private Long createdAt;
    private String endpoint;
    private String lockId;
    private String status;
    private String userId;

    public LockEvent(Long createdAt, String endpoint, String lockId, String status, String userId) {
        this.createdAt = createdAt;
        this.endpoint = endpoint;
        this.lockId = lockId;
        this.status = status;
        this.userId = userId;
    }


}
