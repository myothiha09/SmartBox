package com.team8303.smartbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;
import com.team8303.SmartBoxApplication;
import com.team8303.api.ApiService;
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
import java.util.List;
import java.util.Random;

import rx.Observer;
import rx.schedulers.Schedulers;

public class EditPasscodeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    TextView passcodeNameText;
    EditText name;
    EditText number;
    Button generateButton;
    TextView type;
    Spinner dropDownMenu;
    TextView description;
    TextView notifyTitlePO, notifyDescriptionPO;
    Switch setNotificationsPO;
    TextView notifyTitleTR, notifyDescriptionTR;
    Switch setNotificationsTR;
    CheckBox sun, mon, tues, wed, thurs, fri, sat;
    Button saveButton;
    EditText setDate1, setDate2, setTime1, setTime2;
    TextView startDate, endDate, startTime, endTime;
    RadioButton allDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_passcode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        passcodeNameText = findViewById(R.id.passcodeNameText);
        name = findViewById(R.id.passcodeName);
        number = findViewById(R.id.passcodeNumber);
        generateButton = findViewById(R.id.generateButton);
        type = findViewById(R.id.passcodeType);
        dropDownMenu = findViewById(R.id.dropDownMenu);
        description = findViewById(R.id.passcodeDescription);
        notifyTitlePO = findViewById(R.id.notifications1);
        notifyDescriptionPO = findViewById(R.id.notificationDescription1);
        setNotificationsPO = findViewById(R.id.notificationSwitch1);
        notifyTitleTR = findViewById(R.id.notifications2);
        notifyDescriptionTR = findViewById(R.id.notificationDescription2);
        setNotificationsTR = findViewById(R.id.notificationSwitch2);
        sun = findViewById(R.id.sun);
        mon = findViewById(R.id.mon);
        tues = findViewById(R.id.tues);
        wed = findViewById(R.id.wed);
        thurs = findViewById(R.id.thur);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        allDay = findViewById(R.id.daySwitch);
        setDate1 = findViewById(R.id.calendar1);
        setDate2 = findViewById(R.id.calendar2);
        setTime1 = findViewById(R.id.time1);
        setTime2 = findViewById(R.id.time2);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        saveButton = findViewById(R.id.saveButton);

        dropDownMenu.setOnItemSelectedListener(this);

        List<String> passcodeTypes = new ArrayList<>();
        passcodeTypes.add("Permanent");
        passcodeTypes.add("Temporary");
        passcodeTypes.add("Repeat");
        passcodeTypes.add("One-time");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, passcodeTypes);

        final int usedCount = getIntent().getIntExtra("Used Count", 0);
        final String validPeriod = getIntent().getStringExtra("Valid Period");
        final String creationTime = getIntent().getStringExtra("Creation Time");
        final boolean enabled = getIntent().getBooleanExtra("Enabled", true);
        final int position = getIntent().getIntExtra("Position", 0);

        dropDownMenu.setAdapter(adapter);
        //dropDownMenu.setSelection(0);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        name.setText(getIntent().getStringExtra("Name"));
        number.setText(getIntent().getStringExtra("Number"));
        String type = getIntent().getStringExtra("Type");
        switch (type) {
            case "Permanent":
                dropDownMenu.setSelection(0);
            break;
            case "Temporary":
                dropDownMenu.setSelection(1);
            break;
            case "Repeat":
                dropDownMenu.setSelection(2);
            break;
            case "One_time":
                dropDownMenu.setSelection(3);
            break;
            default:
               dropDownMenu.setSelection(0);
            break;
        }

        generateButton.setOnClickListener(new View.OnClickListener() {
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
                xList.add("-LbK-7O_EJzc38A7E3-L");
                //x.setOwnedLockIds(xList);
                x.setOwnedLockIds(xList);
                SmartBoxApplication.getInstance().getLockApiService().postLock(x);
                PutLockStatusArgs y = new PutLockStatusArgs();
                y.setLockStatusArgs("123456", "OPEN");
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

    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
