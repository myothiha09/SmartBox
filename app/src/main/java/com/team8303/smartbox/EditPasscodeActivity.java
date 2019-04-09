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

import com.team8303.SmartBoxApplication;
import com.team8303.api.ApiService;
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

import rx.Observer;
import rx.schedulers.Schedulers;

public class EditPasscodeActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    TextView passcodeNameText;
    EditText name;
    EditText number;
    Button generateButton;
    TextView pType;
    String type;
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

    final Calendar calendar1 = Calendar.getInstance();
    final Calendar calendar2 = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar1.set(Calendar.YEAR, year);
            calendar1.set(Calendar.MONTH, month);
            calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate1.setText(++month + "/" + dayOfMonth + "/" + year);
        }
    };

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar2.set(Calendar.YEAR, year);
            calendar2.set(Calendar.MONTH, month);
            calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDate2.setText(++month + "/" + dayOfMonth + "/" + year);
        }
    };

    TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar1.set(Calendar.MINUTE, minute);
            setTime1.setText(hourOfDay + ":" + minute);
        }
    };

    TimePickerDialog.OnTimeSetListener time2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar2.set(Calendar.MINUTE, minute);
            setTime2.setText(hourOfDay + ":" + minute);
        }
    };

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
        pType = findViewById(R.id.passcodeType);
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
        final int position = getIntent().getIntExtra("Position", -1);
        final String passcodeName = getIntent().getStringExtra("Name");
        final String passcodeNumber = getIntent().getStringExtra("Number");
        boolean[] days = getIntent().getBooleanArrayExtra("Days");
        String startDate = getIntent().getStringExtra("startDate");
        String endDate = getIntent().getStringExtra("endDate");
        String startTime = getIntent().getStringExtra("startTime");
        String endTime = getIntent().getStringExtra("endTime");

        if (startDate != null) {
            setDate1.setText(startDate);
        }
        if (endDate != null) {
            setDate2.setText(endDate);
        }
        if (startTime != null) {
            setTime1.setText(startTime);
        }
        if (endTime != null) {
            setTime2.setText(endTime);
        }

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
        name.setText(passcodeName);
        number.setText(passcodeNumber);
        type = getIntent().getStringExtra("Type");
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
        }

        sun.setChecked(days[0]);
        mon.setChecked(days[1]);
        tues.setChecked(days[2]);
        wed.setChecked(days[3]);
        thurs.setChecked(days[4]);
        fri.setChecked(days[5]);
        sat.setChecked(days[6]);

        setDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditPasscodeActivity.this, date1,
                        calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
                        calendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        setDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditPasscodeActivity.this, date2,
                        calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
                        calendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        setTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(EditPasscodeActivity.this, time1,
                        calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE),
                        true).show();
            }
        });

        setTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(EditPasscodeActivity.this, time2,
                        calendar2.get(Calendar.HOUR_OF_DAY), calendar2.get(Calendar.MINUTE),
                        true).show();
            }
        });

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
                //...And add passcode to firebase?
                PasscodeType codeType = PasscodeType.values()[dropDownMenu.getSelectedItemPosition()];

                boolean checked1 = sun.isChecked();
                boolean checked2 = mon.isChecked();
                boolean checked3 = tues.isChecked();
                boolean checked4 = wed.isChecked();
                boolean checked5 = thurs.isChecked();
                boolean checked6 = fri.isChecked();
                boolean checked7 = sat.isChecked();

                if (position != -1) {
                    Model.permanentPasscodes.remove(position);
                }
                Passcode newCode = new Passcode(name.getText().toString(),
                        usedCount, creationTime, enabled, number.getText().toString(),
                        codeType);
                if (type.equals("Repeat")) {
                    newCode.setDaysOfWeek(checked1, checked2, checked3,
                            checked4, checked5, checked6, checked7);
                } else if (type.equals("Temporary")) {
                    newCode.setStartDate(setDate1.getText().toString());
                    newCode.setEndDate(setDate2.getText().toString());
                    newCode.setStartTime(setTime1.getText().toString());
                    newCode.setEndTime(setTime2.getText().toString());
                }
                Model.permanentPasscodes.add(position, newCode);

                SmartBoxApplication.getInstance().getLockApiService().getLockStatus("-LbK-7O_EJzc38A7E3-L");
                //SmartBoxApplication.getInstance().getLockApiService().getLockList();
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
            pType.setText("Permanent");
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
            type = "Permanent";
        } else if (item.equals("Temporary")) {
            pType.setText("Temporary");
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
            type = "Temporary";
        } else if (item.equals("Repeat")) {
            pType.setText("Repeat");
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
            type = "Repeat";
        } else if (item.equals("One-time")) {
            pType.setText("One-time");
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
            type = "One-time";
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
