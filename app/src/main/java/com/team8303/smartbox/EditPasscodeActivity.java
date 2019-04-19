package com.team8303.smartbox;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.team8303.SmartBoxApplication;
import com.team8303.api.ApiService;
import com.team8303.api.model.PostLockPasswordArgs;
import com.team8303.api.model.PutLockPasswordArgs;
import com.team8303.api.model.PutLockStatusArgs;
import com.team8303.api.model.UserLockResponse;
import com.team8303.events.LockListEvent;
import com.team8303.model.Model;
import com.team8303.model.Passcode;
import com.team8303.model.PasscodeType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPasscodeActivity extends AppCompatActivity {
    @BindView(R.id.passcodeSpinner) Spinner passcodeTypeSpinner;
    @BindView(R.id.repeatContainer) View repeatContainer;
    @BindView(R.id.tempContainer) View tempContainer;
    @BindView(R.id.passcodeDescription) TextView passcodeDescription;
    @BindView(R.id.passcodeInput) EditText passcodeInput;
    @BindView(R.id.passcodeNameInput) EditText passcodeNameInput;
import retrofit2.http.Body;
import retrofit2.http.Path;
import rx.Observer;
import rx.schedulers.Schedulers;


public class EditPasscodeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    //labels to be used with Date/Time Chooser
    @BindView(R.id.startDateText) TextView startDateText;
    @BindView(R.id.endDateText) TextView endDateText;
    @BindView(R.id.startTimeText) TextView startTimeText;
    @BindView(R.id.endTimeText) TextView endTimeText;
    @BindView(R.id.allDayRadioButton) RadioButton allDayRadioButton;

    //checkboxes for Repeat
    @BindView(R.id.sundayCheckbox) CheckBox sunCheckbox;
    @BindView(R.id.monCheckbox) CheckBox monCheckbox;
    @BindView(R.id.tuesCheckbox) CheckBox tuesCheckbox;
    @BindView(R.id.wedCheckbox) CheckBox wedCheckbox;
    @BindView(R.id.thurCheckbox) CheckBox thursCheckbox;
    @BindView(R.id.friCheckbox) CheckBox friCheckbox;
    @BindView(R.id.satCheckbox) CheckBox satCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passcode);
        ButterKnife.bind(this);
        initSpinner();
    }
    public void initSpinner() {
        final List<PasscodeType> passcodeTypes = new ArrayList<>();
        passcodeTypes.add(PasscodeType.Permanent);
        passcodeTypes.add(PasscodeType.Temporary);
        passcodeTypes.add(PasscodeType.Repeat);
        passcodeTypes.add(PasscodeType.One_time);
        ArrayAdapter<PasscodeType> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, passcodeTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        passcodeTypeSpinner.setAdapter(dataAdapter);
        passcodeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PasscodeType passcodeType = passcodeTypes.get(i);
                if (passcodeType == PasscodeType.Permanent) {
                    passcodeDescription.setText(R.string.permanent_description);
                    repeatContainer.setVisibility(View.GONE);
                    tempContainer.setVisibility(View.GONE);
                } else if (passcodeType == PasscodeType.Temporary) {
                    passcodeDescription.setText(R.string.temporary_description);
                    tempContainer.setVisibility(View.VISIBLE);
                    repeatContainer.setVisibility(View.GONE);
                } else if (passcodeType == PasscodeType.Repeat) {
                    passcodeDescription.setText(R.string.repeat_description);
                    repeatContainer.setVisibility(View.VISIBLE);
                    tempContainer.setVisibility(View.GONE);
                } else if (passcodeType == PasscodeType.One_time) {
                    passcodeDescription.setText(R.string.onetime_description);
                    repeatContainer.setVisibility(View.GONE);
                    tempContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onClick(View view) {
                int randcode = new Random().nextInt(999999);
                String str = String.format("%06d", randcode);
                number.setText(str);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (!name.getText().toString().equals(getIntent().getStringExtra("Name"))) {
                    //pass new parameters to passcode
                }*/
                //...And add passcode to
                PasscodeType codeType = PasscodeType.values()[dropDownMenu.getSelectedItemPosition()];
                Model.permanentPasscodes.remove(position);
                Model.permanentPasscodes.add(position, new Passcode(name.getText().toString(),
                        usedCount, validPeriod, creationTime, enabled, number.getText().toString(),
                        codeType));

                //SmartBoxApplication.getInstance().getLockApiService().getLockStatus("-LbK-7O_EJzc38A7E3-L");
                //SmartBoxApplication.getInstance().getLockApiService().getLockList();
                UserLockResponse x = new UserLockResponse();
                List<String> xList = new ArrayList<String>();
                //xList.add("-LbK-7O_EJzc38A7E3-L");
                String lockId = "-LbK-7O_EJzc38A7E3-L";
                //x.setOwnedLockIds(xList);
                //x.setOwnedLockIds(xList);
                //SmartBoxApplication.getInstance().getLockApiService().postLock(x);
                PutLockStatusArgs y = new PutLockStatusArgs();

                y.setLockStatusArgs("123456", "OPEN_REQUESTED");
                PostLockPasswordArgs z = new PostLockPasswordArgs();
                Long longNum = new Long(-1);
                z.setPostLockPasswordArgs(xList, xList, -1, "246899", "OTP");
                PutLockPasswordArgs a = new PutLockPasswordArgs();
                a.setLockPasswordArgs(xList, xList, -1, "123456");
                //SmartBoxApplication.getInstance().getLockApiService().updateLockStatus(lockId, y);
                //SmartBoxApplication.getInstance().getLockApiService().deleteLockId(lockId);
                //SmartBoxApplication.getInstance().getLockApiService().getLockHistory(lockId);
                //SmartBoxApplication.getInstance().getLockApiService().getPasswordData(lockId);
                //SmartBoxApplication.getInstance().getLockApiService().postLockPassword(lockId, z);
                //SmartBoxApplication.getInstance().getLockApiService().getLockPasswordData(lockId, "-LcUcNZ4uX5eLdDevpQN");
                //SmartBoxApplication.getInstance().getLockApiService().putLockPassword(lockId, "-LcUcNZ4uX5eLdDevpQN", a);
               // SmartBoxApplication.getInstance().getLockApiService().deleteLockPassword(lockId, "-LcUcNZ4uX5eLdDevpQN");
                SmartBoxApplication.getInstance().getLockApiService().postUserInfo();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLockListEvent(LockListEvent event) {
        Intent intent = new Intent(EditPasscodeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
        String item = parent.getItemAtPosition(index).toString();

        if(item.equals("Permanent")) {
            type.setText("Permanent");
            description.setText("Passcode is permanent by default");
            notifyTitlePO.setVisibility(View.VISIBLE);
            notifyDescriptionPO.setVisibility(View.VISIBLE);
            setNotificationsPO.setVisibility(View.VISIBLE);
            notifyTitleTR.setVisibility(View.GONE);
            notifyDescriptionTR.setVisibility(View.GONE);
            setNotificationsTR.setVisibility(View.GONE);
            sun.setVisibility(View.GONE);
            mon.setVisibility(View.GONE);
            tues.setVisibility(View.GONE);
            wed.setVisibility(View.GONE);
            thurs.setVisibility(View.GONE);
            fri.setVisibility(View.GONE);
            sat.setVisibility(View.GONE);
            allDay.setVisibility(View.GONE);
            setDate1.setVisibility(View.GONE);
            setDate2.setVisibility(View.GONE);
            setTime1.setVisibility(View.GONE);
            setTime2.setVisibility(View.GONE);
            startTime.setVisibility(View.GONE);
            endTime.setVisibility(View.GONE);
            startDate.setVisibility(View.GONE);
            endDate.setVisibility(View.GONE);
        } else if (item.equals("Temporary")) {
            type.setText("Temporary");
            description.setText("Works over a period of time");
            notifyTitleTR.setVisibility(View.VISIBLE);
            notifyDescriptionTR.setVisibility(View.VISIBLE);
            setNotificationsTR.setVisibility(View.VISIBLE);
            notifyTitlePO.setVisibility(View.GONE);
            notifyDescriptionPO.setVisibility(View.GONE);
            setNotificationsPO.setVisibility(View.GONE);
            sun.setVisibility(View.GONE);
            mon.setVisibility(View.GONE);
            tues.setVisibility(View.GONE);
            wed.setVisibility(View.GONE);
            thurs.setVisibility(View.GONE);
            fri.setVisibility(View.GONE);
            sat.setVisibility(View.GONE);
            allDay.setVisibility(View.VISIBLE);
            setDate1.setVisibility(View.VISIBLE);
            setDate2.setVisibility(View.VISIBLE);
            setTime1.setVisibility(View.VISIBLE);
            setTime2.setVisibility(View.VISIBLE);
            startTime.setVisibility(View.VISIBLE);
            endTime.setVisibility(View.VISIBLE);
            startDate.setVisibility(View.VISIBLE);
            endDate.setVisibility(View.VISIBLE);
        } else if (item.equals("Repeat")) {
            type.setText("Repeat");
            description.setText("Repeated passcode repeats weekly");
            notifyTitleTR.setVisibility(View.VISIBLE);
            notifyDescriptionTR.setVisibility(View.VISIBLE);
            setNotificationsTR.setVisibility(View.VISIBLE);
            notifyTitlePO.setVisibility(View.GONE);
            notifyDescriptionPO.setVisibility(View.GONE);
            setNotificationsPO.setVisibility(View.GONE);
            sun.setVisibility(View.VISIBLE);
            mon.setVisibility(View.VISIBLE);
            tues.setVisibility(View.VISIBLE);
            wed.setVisibility(View.VISIBLE);
            thurs.setVisibility(View.VISIBLE);
            fri.setVisibility(View.VISIBLE);
            sat.setVisibility(View.VISIBLE);
            allDay.setVisibility(View.GONE);
            setDate1.setVisibility(View.GONE);
            setDate2.setVisibility(View.GONE);
            setTime1.setVisibility(View.GONE);
            setTime2.setVisibility(View.GONE);
            startTime.setVisibility(View.GONE);
            endTime.setVisibility(View.GONE);
            startDate.setVisibility(View.GONE);
            endDate.setVisibility(View.GONE);
        } else if (item.equals("One-time")) {
            type.setText("One-time");
            description.setText("One-time passcode expires after first usage");
            notifyTitlePO.setVisibility(View.VISIBLE);
            notifyDescriptionPO.setVisibility(View.VISIBLE);
            setNotificationsPO.setVisibility(View.VISIBLE);
            notifyTitleTR.setVisibility(View.GONE);
            notifyDescriptionTR.setVisibility(View.GONE);
            setNotificationsTR.setVisibility(View.GONE);
            sun.setVisibility(View.GONE);
            mon.setVisibility(View.GONE);
            tues.setVisibility(View.GONE);
            wed.setVisibility(View.GONE);
            thurs.setVisibility(View.GONE);
            fri.setVisibility(View.GONE);
            sat.setVisibility(View.GONE);
            allDay.setVisibility(View.GONE);
            setDate1.setVisibility(View.GONE);
            setDate2.setVisibility(View.GONE);
            setTime1.setVisibility(View.GONE);
            setTime2.setVisibility(View.GONE);
            startTime.setVisibility(View.GONE);
            endTime.setVisibility(View.GONE);
            startDate.setVisibility(View.GONE);
            endDate.setVisibility(View.GONE);
        }
    }
}
