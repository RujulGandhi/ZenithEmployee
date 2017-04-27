package app.com.zenith.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.zenith.Activity.Employee_Chart_PayrollActivity;
import app.com.zenith.Model.EmployeeListSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi_info on 3/21/2017.
 */
public class EmployeeListAdapter extends BaseAdapter
{
    // TODO Adapter Class For Employee List Page
    public Context context;
    public EmployeeListSetget employeeListSetget;
    public ArrayList<EmployeeListSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private CircleImageView employeelist_image;
    private TextView employeelist_name;
    private TextView employeelist_datetime;
    private ImageView emp_playroll_chart;

    public EmployeeListAdapter(Context context, ArrayList<EmployeeListSetget> arrayList) {
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
            view = inflater.inflate(R.layout.employee_cust_list, null);
        employeeListSetget = arrayList.get(position);
        employeelist_image = (CircleImageView) view.findViewById(R.id.employee_list_img);
        employeelist_name = (TextView) view.findViewById(R.id.employee_list_name);
        employeelist_datetime = (TextView) view.findViewById(R.id.employee_list_datetime);
        emp_playroll_chart = (ImageView) view.findViewById(R.id.employee_list_imageplayroll_chart);
        emp_playroll_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, Employee_Chart_PayrollActivity.class);
                context.startActivity(in);
            }

        });

        if (employeeListSetget.getEmp_listImage().isEmpty()) {
            employeelist_image.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(employeeListSetget.getEmp_listImage()).into(employeelist_image);
        }
        employeelist_name.setText(employeeListSetget.getEmp_listName());
        employeelist_datetime.setText(employeeListSetget.getEmp_listDatetime());
        return view;
    }
}