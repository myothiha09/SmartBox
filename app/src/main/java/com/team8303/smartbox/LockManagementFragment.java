package com.team8303.smartbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.team8303.SmartBoxApplication;
import com.team8303.model.Model;
import com.team8303.smartbox.passcode.PasscodeFragment;
import com.team8303.smartbox.smartbox_history.BoxHistoryFragment;
import com.team8303.smartbox.smartbox_users.BoxUsersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LockManagementFragment extends Fragment {

    @BindView(R.id.button_lock_unlock)
    Button stateChange;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock_management, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_access_history) void viewHistory() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BoxHistoryFragment()).addToBackStack(null).commit();
    }

    @OnClick(R.id.button_passcodes) void viewPassCodes() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PasscodeFragment()).addToBackStack(null).commit();
    }
    @OnClick(R.id.button_users) void viewUsers() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new BoxUsersFragment()).addToBackStack(null).commit();
    }
    @OnClick(R.id.button_lock_unlock) void changeLockState() {
        if (stateChange.getText().equals(getString(R.string.unlock))) {
            stateChange.setText(getString(R.string.lock));
            Model.unlockBox(SmartBoxApplication.getInstance().getLockId(), null);
        } else {
            stateChange.setText(getString(R.string.unlock));
        }
    }
}
