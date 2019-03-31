package com.team8303.model;

import com.team8303.smartbox.smartbox_history.BoxHistoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public class Model {
    public static List<Passcode> permanentPasscodes = new ArrayList<>();
    public static List<Passcode> tempPasscodes = new ArrayList<>();
    public static List<Passcode> onePasscodes = new ArrayList<>();
    public static List<Passcode> repeatPasscodes = new ArrayList<>();

    public static HashMap<String, BoxHistoryItem> boxHistory = new LinkedHashMap<>();

    public static User user = new User("George Burdell", "buzzrox", "", UserType.USER, "678-136-7092", "buzz@gg.com", 1, "3/31/2019");

    private static boolean USE_MOCK = true;

    private static Model model;
    private Model() {

        permanentPasscodes.add(new Passcode("Passcode 1", 0, "",
                false, "974632", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 2", 0, "",
                true, "123456", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 3", 0, "",
                false, "485201", PasscodeType.Permanent));

        tempPasscodes.add(new Passcode("Passcode 4", 0, "2/25/2019",
                false, "355000", PasscodeType.Temporary));
        tempPasscodes.add(new Passcode("Passcode 5", 0,
                "2/25/2019", true, "111111", PasscodeType.Temporary));

        repeatPasscodes.add(new Passcode("Passcode 6", 0,
                "2/25/2019", false, "876543", PasscodeType.Repeat));

        onePasscodes.add(new Passcode("Passcode 7", 0, "2/25/2019",
                false, "503214", PasscodeType.One_time));

    }

    public static User getUserInfo() {
        if (USE_MOCK) {
            return user;
        }
        return user;
    }

    //please do all retrievals for data inside similar methods as before for seamless switching between mock and real
    public static List<Passcode> getPermanentPasscodes() {
        if (USE_MOCK) {
            return permanentPasscodes;
        }
        return new ArrayList<>(); //placeholder can make retrofit calls here
    }

    public static List<Passcode> getTempPasscodes() {
        if (USE_MOCK) {
            return tempPasscodes;
        }
        return new ArrayList<>(); //placeholder
    }

    public static List<Passcode> getRepeatPasscodes() {
        if (USE_MOCK) {
            return repeatPasscodes;
        }
        return new ArrayList<>(); //placeholder
    }

    public static List<Passcode> getOnePasscodes() {
        if (USE_MOCK) {
            return onePasscodes;
        }
        return new ArrayList<>(); //placeholder
    }

    //returning LinkedHashMap to preserve order for keys
    public static HashMap<String, BoxHistoryItem> getBoxHistory() {
        if (USE_MOCK) {
            return boxHistory;
        }
        return new HashMap<>();
    }

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
}
