package com.team8303.smartbox.smartbox_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;
import com.team8303.smartbox.R;

/**
 * Created by Myo on 5/25/2017.
 */

public class BoxHistoryRecyclerAdapter extends StickHeaderRecyclerView<BoxHistoryItem, BoxHistoryHeaderData> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case BoxHistoryHeaderData.HEADER_TYPE_1:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item_recycler, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_smartbox_history, parent, false));
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
        BoxHistoryHeaderData item = getHeaderDataInPosition(headerPosition);
        TextView tv = header.findViewById(R.id.header_text);
        tv.setText(item.getDate());
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.header_text);
        }

        void bindData(int position) {
            BoxHistoryHeaderData item = getHeaderDataInPosition(position);
            tvHeader.setText(item.getDate());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.unlocker)
        TextView unlocker;
        @BindView(R.id.lockStatus)
        TextView lockStatus;
        @BindView(R.id.time)
        TextView Time;
        @BindView(R.id.expand)
        ImageView expand;
        @BindView(R.id.lock_status)
        ImageView lockStatusIcon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindData(int position) {
            BoxHistoryItem item = getDataInPosition(position);
            unlocker.setText(item.getUnlocker());
        }
    }
}

