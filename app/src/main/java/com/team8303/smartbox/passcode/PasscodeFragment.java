package com.team8303.smartbox.passcode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.team8303.model.Model;
import com.team8303.model.Passcode;
import com.team8303.smartbox.EditPasscodeActivity;
import com.team8303.smartbox.R;
import com.team8303.util.ItemClickedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasscodeFragment extends Fragment {

    @BindView(R.id.passcode_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tabLayout)
    TabLayout layout;

    @BindString(R.string.permanent) String _permanent;
    @BindString(R.string.temp) String _temp;
    @BindString(R.string.repeat) String _repeat;
    @BindString(R.string.one_time) String _one_time;
    public static PasscodeRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String currentTab = "Permanent";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passcode, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String title = tab.getText().toString();
                if (title.equals(_permanent)) {
                    currentTab = "Permanent";
                  //  Toast.makeText(getContext(), _permanent, Toast.LENGTH_LONG).show();
                    adapter.setPasscodeList(Model.getPermanentPasscodes());
                } else if (title.equals(_temp)) {
                    currentTab = "Temporary";
                  //  Toast.makeText(getContext(), _temp, Toast.LENGTH_LONG).show();
                    adapter.setPasscodeList(Model.getTempPasscodes());
                } else if (title.equals(_repeat)) {
                    currentTab = "Repeat";
                  //  Toast.makeText(getContext(), _repeat, Toast.LENGTH_LONG).show();
                    adapter.setPasscodeList(Model.getRepeatPasscodes());
                } else if (title.equals(_one_time)) {
                    currentTab = "One-Time";
                   // Toast.makeText(getContext(), _one_time, Toast.LENGTH_LONG).show();
                    adapter.setPasscodeList(Model.getOnePasscodes());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        List<Passcode> inputList = Model.getPermanentPasscodes();
        if (currentTab.equals("Temporary")) {
            inputList = Model.getTempPasscodes();
        } else if (currentTab.equals("Repeat")) {
            inputList = Model.getRepeatPasscodes();
        } else if (currentTab.equals("One-Time")) {
            inputList = Model.getOnePasscodes();
        }
        adapter = new PasscodeRecyclerAdapter(getContext(), inputList);
        adapter.setListener(new ItemClickedListener<Passcode>() {
            List<Passcode> list = new ArrayList<>();
            @Override
            public void itemChosen(int position) {
                list = adapter.getPasscodeList();
                Passcode passcode = list.get(position);
                Toast.makeText(getContext(), passcode.getName() + " is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), EditPasscodeActivity.class);
                intent.putExtra("Name", passcode.getName());
                intent.putExtra("Used Count", passcode.getUsedCount());
                intent.putExtra("Creation Time", passcode.getCreationTime());
                intent.putExtra("Enabled", passcode.isEnabled());
                intent.putExtra("Number", passcode.getNumber());
                intent.putExtra("Type", passcode.getType().toString());
                intent.putExtra("Position", position);
                intent.putExtra("Days", passcode.getDaysOfWeek());
                intent.putExtra("startDate", passcode.getStartDate());
                intent.putExtra("endDate", passcode.getEndDate());
                intent.putExtra("startTime", passcode.getStartTime());
                intent.putExtra("endTime", passcode.getEndTime());
                startActivity(intent);
            }

            @Override
            public void delItem(int position) {
                list = adapter.getPasscodeList();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void toggleItem(int position) {
                list = adapter.getPasscodeList();
                Passcode passcode = list.get(position);

                boolean status = passcode.isEnabled();
                Toast.makeText(getContext(), passcode.getName() + " status is now " + !status, Toast.LENGTH_SHORT).show();
                passcode.setEnabled(!status);
                adapter.notifyItemChanged(position);
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab) void onFabClick() {
        Toast.makeText(getContext(), "Adding passcode!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), EditPasscodeActivity.class));
    }

}
