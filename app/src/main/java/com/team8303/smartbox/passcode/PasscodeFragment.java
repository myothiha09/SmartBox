package com.team8303.smartbox.passcode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasscodeFragment extends Fragment {

    @BindView(R.id.passcode_recycler_view)
    RecyclerView recyclerView;
    public static PasscodeRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passcode, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        final List<Passcode> list = Model.permanentPasscodes;
        adapter = new PasscodeRecyclerAdapter(getContext(), list);
        adapter.setListener(new ItemClickedListener<Passcode>() {
            final List<Passcode> passcodes = Model.permanentPasscodes;

            @Override
            public void itemChosen(int position) {
                Passcode passcode = list.get(position);
                //Toast.makeText(getContext(), passcode.getName() + " is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), EditPasscodeActivity.class);
                startActivity(intent);
            }

            @Override
            public void delItem(int position) {
                return;
            }

            @Override
            public void toggleItem(int position) {
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
    }

}
