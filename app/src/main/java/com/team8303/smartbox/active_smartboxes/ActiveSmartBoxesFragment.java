package com.team8303.smartbox.active_smartboxes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.team8303.model.Model;
import com.team8303.smartbox.LockManagementFragment;
import com.team8303.smartbox.R;
import com.team8303.smartbox.passcode.PasscodeRecyclerAdapter;
import com.team8303.util.ItemClickedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActiveSmartBoxesFragment extends Fragment {

    @BindView(R.id.activeSmartboxes)
    RecyclerView recyclerView;

    public static ActiveSmartBoxesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_smartboxes, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }
    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Model.getActiveSmartboxes();
        adapter = new ActiveSmartBoxesAdapter(getContext(), new ArrayList<Smartbox>());
        adapter.setListener(new ItemClickedListener() {
            @Override
            public void itemChosen(int position) {
                Toast.makeText(getContext(), list.get(position).getName(), Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new LockManagementFragment()).addToBackStack(null).commit();
                //will switch to same box no matter what box is chosen. can pass in the smartbox id to the fragment which can be used to retrieve more infos inside the fragment for specific box in final one.
            }

            @Override
            public void delItem(int position) {

            }

            @Override
            public void toggleItem(int position) {

            }
        });
        recyclerView.setAdapter(adapter);
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
}
