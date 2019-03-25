package com.team8303.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserLockResponse {

    @SerializedName("ownedLockIds")
    private List<String> ownedLockIds;

    public void setOwnedLockIds(List<String> ownedLockIds) {
        this.ownedLockIds = ownedLockIds;
    }

    public List<String> getOwnedLockIds() {
        return ownedLockIds;
    }
}
