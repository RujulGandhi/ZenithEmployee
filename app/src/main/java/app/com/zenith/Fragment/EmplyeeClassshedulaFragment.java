package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.com.zenith.Adapter.EmployeeClasssheduleAdapter;
import app.com.zenith.Model.EmployeeClasssheduleeSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmplyeeClassshedulaFragment extends Fragment
{
    // TODO Fragment Class For Employee Home Page  *******   Sanjay Umaraniya  **********
    private ListView list;
    public EmployeeClasssheduleeSetget employeeClasssheduleeSetget;
    public ArrayList<EmployeeClasssheduleeSetget> arrayList;
    public EmployeeClasssheduleAdapter adapter;
    public Utils utils;
    private String str_userid;
    private String date;
    private String  str_url;
    private CompactCalendarView compactCalendarView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_emplyee_classshedula, container, false);
        utils = new Utils(getActivity());

        str_userid=utils.ReadSharePrefrence(getActivity(),Constant.USERID_STUDENT);
        arrayList = new ArrayList<>();
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(dateClicked);
                Log.d("Daate", date);
                utils = new Utils(getActivity());
                new EmployeeClassshedulelist().execute();
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
            }
        });
        return view;
    }

    private class EmployeeClassshedulelist extends AsyncTask<String, String, String>
    {
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
        protected String doInBackground(String... params)
        {
            str_url = utils.getResponseofGet(Constant.BASE_URL + "get_datewise_event.php?select_date=" + date);
            Log.d("Response", "" + str_url);
            return str_url;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true"))
                {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    Log.d("ARRAY",""+array.length());
                    for (int i = 0; i < array.length(); i++)
                    {

                        JSONObject jsonObj = array.getJSONObject(i);
                        employeeClasssheduleeSetget = new EmployeeClasssheduleeSetget();
                        date = jsonObj.getString("event_date");
                        employeeClasssheduleeSetget.setE_eventdate(jsonObj.getString("event_date"));
                        employeeClasssheduleeSetget.setE_event_name(jsonObj.getString("event_name"));
                        employeeClasssheduleeSetget.setE_event_add_time(jsonObj.getString("event_start_time"));
                        arrayList.add(employeeClasssheduleeSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
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
}