package com.team8303.smartbox.smartbox_users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;
import com.team8303.smartbox.R;
import com.team8303.smartbox.smartbox_history.BoxHistoryHeaderData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Myo on 5/25/2017.
 */

public class BoxUsersRecyclerAdapter extends StickHeaderRecyclerView<BoxUsersItem, BoxUsersHeaderData> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case BoxHistoryHeaderData.HEADER_TYPE_1:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_recycler, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_smartbox_users, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(position);
        } else if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).bindData(position);
        }
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        // this method is called when your header move and you must not only bind header data in HeaderViewHolder
        //but also bind header data here.
        BoxUsersHeaderData item = getHeaderDataInPosition(headerPosition);
        TextView tv = header.findViewById(R.id.header_text);
        tv.setText(item.getUserType());
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.header_text);
        }

        void bindData(int position) {
            BoxUsersHeaderData item = getHeaderDataInPosition(position);
            tvHeader.setText(item.getUserType());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindData(int position) {
            BoxUsersItem item = getDataInPosition(position);
            tv.setText(item.getName());

        }
    }
}

