package com.team8303.smartbox.smartbox_users;

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
import com.team8303.model.UserType;
import com.team8303.smartbox.R;
import com.team8303.smartbox.smartbox_history.BoxHistoryHeaderData;
import com.team8303.smartbox.smartbox_history.BoxHistoryItem;
import com.team8303.smartbox.smartbox_history.BoxHistoryRecyclerAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BoxUsersFragment extends Fragment {
    @BindView(R.id.users_recycler_view)
    RecyclerView recyclerView;
    public static BoxUsersRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box_users, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BoxUsersRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        HashMap<UserType, List<BoxUsersItem>> hm = Model.getBoxUsers();
        for (UserType ut: hm.keySet()) {
            List<BoxUsersItem> list = hm.get(ut);
            BoxUsersHeaderData headerData1 = new BoxUsersHeaderData(BoxUsersHeaderData.HEADER_TYPE_1, R.layout.header_item_recycler, ut);
            adapter.setHeaderAndData(list, headerData1);
        }

    }
}
