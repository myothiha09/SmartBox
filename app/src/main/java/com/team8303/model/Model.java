package com.team8303.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo Thiha on 2/25/2019.
 */

public class Model {
    public static List<Passcode> permanentPasscodes = new ArrayList<>();
    public static List<Passcode> tempPasscodes = new ArrayList<>();
    public static List<Passcode> onePasscodes = new ArrayList<>();
    public static List<Passcode> repeatPasscodes = new ArrayList<>();

    private static Model model;
    private Model() {
        permanentPasscodes.add(new Passcode("Passcode 1", 0, "", "2/25/2019",
                false, "974632", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 2", 0, "", "2/14/2019",
                true, "123456", PasscodeType.Permanent));
        permanentPasscodes.add(new Passcode("Passcode 3", 0, "", "2/16/2019",
                false, "485201", PasscodeType.Permanent));

        tempPasscodes.add(new Passcode("Passcode 4", 0, "2/25 (6PM) - 2/25(9PM)",
                "2/25/2019", false, "355000", PasscodeType.Temporary));
        tempPasscodes.add(new Passcode("Passcode 5", 0, "2/22 (6PM) - 2/22(9PM)",
                "2/25/2019", true, "111111", PasscodeType.Temporary));

        repeatPasscodes.add(new Passcode("Passcode 6", 0, "Every Mon, Wed, Fri",
                "2/25/2019", false, "876543", PasscodeType.Repeat));

        onePasscodes.add(new Passcode("Passcode 7", 0, "", "2/25/2019",false,
                "503214", PasscodeType.One_time));

    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
}
