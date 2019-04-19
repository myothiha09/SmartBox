package com.team8303.model;

import android.widget.TextView;

public class Utils {
    public static String getFormatted(TextView textView, String data) {
        return textView.getText() + ": " + data;
    }
}
