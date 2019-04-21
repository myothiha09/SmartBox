package com.team8303.smartbox.active_smartboxes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.team8303.model.Passcode;
import com.team8303.model.PasscodeType;
import com.team8303.smartbox.R;
import com.team8303.util.ItemClickedListener;

import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class ActiveSmartBoxesAdapter
        extends RecyclerView.Adapter<ActiveSmartBoxesAdapter.ViewHolder> {
    private List<Smartbox> list;
    private Context context;
    private ItemClickedListener<Passcode> listener;

    public ActiveSmartBoxesAdapter(Context context, List<Smartbox> list) {
        this.context = context;
        this.list = list;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_active_smartboxes, parent, false);

        return new ViewHolder(itemView);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Smartbox current = list.get(position);
        holder.smartboxID.setText("ID: " + current.getId());
        holder.smartboxName.setText(current.getName());
        holder.smartboxUserCount.setText("Users: " + current.getCount());



    }
    public void setListener(ItemClickedListener listener) {
        this.listener = listener;
    }

    @Override public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.smartboxID) TextView smartboxID;
        @BindView(R.id.smartboxUserCount) TextView smartboxUserCount;
        @BindView(R.id.smartboxName) TextView smartboxName;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick (R.id.smartbox_card) void onClick() {
            listener.itemChosen(getAdapterPosition());
        }
    }
}
