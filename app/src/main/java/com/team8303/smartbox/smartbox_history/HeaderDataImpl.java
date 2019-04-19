package com.team8303.smartbox.smartbox_history;

import android.support.annotation.LayoutRes;

import com.saber.stickyheader.stickyData.HeaderData;

public class HeaderDataImpl implements HeaderData {
    public static final int HEADER_TYPE_1 = 1;

    private int headerType;
    @LayoutRes
    private final int layoutResource;

    private String date;
    public HeaderDataImpl(int headerType, @LayoutRes int layoutResource, String date) {
        this.layoutResource = layoutResource;
        this.headerType = headerType;
        this.date = date;
    }

    public String getDate() {
        return date;
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