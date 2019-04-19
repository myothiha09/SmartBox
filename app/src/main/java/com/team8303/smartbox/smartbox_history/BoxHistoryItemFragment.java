package com.team8303.smartbox.smartbox_history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team8303.smartbox.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BoxHistoryItemFragment extends Fragment {
    @BindView(R.id.history_recycler_view)
    RecyclerView recyclerView;
    public static BoxHistoryRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smartbox_history, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        HeaderDataImpl headerData1 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.header_item_recycler, "Date " + 1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BoxHistoryRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        List<BoxHistoryItem> items = new ArrayList<>();
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        adapter.setHeaderAndData(items, headerData1);

        headerData1 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.header_item_recycler, "Date " + 2);
        items = new ArrayList<>();
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        adapter.setHeaderAndData(items, headerData1);

        headerData1 = new HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.header_item_recycler, "Date " + 3);
        items = new ArrayList<>();
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        items.add(new BoxHistoryItem());
        adapter.setHeaderAndData(items, headerData1);
    }
}
