package app.com.zenith.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.com.zenith.Model.LeaveDatum;
import app.com.zenith.R;

import static app.com.zenith.Utils.Utils.getLeaveStatus;

/**
 * Created by archirayan on 20-Feb-17.
 */
public class LeaveApplicationAdapter extends RecyclerView.Adapter<LeaveApplicationAdapter.ViewHolder> {
    // TODO: 21-Feb-17 Rujul Adapter for leave application
    public Context context;
    public List<LeaveDatum> leaveInfoList;

    public LeaveApplicationAdapter(Context context, List<LeaveDatum> leaveInfoList) {
        this.leaveInfoList = leaveInfoList;
        this.context = context;
    }

    @Override
    public LeaveApplicationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_leave_application, parent, false);
        LeaveApplicationAdapter.ViewHolder viewHolder = new LeaveApplicationAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeaveApplicationAdapter.ViewHolder holder, int position) {
        LeaveDatum details = leaveInfoList.get(position);
        String month = "";
        String date = "";
        String year = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMM");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        try {
            Date dateObj = sdf.parse(details.getAppliedDate());
            month = sdfMonth.format(dateObj);
            date = sdfDate.format(dateObj);
            year = sdfYear.format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.leaveReasonTv.setText(details.getLeaveName());
        String htmlCode = "<h6><font color=\"#FFFFFF\">Feb</font></h6><h3><b><font color=\"#000000\"><p>|17|</p></font></b></h3>";
        Spanned htmlSpann;
        if (Build.VERSION.SDK_INT >= 24) {
            htmlSpann = Html.fromHtml(htmlCode.replace("Feb", month).replace("|17|", date), 0); // for 24 api and more
        } else {
            htmlSpann = Html.fromHtml(htmlCode.replace("Feb", month).replace("|17|", date)); // or for older api
        }
        holder.calendarText.setText(htmlSpann);
        holder.durationTv.setText(details.getTotaldays());
        holder.dateTv.setText(details.getFromDate() + " - " + details.getToDate());
        holder.statusTv.setText(getLeaveStatus(details.getLeaveStatus()));
        holder.noteTv.setText(details.getLeaveNotes());
        holder.yearTv.setText(year);
        if (details.getLeaveAttachment().length() > 0)
            Glide.with(context).load(details.getLeaveAttachment()).placeholder(R.drawable.ic_placeholder).into(holder.leaveImage);

    }

    @Override
    public int getItemCount() {
        return leaveInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView leaveReasonTv, calendarText, durationTv, dateTv, statusTv, noteTv, yearTv;
        public ImageView leaveImage;

        public ViewHolder(View itemView) {
            super(itemView);
            leaveReasonTv = (TextView) itemView.findViewById(R.id.adapter_reason_leave);
            calendarText = (TextView) itemView.findViewById(R.id.adapter_leave_appliacation_date);
            yearTv = (TextView) itemView.findViewById(R.id.adapter_leave_appliacation_year);
            durationTv = (TextView) itemView.findViewById(R.id.adapter_reason_duration);
            dateTv = (TextView) itemView.findViewById(R.id.adapter_reason_date);
            statusTv = (TextView) itemView.findViewById(R.id.adapter_reason_status);
            noteTv = (TextView) itemView.findViewById(R.id.adapter_reason_note);
            leaveImage = (ImageView) itemView.findViewById(R.id.adapter_leave_application_image);
        }
    }
}
