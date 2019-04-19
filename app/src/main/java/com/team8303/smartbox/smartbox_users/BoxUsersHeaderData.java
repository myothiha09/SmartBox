package com.team8303.smartbox.smartbox_users;

import android.support.annotation.LayoutRes;

import com.saber.stickyheader.stickyData.HeaderData;
import com.team8303.model.UserType;

public class BoxUsersHeaderData implements HeaderData {
    public static final int HEADER_TYPE_1 = 1;

    private int headerType;
    @LayoutRes
    private final int layoutResource;

    private UserType userType;
    public BoxUsersHeaderData(int headerType, @LayoutRes int layoutResource, UserType userType) {
        this.layoutResource = layoutResource;
        this.headerType = headerType;
        this.userType = userType;
    }

    public String getUserType() {
        return userType.name();
    }

    @LayoutRes
    @Override
    public int getHeaderLayout() {
        //retunr layout of yourHeader
        return layoutResource;
    }

    @Override
    public int getHeaderType() {
        return headerType;
    }
}