package com.team8303.smartbox.passcode;

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
import com.team8303.smartbox.R;
import com.team8303.util.ItemClickedListener;

import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class PasscodeRecyclerAdapter
        extends RecyclerView.Adapter<PasscodeRecyclerAdapter.ViewHolder> {
    private List<Passcode> list;
    private Context context;
    private ItemClickedListener<Passcode> listener;

    public PasscodeRecyclerAdapter(Context context, List<Passcode> list) {
        this.context = context;
        this.list = list;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_passcode, parent, false);

        return new ViewHolder(itemView);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Passcode current = list.get(position);
        holder.passcodeName.setText(current.getName());
        holder.creationPeriod.setText("Created: " + current.getCreationTime());
        holder.usedCount.setText("Used " + current.getUsedCount() + " time(s)");
        holder.toggle.setChecked(current.isEnabled());
    }

    @Override public int getItemCount() {
        return list.size();
    }

    public void setListener(ItemClickedListener<Passcode> listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.passcodeName) TextView passcodeName;
        @BindView(R.id.creationTimeValidPeriod) TextView creationPeriod;
        @BindView(R.id.usedCount) TextView usedCount;
        @BindView(R.id.toggleEnabled) Switch toggle;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick (R.id.passcode_card) void onClickPasscode() {
            int position = getAdapterPosition();
            listener.itemChosen(position);
        }

        @OnClick (R.id.toggleEnabled) void onToggle() {
            int position = getAdapterPosition();
            listener.toggleItem(position);

        }
    }
}
