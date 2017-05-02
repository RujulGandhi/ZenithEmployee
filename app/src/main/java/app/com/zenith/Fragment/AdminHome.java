package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.com.zenith.Activity.AdminEmployeeListwithoutchartActivity;
import app.com.zenith.Activity.StudentListActivity;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.Utils.Constant.BASE_URL;

/**
 * Created by archirayan on 15-Feb-17.
 */

public class AdminHome extends Fragment {
    // TODO Fragment Class For Admin Home Page  *******   Sanjay Umaraniya  **********
    private Utils utils;
    private LinearLayout classshedual_layoutid;
    private LinearLayout classshedual_layoutid3;
    private TextView total_emp_event_txt;
    private TextView total_std_event_txt;
    public CompactCalendarView calendarView;
    public Button adminhome_showemployeelist;
    private Toolbar toolbar;
    private TextView textToolHeader;
    private ImageView textToolHeaderIv, toolProfileIv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        utils = new Utils(getActivity());
        calendarView = (CompactCalendarView) view.findViewById(R.id.admin_compactcalendar_view);
//        setHasOptionsMenu(true);
        toolbar = (Toolbar) getActivity().findViewById(R.id.admin_toolbar);

        textToolHeader = (TextView) toolbar.findViewById(R.id.admin_toolbar_title);
        textToolHeader.setVisibility(View.GONE);

        textToolHeaderIv = (ImageView) toolbar.findViewById(R.id.adminhome_cust_titleimage);
        textToolHeaderIv.setVisibility(View.VISIBLE);

        toolProfileIv = (ImageView) toolbar.findViewById(R.id.admin_toolbar_profile);
        toolProfileIv.setVisibility(View.VISIBLE);

        total_emp_event_txt = (TextView) view.findViewById(R.id.admin_total_emp_event);
        total_std_event_txt = (TextView) view.findViewById(R.id.admin_total_std_event);
        //admin_addjobshift=find0.3.0
        classshedual_layoutid = (LinearLayout) view.findViewById(R.id.classshedual_layoutid1);
        classshedual_layoutid3 = (LinearLayout) view.findViewById(R.id.classshedual_layoutid3);
        adminhome_showemployeelist = (Button) view.findViewById(R.id.adminhome_showemployeelist);
        total_emp_event_txt.setText(utils.ReadSharePrefrence(getActivity(), Constant.ADMIN_TOTAL_EMP));
        total_std_event_txt.setText(utils.ReadSharePrefrence(getActivity(), Constant.ADMIN_TOTAL_STD));

        classshedual_layoutid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentListActivity.class);
                startActivity(intent);
            }
        });

        classshedual_layoutid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminEmployeeListwithoutchartActivity.class);
                startActivity(intent);
            }
        });
        adminhome_showemployeelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminEmployeeListwithoutchartActivity.class);
                startActivity(intent);
            }
        });
        new GetEventList().execute();
        return view;
    }

    private class GetEventList extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return utils.getResponseofGet(BASE_URL + "get_all_job_shift.php");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("status").equals("true")) {

                    JSONArray eventArray = object.getJSONArray("event_detail");
                    for (int i = 0; i < eventArray.length(); i++) {

                        JSONObject eventObj = eventArray.getJSONObject(i);
                        Date date = null;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = dateFormat.parse(eventObj.getString("event_date"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Event ev1 = new Event(Color.GREEN, date.getTime(), "Some extra data that I want to store.");
                        calendarView.addEvent(ev1);
                    }

                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            pd.dismiss();
        }
    }
}