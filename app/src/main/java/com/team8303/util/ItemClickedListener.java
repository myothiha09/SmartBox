package com.team8303.util;

import com.team8303.model.Passcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public interface ItemClickedListener<T> {
    List<Passcode> list = new ArrayList<>();
    void itemChosen(int position);
    void delItem(int position);
    void toggleItem(int position);
}
