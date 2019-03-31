package com.team8303.smartbox.user_profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.team8303.model.Model;
import com.team8303.model.User;
import com.team8303.smartbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileFragment extends Fragment {

    @BindView(R.id.profile_img)
    ImageView usericon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.active_smartbox_count)
    TextView smartboxCount;
    @BindView(R.id.date_registered)
    TextView dateRegistered;
    @BindView(R.id.phone_number)
    TextView phoneNum;
    @BindView(R.id.email)
    TextView email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        User user = Model.getUserInfo();
        name.setText(user.getName());
        username.setText(getFormatted(username, user.getUsername()));
        smartboxCount.setText(getFormatted(smartboxCount, user.getActiveCount() + ""));
        dateRegistered.setText(getFormatted(dateRegistered, user.getDate_registered()));
        phoneNum.setText(getFormatted(phoneNum, user.getPhoneNumber()));
        email.setText(getFormatted(email, user.getEmail()));

        return view;
    }
    public String getFormatted(TextView textView, String data) {
        return textView.getText() + ": " + data;
    }

    @OnClick(R.id.change_password_button) void changePassword() {
        Toast.makeText(getContext(), "Change password clicked", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.delete_account_button) void deleteAccount() {
        Toast.makeText(getContext(), "Delete account clicked", Toast.LENGTH_SHORT).show();
    }
}
