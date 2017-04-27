package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.zenith.R;

/**
 * Created by archirayan on 20-Feb-17.
 */
public class LeaveDaysAdapter extends RecyclerView.Adapter<LeaveDaysAdapter.ViewHolder>
{
    // TODO: 21-Feb-17 Rujul Adapter for leave application Day
    public Context context;
    private int totalDays, usedDays;

    public LeaveDaysAdapter(Context context, int totalDays, int usedDays)
    {
        this.context = context;
        this.totalDays = totalDays;
        this.usedDays = usedDays;
    }

    @Override
    public LeaveDaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_leave_days, parent, false);
        LeaveDaysAdapter.ViewHolder viewHolder = new LeaveDaysAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeaveDaysAdapter.ViewHolder holder, final int position)
    {
        holder.numberTv.setText("" + (position + 1));
        if (position < usedDays) {
            holder.numberTv.setBackgroundResource(R.drawable.circle_blue);
        } else {
            holder.numberTv.setBackgroundResource(R.drawable.circle_grey);
        }
    }

    @Override
    public int getItemCount()
    {
        return totalDays;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView numberTv;
        public ViewHolder(View itemView) {
            super(itemView);
            numberTv = (TextView) itemView.findViewById(R.id.adapter_leave_days_count);
        }
    }
}
