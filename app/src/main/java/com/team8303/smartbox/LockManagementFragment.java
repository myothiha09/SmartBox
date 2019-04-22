package com.team8303.smartbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.stetho.inspector.elements.ShadowDocument;
import com.team8303.SmartBoxApplication;
import com.team8303.api.model.UserLockStatusResponse;
import com.team8303.events.LockStatusEvent;
import com.team8303.events.UpdateLockStatusEvent;
import com.team8303.model.Model;
import com.team8303.smartbox.passcode.PasscodeFragment;
import com.team8303.smartbox.smartbox_history.BoxHistoryFragment;
import com.team8303.smartbox.smartbox_users.BoxUsersFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LockManagementFragment extends Fragment {

    @BindView(R.id.button_lock_unlock)
    Button stateChange;

    @BindView(R.id.smartlock_locked_status)
    TextView lockState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock_management, container, false);
        ButterKnife.bind(this, view);
        Model.getLockStatus(SmartBoxApplication.getInstance().getLockId());
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
            //please replace this passcode before the demo.
            Model.unlockBox(SmartBoxApplication.getInstance().getLockId(), "111111");
        } else {
            stateChange.setText(getString(R.string.unlock));
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLockStatusEvent(LockStatusEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.getResponse().getStatus().equals("CLOSED")) {
            stateChange.setText(getString(R.string.unlock));
            lockState.setText(getString(R.string.locked));
        } else {
            stateChange.setText(getString(R.string.lock));
            lockState.setText(getString(R.string.unlocked));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLockStatusChangeEvent(UpdateLockStatusEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        Model.getLockStatus(SmartBoxApplication.getInstance().getLockId());
    }
}
