package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.zenith.Model.EmployeeClasssheduleeSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

/**
 * Created by archi_info on 3/24/2017.
 */
public class EmployeeClasssheduleAdapter extends BaseAdapter {
    // TODO Adapter Class For Employee Home Page
    public Context context;
    public EmployeeClasssheduleeSetget employeeClasssheduleeSetget;
    public ArrayList<EmployeeClasssheduleeSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView employeeclassshedule_date;
    private TextView employeeclassshedule_shiftname;
    private TextView employeeclassshedule_time;

    public EmployeeClasssheduleAdapter(FragmentActivity context, ArrayList<EmployeeClasssheduleeSetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Log.d("Length", "" + arrayList.size());
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        utils = new Utils(context);
        if (convertView == null)
            view = inflater.inflate(R.layout.employeeclassshedule_cust_list, null);
        employeeClasssheduleeSetget = arrayList.get(position);
        employeeclassshedule_date = (TextView) view.findViewById(R.id.emplyeeclassschedule_date);
        employeeclassshedule_shiftname = (TextView) view.findViewById(R.id.emplyeeclassschedule_shiftname);
        employeeclassshedule_time = (TextView) view.findViewById(R.id.emplyeeclassschedule_time);

        employeeclassshedule_date.setText(employeeClasssheduleeSetget.getE_eventdate());
        employeeclassshedule_shiftname.setText(employeeClasssheduleeSetget.getE_event_name());
        employeeclassshedule_time.setText(employeeClasssheduleeSetget.getE_event_add_time());
        return view;
    }
}
