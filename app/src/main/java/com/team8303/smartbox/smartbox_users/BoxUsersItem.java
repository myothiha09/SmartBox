package com.team8303.smartbox.smartbox_users;

import com.saber.stickyheader.stickyData.StickyMainData;
import com.team8303.model.UserType;

public class BoxUsersItem implements StickyMainData {
    private UserType userType;
    private String name;

    public BoxUsersItem(UserType userType, String name) {
        this.userType = userType;
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
