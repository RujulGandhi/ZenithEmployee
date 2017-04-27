package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.com.zenith.Activity.Employee_Chart_PayrollActivity;
import app.com.zenith.Adapter.PayrollAdapter;
import app.com.zenith.Model.PayrollSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayrollFragment extends Fragment {
    // TODO Fragment Class For  Payroll Page  *******   Sanjay Umaraniya  **********
    private CompactCalendarView compactCalendarView;
    private ImageView adminpayroll_toolbar_img_chart;
    private ImageView adminpayroll_toolbar_homemenuimage;
    private TextView payroll_emptotalprice;
    private TextView payroll_emptotalhour;
    private String date1;
    private String date2;
    private TextView adminpayroll_enddate;
    private TextView adminpayroll_startdate;
    private ListView list;
    public PayrollSetget payrollSetget;
    public ArrayList<PayrollSetget> arrayList;
    public PayrollAdapter adapter;
    public Utils utils;
    private String str_url;
    private String strtotalprice;
    private String strtotalhour;
    private String Str_empname;
    private String Str_event_hourely_rate;
    private String Str_event_hours;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payroll, container, false);
        setHasOptionsMenu(true);
        utils = new Utils(getActivity());

        // TODO Initialization Variable...
        adminpayroll_startdate = (TextView) view.findViewById(R.id.adminpayroll_startdate);
        adminpayroll_enddate = (TextView) view.findViewById(R.id.adminpayroll_enddate);
        payroll_emptotalprice = (TextView) view.findViewById(R.id.payroll_emptotalprice);
        payroll_emptotalhour = (TextView) view.findViewById(R.id.payroll_emptotalhour);
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.admin_compactcalendar_view);
        list = (ListView) view.findViewById(R.id.employpayroll_fragment_listview);

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
            }
        });
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.payroll_menuitem_chart, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_payrollchart:

                Intent intent = new Intent(getActivity(), Employee_Chart_PayrollActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
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
            str_url = utils.getResponseofGet(Constant.BASE_URL + "admin_get_count_hours.php?start_date=" + date1 + "&end_date=" + date2);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {

                int count = 0;
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    payroll_emptotalprice.setText(jsonObject.getString("emp_total_price"));
                    payroll_emptotalhour.setText(jsonObject.getString("emp_total_hour"));
                    JSONArray jsonArray = jsonObject.getJSONArray("emp_detail");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject eventObject = jsonArray.getJSONObject(i);
                        if (eventObject.has("event_detail")) {
                            count++;
                            JSONArray eventArray = eventObject.getJSONArray("event_detail");
                            for (int k = 0; k < eventArray.length(); k++) {
                                JSONObject eventInfoObj = eventArray.getJSONObject(k);
                                payrollSetget = new PayrollSetget();
                                payrollSetget.setUeventname(eventObject.getString("emp_name"));
                                payrollSetget.setUhour(eventInfoObj.getString("total_hours"));
                                payrollSetget.setUincome(eventInfoObj.getString("event_hourly_rate"));
                                arrayList.add(payrollSetget);
                            }
                        }
                    }
                }
                Log.d("count@#", "" + count);
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