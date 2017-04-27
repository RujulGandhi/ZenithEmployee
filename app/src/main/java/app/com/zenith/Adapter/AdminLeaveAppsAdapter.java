package app.com.zenith.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.zenith.Activity.AdminLeaveAppsListActivity;
import app.com.zenith.Model.AdminleavelppSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.com.zenith.R.id.admin_leaveapplication_leavetype;
import static app.com.zenith.R.id.admin_leaveapplication_totalday;

/**
 * Created by archi_info on 3/30/2017.
 */
public class AdminLeaveAppsAdapter extends BaseAdapter {
    public Context context;
    public AdminleavelppSetget adminleavelppSetget;
    public ArrayList<AdminleavelppSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private String Id;
    private String name;
    private String date;
    private LinearLayout admin_leaveapplication_layout_id;
   private CircleImageView adminleaveapps_image;
   private TextView adminleaveapps_Name;
   private TextView adminleaveapps_Date;
   private TextView adminleaveapps_TotalLeaveDay;
   private TextView adminleaveapps_Leavtype;

    public AdminLeaveAppsAdapter(FragmentActivity context, ArrayList<AdminleavelppSetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Log.d("Length", "" + arrayList.size());
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        utils = new Utils(context);
        if (convertView == null)
            view = inflater.inflate(R.layout.admin_custlayout_leaveapplication, null);
        adminleavelppSetget = arrayList.get(position);
        admin_leaveapplication_layout_id = (LinearLayout) view.findViewById(R.id.admin_leaveapplication_layout_id);
        adminleaveapps_image = (CircleImageView) view.findViewById(R.id.admin_leaveapplication_img);
        adminleaveapps_Name = (TextView) view.findViewById(R.id.admin_leaveapplication_name);
        adminleaveapps_Date = (TextView) view.findViewById(R.id.admin_leaveapplication_date);
        adminleaveapps_TotalLeaveDay = (TextView) view.findViewById(admin_leaveapplication_totalday);
        adminleaveapps_Leavtype = (TextView) view.findViewById(admin_leaveapplication_leavetype);


        adminleaveapps_Name.setText(adminleavelppSetget.getAdminLeave_Name());
        adminleaveapps_Date.setText(adminleavelppSetget.getAdminLeave_Leavedate());
        adminleaveapps_Leavtype.setText(adminleavelppSetget.getAdminLeave_Leavetype());
        adminleaveapps_TotalLeaveDay.setText(adminleavelppSetget.getAdminLeave_TotalLeaveDay());
        if (adminleavelppSetget.getAdminLeave_Image().isEmpty()) {
            adminleaveapps_image.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(adminleavelppSetget.getAdminLeave_Image()).into(adminleaveapps_image);
        }
        adminleaveapps_Name.setText(adminleavelppSetget.getAdminLeave_Name());
        admin_leaveapplication_layout_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Id = adminleavelppSetget.getAdminLeeve_Id();
                Intent in = new Intent(context, AdminLeaveAppsListActivity.class);
                in.putExtra("id", arrayList.get(position).getAdminLeeve_Id());
                in.putExtra("Name", arrayList.get(position).getAdminLeave_Name());
                in.putExtra("Leavestatus", arrayList.get(position).getAdminLeave_Status());
                in.putExtra("leave_type", arrayList.get(position).getAdminLeave_Leavetype());
                in.putExtra("FromDate", arrayList.get(position).getAdminLeave_FromDate());
                in.putExtra("ToDate", arrayList.get(position).getAdminLeave_ToDate());
                in.putExtra("DaysUsed", arrayList.get(position).getAdminLeave_TotalLeaveDay());
                in.putExtra("LeaveNotes", arrayList.get(position).getAdminLeave_leave_notes());
                in.putExtra("DaysAvailable", arrayList.get(position).getAdminLeave_DaysAvailable());
                in.putExtra("appliedDate", arrayList.get(position).getAdminDate());
                in.putExtra("leavedays", arrayList.get(position).getAdminLeave_TotalLeaveDay());
                in.putExtra("LeaveImage", arrayList.get(position).getAdminLeave_Image());
                in.putExtra("emp_image", arrayList.get(position).getAdminLeave_Image());
                context.startActivity(in);
            }
        });
        return view;
    }
}