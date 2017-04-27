package app.com.zenith.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.zenith.Model.AdminSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

/**
 * Created by archi_info on 3/9/2017.
 */

public class AdminjobinformationAdapter extends BaseAdapter
{
    // TODO Adpater Class For Admin Job information Page
    public Context context;
    public AdminSetget adminjobinfoSetget;
    public ArrayList<AdminSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView admin_jobinformation_eventname;
    private TextView admin_jobinformation_startdate;
    private TextView admin_jobinformation_enddate;
    private TextView admin_jobinformation_hourerate;
    private TextView admin_jobinformation_colorcode;



    public AdminjobinformationAdapter(Context context, ArrayList<AdminSetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Log.d("Length", "" + arrayList.size());
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        utils = new Utils(context);

        if (convertView == null)
            vi = inflater.inflate(R.layout.admin_cust_jobinformation, null);
        adminjobinfoSetget = arrayList.get(position);
        admin_jobinformation_eventname = (TextView) vi.findViewById(R.id.admin_jobinformation_eventname);
        admin_jobinformation_startdate = (TextView) vi.findViewById(R.id.admin_jobinformation_startdate);
        admin_jobinformation_enddate = (TextView) vi.findViewById(R.id.admin_jobinformation_enddate);
        admin_jobinformation_hourerate = (TextView) vi.findViewById(R.id.admin_jobinformation_hourerate);
        admin_jobinformation_colorcode = (TextView) vi.findViewById(R.id.admin_jobinformation_colorcode);


        admin_jobinformation_eventname.setText(adminjobinfoSetget.getEvent_name());
        admin_jobinformation_startdate.setText(adminjobinfoSetget.getEvent_start_time());
        admin_jobinformation_enddate.setText(adminjobinfoSetget.getEvent_end_time());
        admin_jobinformation_hourerate.setText(adminjobinfoSetget.getEvent_hourly_rate());
        admin_jobinformation_colorcode.setText(adminjobinfoSetget.getEvent_colorcode());

        return vi;
    }
}

