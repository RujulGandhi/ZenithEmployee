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
import app.com.zenith.Activity.EmployeeList;
import app.com.zenith.Adapter.AdminjoblistAdpter;
import app.com.zenith.Model.AdminSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.R.layout.fragment_admin_joblist;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminJoblistFragment extends Fragment {
    // TODO Fragment Class For Admin Job List Page  *******   Sanjay Umaraniya  **********
    public ListView list;
    public AdminSetget setget;
    public ArrayList<AdminSetget> arrayList;
    public AdminjoblistAdpter adpter;
    public Context context;
    public Utils utils;
    private TextView adminjoblist_txteventcount;
    private Button admin_joblist_btnaddshift;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(fragment_admin_joblist, container, false);
        utils = new Utils(getActivity());
        admin_joblist_btnaddshift = (Button) view.findViewById(R.id.admin_joblist_addbtnshift);
        adminjoblist_txteventcount = (TextView) view.findViewById(R.id.admin_joblist_eventcount);

        new AdminGetJoblist().execute();

        admin_joblist_btnaddshift.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(getActivity(), EmployeeList.class);
                getActivity().startActivity(in);
            }
        });
        return view;
    }


    private class AdminGetJoblist extends AsyncTask<String, String, String>
    {
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
            Log.d("RESPONSE...", "" + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true"))
                {
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    String totalEvent = String.valueOf(array.length());
                    adminjoblist_txteventcount.setText(totalEvent + " Jobs");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject empobj = array.getJSONObject(i);
                        setget = new AdminSetget();
                        setget.setE_id(empobj.getString("emp_id"));
                        setget.setE_shift(empobj.getString("emp_shift"));
                        setget.setE_name(empobj.getString("emp_name"));
                        setget.setE_shift(empobj.getString("emp_shift"));
                        setget.setE_img(empobj.getString("emp_image"));
                        JSONArray datearray = empobj.getJSONArray("event_detail");
                        for (int j = 0; j < datearray.length(); j++)
                        {
                            JSONObject dateobj = datearray.getJSONObject(j);
                            setget.setE_date(dateobj.getString("event_date"));
                            setget.setEvent_id(dateobj.getString("event_id"));
                            setget.setEvent_name(dateobj.getString("event_name"));
                            setget.setEvent_start_time(dateobj.getString("event_start_time"));
                            setget.setEvent_end_time(dateobj.getString("event_end_time"));
                            setget.setEvent_hourly_rate(dateobj.getString("event_hourly_rate"));
                            setget.setEvent_colorcode(dateobj.getString("event_RGBColor_code"));
                        }
                        Log.d("JobDetailspage", "" + arrayList.add(setget));
                        arrayList.add(setget);


                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0)
            {
                list = (ListView) getActivity().findViewById(R.id.admin_joblist_lv);
                adpter = new AdminjoblistAdpter(getActivity(), arrayList);
                list.setAdapter(adpter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Toast.makeText(getActivity(), ""+setget.getE_id(), Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        Intent in = new Intent(getActivity(), AdminJobInformation.class);
                        in.putExtra("jobdetails", gson.toJson(setget));
                        getActivity().startActivity(in);

                    }
                });
            }
            else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}