package com.team8303.smartbox.smartbox_history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.saber.stickyheader.stickyView.StickHeaderRecyclerView;
import com.team8303.model.Utils;
import com.team8303.smartbox.R;

import java.util.concurrent.locks.Lock;

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
        TextView time;
        @BindView(R.id.expand)
        ImageView expand;
        @BindView(R.id.lockStatusIcon)
        ImageView lockStatusIcon;
        @BindView(R.id.expanded_info)
        LinearLayout expandedInfo;

        @BindView(R.id.inputPasscode) TextView inputPasscode;
        @BindView(R.id.inputPasscodeType) TextView inputPasscodeType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bindData(int position) {
            BoxHistoryItem item = getDataInPosition(position);
            unlocker.setText(item.getUnlocker());
            time.setText(item.getTime());
            LockStatus currLockStatus = item.getLockStatus();
            lockStatus.setText(currLockStatus.toString());

            if (currLockStatus.equals(LockStatus.LOCKED)) {
                lockStatusIcon.setImageResource(R.drawable.ic_lock_black_24dp);
            } else if (currLockStatus.equals(LockStatus.ATTEMPTED_UNLOCK)) {
                lockStatusIcon.setImageResource(R.drawable.ic_lock_red_24dp);
            } else {
                lockStatusIcon.setImageResource(R.drawable.ic_lock_open_blue_24dp);
            }
            if (item.isExpanded()) {
                expand.setImageResource(R.drawable.ic_expand_less_black_24dp);
                expandedInfo.setVisibility(View.VISIBLE);
                inputPasscode.setText("Passcode: " + (currLockStatus.equals(LockStatus.LOCKED) ? "N/A" : item.getPasscode().getNumber()));
                inputPasscodeType.setText("Passcode Type: " + (currLockStatus.equals(LockStatus.UNLOCKED) ? item.getPasscode().getType().name() : "N/A"));
            } else {
                expand.setImageResource(R.drawable.ic_expand_more_black_24dp);
                expandedInfo.setVisibility(View.GONE);
            }

        }

        @OnClick (R.id.expand) void onClick() {
            int position = getAdapterPosition();
            BoxHistoryItem item = getDataInPosition(position);
            item.toggleExpanded();
            notifyItemChanged(position);
        }
    }
}

