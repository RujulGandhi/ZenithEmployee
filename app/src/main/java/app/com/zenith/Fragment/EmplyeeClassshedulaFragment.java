package app.com.zenith.Fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import app.com.zenith.Adapter.EmployeeClasssheduleAdapter;
import app.com.zenith.Model.EmployeeClasssheduleeSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmplyeeClassshedulaFragment extends Fragment {
    // TODO Fragment Class For Employee Home Page  *******   Sanjay Umaraniya  **********
    private ListView list;
    public EmployeeClasssheduleeSetget employeeClasssheduleeSetget;
    public ArrayList<EmployeeClasssheduleeSetget> arrayList;
    public EmployeeClasssheduleAdapter adapter;
    public Utils utils;
    private String str_userid;
    public final int REQUEST_WRITE_CALENDAR_CODE = 100;
    private String date;
    private String str_url;
    private CompactCalendarView compactCalendarView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emplyee_classshedula, container, false);
        utils = new Utils(getActivity());
        str_userid = utils.ReadSharePrefrence(getActivity(), Constant.USERID);
//        new EmployeeClassshedulelist().execute();

        arrayList = new ArrayList<>();
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
        return view;
    }

    private class EmployeeClassshedulelist extends AsyncTask<String, String, String> {
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
            str_url = utils.getResponseofGet(Constant.BASE_URL + "get_emp_event.php?emp_id=" + str_userid);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        employeeClasssheduleeSetget = new EmployeeClasssheduleeSetget();
                        date = jsonObj.getString("event_date");
                        employeeClasssheduleeSetget.setE_eventdate(jsonObj.getString("event_date"));
                        employeeClasssheduleeSetget.setE_event_name(jsonObj.getString("event_name"));
                        employeeClasssheduleeSetget.setE_event_add_time(jsonObj.getString("event_start_time"));
                        arrayList.add(employeeClasssheduleeSetget);
                        addToDeviceCalendar(jsonObj.getString("event_date"), jsonObj.getString("event_name"), "");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateTemp = null;
                        try {
                            dateTemp = dateFormat.parse(jsonObj.getString("event_date"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Event ev1 = new Event(Color.GREEN, dateTemp.getTime(), "Some extra data that I want to store.");
                        compactCalendarView.addEvent(ev1, true);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
            if (arrayList.size() > 0) {
                list = (ListView) getActivity().findViewById(R.id.empclassshedule_listview);
                adapter = new EmployeeClasssheduleAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addEvent(String dtstart, String title) {

        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dt = Calendar.getInstance();

        //where untilDate is a date instance of your choice,for example 30/01/2012
        try {
            dt.setTime(yyyyMMdd.parse(dtstart));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //if you want the event until 30/01/2012 we add one day from our day because UNTIL in RRule sets events Before the last day want for event
        dt.add(Calendar.DATE, 1);
        String dtUntill = yyyyMMdd.format(dt.getTime());


        ContentResolver cr = getActivity().getContentResolver();
        ContentValues values = new ContentValues();

        values.put(CalendarContract.Events.DTSTART, dtstart);
        values.put(CalendarContract.Events.TITLE, title);

        TimeZone timeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());

        // default calendar
        values.put(CalendarContract.Events.CALENDAR_ID, 1);

        values.put(CalendarContract.Events.RRULE, "FREQ=DAILY;UNTIL="
                + dtUntill);
        //for one hour
        values.put(CalendarContract.Events.DURATION, "+P1H");

        values.put(CalendarContract.Events.HAS_ALARM, 1);

        // insert event to calendar
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_WRITE_CALENDAR_CODE);
            return;
        } else {
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
        }

    }

    private void addToDeviceCalendar(String startDate, String title, String description) {

        String stDate = startDate;

        GregorianCalendar calDate = new GregorianCalendar();
        //GregorianCalendar calEndDate = new GregorianCalendar();

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
        Date date = null, edate;
        try {
            date = originalFormat.parse(startDate);
            stDate = targetFormat.format(date);

        } catch (ParseException ex) {
            Log.d("#@", ex.toString());
        }

        long startMillis = 0;
        long endMillis = 0;
        String dates[] = stDate.split(",");

//        SD_YeaR = dates[0];
//        SD_MontH = dates[1];
//        SD_DaY = dates[2];
//        SD_HouR = dates[3];
//        SD_MinutE = dates[4];


        /*Log.e("YeaR ", SD_YeaR);
        Log.e("MontH ",SD_MontH );
        Log.e("DaY ", SD_DaY);
        Log.e(" HouR", SD_HouR);
        Log.e("MinutE ", SD_MinutE);*/

//        calDate.set(Integer.parseInt("2017"), Integer.parseInt("06") - 1, Integer.parseInt("15"), Integer.parseInt("01"), Integer.parseInt("00"));
//        startMillis = calDate.getTimeInMillis();
/*
        try {
            edate = originalFormat.parse(endDate);
            enDate=targetFormat.format(edate);

        } catch (ParseException ex) {}


        String end_dates[] = endDate.split(",");

        String ED_YeaR = end_dates[0];
        String ED_MontH = end_dates[1];
        String ED_DaY = end_dates[2];

        String ED_HouR = end_dates[3];
        String ED_MinutE = end_dates[4];


        calEndDate.set(Integer.parseInt(ED_YeaR), Integer.parseInt(ED_MontH)-1, Integer.parseInt(ED_DaY), Integer.parseInt(ED_HouR), Integer.parseInt(ED_MinutE));
        endMillis = calEndDate.getTimeInMillis();*/

        try {
            ContentResolver cr = getActivity().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, date.getTime());
            values.put(CalendarContract.Events.DTEND, calDate.getTimeInMillis() + 60 * 60 * 1000);
            values.put(CalendarContract.Events.TITLE, title);
            values.put(CalendarContract.Events.DESCRIPTION, description);
//            values.put(CalendarContract.Events.EVENT_LOCATION, location);
            values.put(CalendarContract.Events.HAS_ALARM, 1);
            values.put(CalendarContract.Events.CALENDAR_ID, 1);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                    .getTimeZone().getID());
            System.out.println(Calendar.getInstance().getTimeZone().getID());
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

            long eventId = Long.parseLong(uri.getLastPathSegment());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("#@", e.toString());
        }
    }

}