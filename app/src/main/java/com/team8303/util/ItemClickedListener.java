package com.team8303.util;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public interface ItemClickedListener<T> {
    void itemChosen(int position);
    void delItem(int position);
    void toggleItem(int position);
}
