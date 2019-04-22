package com.team8303.model;

import com.team8303.api.model.PostLockPasswordArgs;
import com.team8303.api.model.PutLockPasswordArgs;
import com.team8303.api.model.PutLockStatusArgs;
import com.team8303.api.model.UserLockResponse;
import com.team8303.api.model.UserLockStatusResponse;
import com.team8303.events.LockListEvent;
import com.team8303.events.LockStatusEvent;
import com.team8303.events.ModelLockListEvent;
import com.team8303.events.PostLockPasswordEvent;
import com.team8303.events.UpdateLockStatusEvent;
import com.team8303.smartbox.active_smartboxes.Smartbox;
import com.team8303.SmartBoxApplication;
import com.team8303.api.model.LockPasswordResponse;
import com.team8303.api.model.LockPasswordsResponse;
import com.team8303.events.GetPasswordDataEvent;
import com.team8303.events.ModelPasscodeListEvent;
import com.team8303.smartbox.smartbox_history.BoxHistoryItem;
import com.team8303.smartbox.smartbox_history.LockStatus;
import com.team8303.smartbox.smartbox_users.BoxUsersItem;
import com.team8303.smartbox.smartbox_history.LockStatus;
import com.team8303.smartbox.smartbox_users.BoxUsersItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Response;
import rx.Observer;

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

    private static boolean USE_MOCK = false;

    private static Model model;
    private static List<Smartbox> activeBoxes = new ArrayList<>();
    private static final String[] daysOfWeek = {"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};

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
    public static List<Passcode> getPermanentPasscodes(String lockId) {
        if (USE_MOCK) {
            ModelPasscodeListEvent event = new ModelPasscodeListEvent();
            event.setRequestedPasscodes(permanentPasscodes);
            event.setTypeRequested(PasscodeType.Permanent);
            EventBus.getDefault().postSticky(event);
            return permanentPasscodes;
        }
        SmartBoxApplication.getInstance().getLockApiService().getPasswordData(lockId)
                .subscribe(new Observer<Response<LockPasswordsResponse>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<LockPasswordsResponse> lockPasswordsResponse) {
                if (lockPasswordsResponse.isSuccessful()) {
                    List<Passcode> passcodes = new ArrayList<>();
                    List<LockPasswordResponse> permanentPasscodes = lockPasswordsResponse.body().getPermanent();
                    for (LockPasswordResponse response: permanentPasscodes) {
                        Passcode passcode = new Passcode(null, 0, response.getCreatedAt().toString(), true, response.getId(),
                                null);
                        if (response.getType().equals("UNLIMITED")) {
                            passcode.setType(PasscodeType.Permanent);
                        } else if (response.getType().equals("OTP")) {
                            passcode.setType(PasscodeType.One_time);
                        }
                        passcode.setId(response.getId());
                        passcodes.add(passcode);
                    }
                    ModelPasscodeListEvent event = new ModelPasscodeListEvent();
                    event.setTypeRequested(PasscodeType.Permanent);
                    event.setRequestedPasscodes(passcodes);
                    EventBus.getDefault().postSticky(event);
                } else {
                    EventBus.getDefault().postSticky(new GetPasswordDataEvent(null, false));
                }
            }
        });
        return new ArrayList<>(); //placeholder can make retrofit calls here
    }

    public static List<Passcode> getTempPasscodes() {
        if (USE_MOCK) {
            ModelPasscodeListEvent event = new ModelPasscodeListEvent();
            event.setRequestedPasscodes(tempPasscodes);
            event.setTypeRequested(PasscodeType.Temporary);
            EventBus.getDefault().postSticky(event);
            return tempPasscodes;
        }
        ModelPasscodeListEvent event = new ModelPasscodeListEvent();
        event.setRequestedPasscodes(new ArrayList<Passcode>());
        event.setTypeRequested(PasscodeType.Temporary);
        EventBus.getDefault().postSticky(event);
        return new ArrayList<>(); //placeholder
    }

    public static void savePasscode(final String lockId, final Passcode passcode) {
        if (USE_MOCK) {
            if (passcode.getType() == PasscodeType.Permanent) {
                permanentPasscodes.remove(passcode);
                permanentPasscodes.add(passcode);
            } else if (passcode.getType() == PasscodeType.Temporary) {
                tempPasscodes.remove(passcode);
                tempPasscodes.add(passcode);
            } else if (passcode.getType() == PasscodeType.One_time) {
                onePasscodes.remove(passcode);
                onePasscodes.add(passcode);
            } else {
                repeatPasscodes.remove(passcode);
                repeatPasscodes.add(passcode);
            }
        } else {
            if (passcode.getId() != null) {
                PutLockPasswordArgs args = new PutLockPasswordArgs();
                args.setLockPasswordArgs(new ArrayList<String>(), new ArrayList<String>(), -1,
                        passcode.getNumber());
                SmartBoxApplication.getInstance().getLockApiService().putLockPassword(lockId, passcode.getId(), args)
                        .subscribe(new Observer<Response<LockPasswordResponse>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Response<LockPasswordResponse> lockPasswordResponse) {
                                if (lockPasswordResponse.isSuccessful()) {
//                                    Model.unlockBox(lockId, passcode.getNumber());
                                    EventBus.getDefault().postSticky(new PostLockPasswordEvent(lockPasswordResponse.body(), true));
                                } else {
                                    EventBus.getDefault().postSticky(new PostLockPasswordEvent(null, false));
                                }
                            }
                        });
            } else {
                PostLockPasswordArgs args = new PostLockPasswordArgs();
                String type = "UNLIMITED";
                if (passcode.getType() == PasscodeType.Permanent) {
                    type = "UNLIMITED";
                } else if (passcode.getType() == PasscodeType.One_time) {
                    type = "OTP";
                }
                args.setPostLockPasswordArgs(new ArrayList<String>(), new ArrayList<String>(), -1,
                        passcode.getNumber(), type);
                SmartBoxApplication.getInstance().getLockApiService().postLockPassword(lockId, args)
                        .subscribe(new Observer<Response<LockPasswordResponse>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Response<LockPasswordResponse> lockPasswordResponse) {
                                if (lockPasswordResponse.isSuccessful()) {
//                                    Model.unlockBox(lockId, passcode.getNumber());
                                    EventBus.getDefault().postSticky(new PostLockPasswordEvent(lockPasswordResponse.body(), true));
                                } else {
                                    EventBus.getDefault().postSticky(new PostLockPasswordEvent(null, false));
                                }
                            }
                        });
            }
        }
    }

    public static List<Passcode> getRepeatPasscodes() {
        if (USE_MOCK) {
            ModelPasscodeListEvent event = new ModelPasscodeListEvent();
            event.setRequestedPasscodes(repeatPasscodes);
            event.setTypeRequested(PasscodeType.Repeat);
            EventBus.getDefault().postSticky(event);
            return repeatPasscodes;
        }
        ModelPasscodeListEvent event = new ModelPasscodeListEvent();
        event.setRequestedPasscodes(new ArrayList<Passcode>());
        event.setTypeRequested(PasscodeType.Repeat);
        EventBus.getDefault().postSticky(event);
        return new ArrayList<>(); //placeholder
    }

    public static List<Passcode> getOnePasscodes(String lockId) {
        if (USE_MOCK) {
            ModelPasscodeListEvent event = new ModelPasscodeListEvent();
            event.setTypeRequested(PasscodeType.One_time);
            event.setRequestedPasscodes(onePasscodes);
            EventBus.getDefault().postSticky(event);
            return onePasscodes;
        }
        SmartBoxApplication.getInstance().getLockApiService().getPasswordData(lockId)
                .subscribe(new Observer<Response<LockPasswordsResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LockPasswordsResponse> lockPasswordsResponse) {
                        if (lockPasswordsResponse.isSuccessful()) {
                            List<Passcode> passcodes = new ArrayList<>();
                            List<LockPasswordResponse> oneTimePasscodes = lockPasswordsResponse.body().getOtp();
                            for (LockPasswordResponse response: oneTimePasscodes) {
                                Passcode passcode = new Passcode(null, 0, response.getCreatedAt().toString(), true, response.getId(),
                                        PasscodeType.valueOf(response.getType()));
                                passcodes.add(passcode);
                            }
                            ModelPasscodeListEvent event = new ModelPasscodeListEvent();
                            event.setTypeRequested(PasscodeType.One_time);
                            event.setRequestedPasscodes(passcodes);
                            EventBus.getDefault().postSticky(event);
                        } else {
                            EventBus.getDefault().postSticky(new GetPasswordDataEvent(null, false));
                        }
                    }
                });
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
            ModelLockListEvent event = new ModelLockListEvent();
            event.setLocks(activeBoxes);
            EventBus.getDefault().postSticky(event);
            return activeBoxes;
        }
        SmartBoxApplication.getInstance().getLockApiService().getLockList().subscribe(new Observer<Response<UserLockResponse>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Response<UserLockResponse> userLockResponse) {
                if (userLockResponse.isSuccessful()) {
                    List<String> lockIds = userLockResponse.body().getOwnedLockIds();
                    final List<Smartbox> locks = new ArrayList<>();
                    for (String id: lockIds) {
                        locks.add(new Smartbox(null, id, 0));
                    }
                    ModelLockListEvent event = new ModelLockListEvent();
                    event.setLocks(locks);
                    EventBus.getDefault().postSticky(event);
                } else {
                    ModelLockListEvent event = new ModelLockListEvent();
                    event.setLocks(activeBoxes);
                    EventBus.getDefault().postSticky(event);
                }
            }
        });
        return new ArrayList<>();
    }

    public static void unlockBox(String lockId, String passcode) {
        if (USE_MOCK) {
            List<BoxHistoryItem> history = boxHistory.get("April 22 2019");
            if (history == null) {
                history = new ArrayList<>();
            }
            history.add(new BoxHistoryItem("April 22, 2019", null, LockStatus.UNLOCKED, "Phone", permanentPasscodes.get(0)));
            boxHistory.put("April 22 2019", history);
            EventBus.getDefault().postSticky(new UpdateLockStatusEvent(null, true));
        } else {
            PutLockStatusArgs args = new PutLockStatusArgs();
            args.setLockStatusArgs(passcode, "OPEN_REQUESTED");
            SmartBoxApplication.getInstance().getLockApiService().updateLockStatus(lockId, args)
                    .subscribe(new Observer<Response<PutLockStatusArgs>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Response<PutLockStatusArgs> putUserLockStatusResponse) {
                            if (putUserLockStatusResponse.isSuccessful()) {
                                EventBus.getDefault().postSticky(new UpdateLockStatusEvent(putUserLockStatusResponse.body(), true));
                            } else {
                                EventBus.getDefault().postSticky(new UpdateLockStatusEvent(null, false));
                            }
                        }
                    });
        }
    }

    public static void lockBox(String lockId) {
        if (USE_MOCK) {
            List<BoxHistoryItem> history = boxHistory.get("April 22 2019");
            if (history == null) {
                history = new ArrayList<>();
            }
            history.add(new BoxHistoryItem("April 22, 2019", null, LockStatus.LOCKED, "Phone", null));
            boxHistory.put("April 22 2019", history);
        } else {
            PutLockStatusArgs args = new PutLockStatusArgs();
            args.setLockStatusArgs(null, "CLOSED");
            SmartBoxApplication.getInstance().getLockApiService().updateLockStatus(lockId, args)
                    .subscribe(new Observer<Response<PutLockStatusArgs>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Response<PutLockStatusArgs> putUserLockStatusResponse) {
                            if (putUserLockStatusResponse.isSuccessful()) {
                                EventBus.getDefault().postSticky(new UpdateLockStatusEvent(putUserLockStatusResponse.body(), true));
                            } else {
                                EventBus.getDefault().postSticky(new UpdateLockStatusEvent(null, false));
                            }
                        }
                    });
        }
    }

    public static void getLockStatus(String lockId) {
        if (USE_MOCK) {

        } else {
            SmartBoxApplication.getInstance().getLockApiService().getLockStatus(lockId)
                    .subscribe(new Observer<Response<UserLockStatusResponse>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Response<UserLockStatusResponse> userLockStatusResponse) {
                    if (userLockStatusResponse.isSuccessful()) {

                        EventBus.getDefault().postSticky(new LockStatusEvent(userLockStatusResponse.body(), true));
                    } else {
                        EventBus.getDefault().postSticky(new LockStatusEvent(null, false));
                    }
                }
            });
        }
    }
}
