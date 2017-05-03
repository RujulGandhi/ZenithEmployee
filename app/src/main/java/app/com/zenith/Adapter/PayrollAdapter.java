package app.com.zenith.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.zenith.Model.PayrollSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

/**
 * Created by archi_info on 3/22/2017.
 */
public class PayrollAdapter extends BaseAdapter {
    // TODO Adapter Class For Payroll Page
    public Context context;
    public PayrollSetget payrollSetget;
    public ArrayList<PayrollSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private int id;
    private TextView payroll_uhourtotal;
    private TextView payroll_uhourerate;
    private TextView payroll_eventusername;


    public PayrollAdapter(Context context, ArrayList<PayrollSetget> arrayList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.arrayList = arrayList;
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
        View view = inflater.inflate(R.layout.payroll_cust_list, null);
        payrollSetget = arrayList.get(position);
        payroll_eventusername = (TextView) view.findViewById(R.id.payroll_eventusername);
        payroll_uhourtotal = (TextView) view.findViewById(R.id.payroll_uhouretotal);
        payroll_uhourerate = (TextView) view.findViewById(R.id.payroll_uhourerate);

        payroll_uhourtotal.setText(payrollSetget.getUhour() + "hrs");
        payroll_eventusername.setText(payrollSetget.getUeventname());
        payroll_uhourerate.setText("$" + payrollSetget.getUincome());
        return view;
    }
}