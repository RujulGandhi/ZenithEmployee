package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Activity.AdminJobInformation;
import app.com.zenith.Adapter.AdminjoblistAdpter;
import app.com.zenith.Model.AdminSetget;
import app.com.zenith.Model.EventDetails;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminJoblistFragment extends Fragment {
    ListView list;
    AdminSetget setget;
    ArrayList<AdminSetget> arrayList;
    Context context;
    Button admin_joblist_btnaddshift;
    public Utils utils;
    TextView adminjoblist_txteventcount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_joblist, container, false);
        Button admin_joblist_btnaddshift = (Button) view.findViewById(R.id.admin_joblist_addbtnshift);
        list = (ListView) view.findViewById(R.id.admin_joblist_lv);
        adminjoblist_txteventcount = (TextView) view.findViewById(R.id.admin_joblist_eventcount);

        utils = new Utils(getActivity());

        return view;
    }

    private class AdminGetJoblist extends AsyncTask<String, String, String> {
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
            return utils.getResponseofGet(Constant.BASE_URL + "get_job&shift.php");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "Admin Joblist..." + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    String totalEvent = jsonObject.getString("total_event");
                    adminjoblist_txteventcount.setText(totalEvent);
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject empobj = array.getJSONObject(i);
                        setget = new AdminSetget();
                        setget.setE_id(empobj.getString("emp_id"));
                        setget.setE_name(empobj.getString("emp_name"));
                        setget.setE_shift(empobj.getString("emp_shift"));
                        setget.setE_img(empobj.getString("emp_image"));
                        setget.setE_date(empobj.getString("emp_join_date"));

                        JSONArray datearray = empobj.getJSONArray("event_detail");
                        ArrayList<EventDetails> arrayEventDetails = new ArrayList<>();
                        for (int j = 0; j < datearray.length(); j++) {
                            JSONObject dateobj = datearray.getJSONObject(j);
                            EventDetails eventDetails = new EventDetails();

                            //JSONObject ProductName=Products.getJSONObject(j);
                            eventDetails.setEvent_date(dateobj.getString("event_date"));
                            eventDetails.setEvent_id(dateobj.getString("event_id"));
                            eventDetails.setEvent_name(dateobj.getString("event_name"));
                            eventDetails.setEvent_start_time(dateobj.getString("event_start_time"));
                            eventDetails.setEvent_end_time(dateobj.getString("event_end_time"));
                            eventDetails.setEvent_hourly_rate(dateobj.getString("event_hourly_rate"));
                            eventDetails.setEvent_colorcode(dateobj.getString("event_RGBColor_code"));

                            arrayEventDetails.add(eventDetails);

                        }
                        setget.setEventArray(arrayEventDetails);
                        arrayList.add(setget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                AdminjoblistAdpter adpter = new AdminjoblistAdpter(getActivity(), arrayList);
                list.setAdapter(adpter);
                adpter.notifyDataSetChanged();
                list.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                if (getActivity() != null) {
                                    Gson gson = new Gson();
                                    Intent in = new Intent(getActivity(), AdminJobInformation.class);
                                    in.putExtra("jobdetails", gson.toJson(arrayList.get(position)));
                                    getActivity().startActivity(in);
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new AdminGetJoblist().execute();
    }
}