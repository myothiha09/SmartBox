package com.team8303.model;

import com.team8303.smartbox.active_smartboxes.Smartbox;
import com.team8303.smartbox.smartbox_history.BoxHistoryItem;
import com.team8303.smartbox.smartbox_history.LockStatus;
import com.team8303.smartbox.smartbox_users.BoxUsersItem;

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

    public static HashMap<String, List<BoxHistoryItem>> boxHistory = new LinkedHashMap<>();
    public static HashMap<UserType, List<BoxUsersItem>> boxUsers = new LinkedHashMap<>();

    public static User user = new User("George Burdell", "buzzrox", "", UserType.USER, "678-136-7092", "buzz@gg.com", 1, "3/31/2019");

    private static boolean USE_MOCK = true;

    private static Model model;
    private static List<Smartbox> activeBoxes = new ArrayList<>();

    private Model() {

        activeBoxes.add(new Smartbox("Box 1", "123456", 4));
        activeBoxes.add(new Smartbox("Box 2", "132412", 1));
        permanentPasscodes.add(new Passcode("Passcode 1", 0, "3/31/2019",
                false, "974632", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 2", 0, "3/31/2019",
                true, "123456", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 3", 0, "3/31/2019",
                false, "485201", PasscodeType.Permanent));

        tempPasscodes.add(new Passcode("Passcode 4", 0, "2/25/2019",
                false, "355000", PasscodeType.Temporary));
        tempPasscodes.add(new Passcode("Passcode 5", 0,
                "2/25/2019", true, "111111", PasscodeType.Temporary));

        repeatPasscodes.add(new Passcode("Passcode 6", 0,
                "2/25/2019", false, "876543", PasscodeType.Repeat));

        onePasscodes.add(new Passcode("Passcode 7", 0, "2/25/2019",
                false, "503214", PasscodeType.One_time));

        List<BoxHistoryItem> items = new ArrayList<>();

        //passcode only needs passcode number and passcode type
        items.add(new BoxHistoryItem("March 31 2019", "4:01 PM", LockStatus.LOCKED, "Box", permanentPasscodes.get(0)));
        items.add(new BoxHistoryItem("March 31 2019", "3:59 PM", LockStatus.UNLOCKED, "Box", permanentPasscodes.get(1)));

        boxHistory.put("March 31 2019", items);

        items = new ArrayList<>();
        items.add(new BoxHistoryItem("March 30 2019", "3:59 PM", LockStatus.LOCKED, "UPS", tempPasscodes.get(1))); //don't need passcode to lock but placed as placeholder
        items.add(new BoxHistoryItem("March 30 2019", "3:58 PM", LockStatus.UNLOCKED, "UPS", tempPasscodes.get(0)));
        items.add(new BoxHistoryItem("March 30 2019", "3:52 PM", LockStatus.ATTEMPTED_UNLOCK, "UPS", tempPasscodes.get(1)));

        boxHistory.put("March 30 2019", items);

        List<BoxUsersItem> items2 = new ArrayList<>();
        items2.add(new BoxUsersItem(UserType.ADMINISTRATOR, "George Burdell"));

        boxUsers.put(UserType.ADMINISTRATOR, items2);

        items2 = new ArrayList<>();

        items2.add(new BoxUsersItem(UserType.USER, "Buzz"));
        items2.add(new BoxUsersItem(UserType.USER, "Bee"));

        boxUsers.put(UserType.USER, items2);
    }

    public static HashMap<UserType, List<BoxUsersItem>> getBoxUsers() {
        if (USE_MOCK) {
            return boxUsers;
        }
        return boxUsers;
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
    public static HashMap<String, List<BoxHistoryItem>> getBoxHistory() {
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

    public static List<Smartbox> getActiveSmartboxes() {
        if (USE_MOCK) {
            return activeBoxes;
        }
        return new ArrayList<>();
    }
}
