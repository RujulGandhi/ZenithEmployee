package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import app.com.zenith.Activity.EmployeeList;
import app.com.zenith.Adapter.PayrollAdapter;
import app.com.zenith.Model.PayrollSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.Utils.Utils.isConnectingToInternet;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayrollFragment extends Fragment implements View.OnClickListener {
    // TODO Fragment Class For  Payroll Page  *******   Sanjay Umaraniya  **********
    private CompactCalendarView compactCalendarView;
    private TextView payroll_emptotalprice, payroll_emptotalhour, adminpayroll_enddate, adminpayroll_startdate, monthNameTv;
    private String date1, date2;
    private ListView list;
    public PayrollSetget payrollSetget;
    public ArrayList<PayrollSetget> arrayList;
    public PayrollAdapter adapter;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    public Utils utils;
    private String str_url;
    private Toolbar toolbar;
    private TextView toolBarTitleTv;
    private ImageView toolBarTitleImageIv, toolProfileIv;
    private ImageView previousMonth, nextMonth;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isConnectingToInternet(getActivity())) {
            new getAllEvent().execute();
        } else {
            Toast.makeText(getActivity(), getString(R.string.error_nointernet), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payroll, container, false);
        utils = new Utils(getActivity());

        toolbar = (Toolbar) getActivity().findViewById(R.id.admin_toolbar);

        toolBarTitleTv = (TextView) toolbar.findViewById(R.id.admin_toolbar_title);
        toolBarTitleTv.setVisibility(View.VISIBLE);

        toolBarTitleImageIv = (ImageView) toolbar.findViewById(R.id.adminhome_cust_titleimage);
        toolBarTitleImageIv.setVisibility(View.GONE);

        toolProfileIv = (ImageView) toolbar.findViewById(R.id.admin_toolbar_profile);
        toolProfileIv.setImageResource(R.drawable.payroll_char_shareimage);
        toolProfileIv.setVisibility(View.VISIBLE);
        toolProfileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeList.class);
                startActivity(intent);
            }
        });

        // TODO Initialization Variable...
        adminpayroll_startdate = (TextView) view.findViewById(R.id.adminpayroll_startdate);
        adminpayroll_enddate = (TextView) view.findViewById(R.id.adminpayroll_enddate);
        payroll_emptotalprice = (TextView) view.findViewById(R.id.payroll_emptotalprice);
        payroll_emptotalhour = (TextView) view.findViewById(R.id.payroll_emptotalhour);
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.admin_compactcalendar_view);
        list = (ListView) view.findViewById(R.id.employpayroll_fragment_listview);
        monthNameTv = (TextView) view.findViewById(R.id.activity_calendar_monthname);
        previousMonth = (ImageView) view.findViewById(R.id.activity_calendar_previousmonth);
        nextMonth = (ImageView) view.findViewById(R.id.activity_calendar_nextmonth);


        monthNameTv.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            public void onDayClick(Date dateClicked) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (adminpayroll_startdate.getText().toString().trim().length() == 0) {
                    date1 = sdf.format(dateClicked);
                    adminpayroll_startdate.setText(date1);
                } else if (adminpayroll_enddate.getText().toString().trim().length() == 0) {
                    date2 = sdf.format(dateClicked);
                    adminpayroll_enddate.setText(date2);
                    new EmployeehomegetEventList().execute();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthNameTv.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
            }
        });

        nextMonth.setOnClickListener(this);
        previousMonth.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_calendar_nextmonth:
                compactCalendarView.showNextMonth();
                break;
            case R.id.activity_calendar_previousmonth:
                compactCalendarView.showPreviousMonth();
                break;
        }
    }

    private class getAllEvent extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
//            admin_count_event_hours.php
            str_url = Constant.BASE_URL + "admin_count_event_hours.php";
            Log.d("url", str_url);
            return utils.getResponseofGet(str_url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    payroll_emptotalprice.setText(jsonObject.getString("emp_total_price"));
                    payroll_emptotalhour.setText(jsonObject.getString("emp_total_hour"));
                    JSONArray jsonArray = jsonObject.getJSONArray("job_detail");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject eventObject = jsonArray.getJSONObject(i);
                        payrollSetget = new PayrollSetget();
                        payrollSetget.setUeventname(eventObject.getString("emp_name"));
                        payrollSetget.setUhour(eventObject.getString("total_hours"));
                        payrollSetget.setUincome(eventObject.getString("event_hourly_rate"));
                        arrayList.add(payrollSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                adapter = new PayrollAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            }
        }
    }

    private class EmployeehomegetEventList extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
//            admin_count_event_hours.php
            str_url = utils.getResponseofGet(Constant.BASE_URL + "admin_count_event.php?start_date=" + date1 + "&end_date=" + date2);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    payroll_emptotalprice.setText(jsonObject.getString("emp_total_price"));
                    payroll_emptotalhour.setText(jsonObject.getString("emp_total_hour"));
                    JSONArray jsonArray = jsonObject.getJSONArray("job_detail");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject eventObject = jsonArray.getJSONObject(i);
                        payrollSetget = new PayrollSetget();
                        payrollSetget.setUeventname(eventObject.getString("emp_name"));
                        payrollSetget.setUhour(eventObject.getString("total_hours"));
                        payrollSetget.setUincome(eventObject.getString("event_hourly_rate"));
                        arrayList.add(payrollSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                adapter = new PayrollAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            }
        }
    }
}