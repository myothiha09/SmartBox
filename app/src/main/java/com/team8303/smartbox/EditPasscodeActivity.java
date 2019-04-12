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

import com.team8303.model.Model;
import com.team8303.model.Passcode;
import com.team8303.model.PasscodeType;

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
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @OnClick(R.id.startDateButton) void onStartDateClicked() { //I think you can just append the date chosen to startDateText.
        Toast.makeText(getApplicationContext(), "Start Date clicked", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.startTimeButton) void onStartTimeClicked() {
        Toast.makeText(getApplicationContext(), "Start Time clicked", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.endDateButton) void onEndDateClicked() {
        Toast.makeText(getApplicationContext(), "End Date clicked", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.endTimeButton) void onEndTimeClicked() {
        Toast.makeText(getApplicationContext(), "End Time clicked", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.generatePasscode) void onGeneratePasscodeClicked() {
        int randcode = new Random().nextInt(999999);
        String str = String.format("%06d", randcode);
        passcodeInput.setText(str);
    }
    @OnClick(R.id.saveButton) void onSaveButtonClicked() {
        Toast.makeText(getApplicationContext(), "Save Button clicked", Toast.LENGTH_SHORT).show();
        finish(); //can kill this activity to go back to previous activity. previous activity might need to use Android Lifecycle to refresh data.
    }
}
