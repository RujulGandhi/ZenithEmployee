package app.com.zenith.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Adapter.AdminLeaveAppsAdapter;
import app.com.zenith.Model.AdminleavelppSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLeaveFragment extends Fragment {
    // TODO Fragment Class For Admin Leave Page  *******   Sanjay Umaraniya  **********
    private ListView list;
    public AdminleavelppSetget adminleavelppSetget;
    public ArrayList<AdminleavelppSetget> arrayList;
    public Context context;
    public AdminLeaveAppsAdapter adapter;
    public Utils utils;
    private String strUserId;
    private String str_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_leave, container, false);
        utils = new Utils(getActivity());
        new adminLeaveGetList().execute();
        return view;
    }


    private class adminLeaveGetList extends AsyncTask<String, String, String> {
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
            str_url = utils.getResponseofGet(Constant.BASE_URL + "employee_leave_data.php");
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
                    JSONArray array = jsonObject.getJSONArray("leave_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        adminleavelppSetget = new AdminleavelppSetget();
                        adminleavelppSetget.setAdminLeeve_Id(jsonObj.getString("leave_id"));
                        adminleavelppSetget.setAdminLeave_Status(jsonObj.getString("leave_status"));
                        adminleavelppSetget.setAdminLeave_Name(jsonObj.getString("emp_name"));
                        adminleavelppSetget.setAdminLeave_Image(jsonObj.getString("emp_image"));
                        adminleavelppSetget.setAdminLeave_ToDate(jsonObj.getString("to_date"));
                        adminleavelppSetget.setAdminLeave_TotalLeaveDay(jsonObj.getString("leave_days"));
                        adminleavelppSetget.setAdminLeave_Leavetype(jsonObj.getString("leave_name"));
                        adminleavelppSetget.setAdminLeave_leave_notes(jsonObj.getString("leave_notes"));
                        adminleavelppSetget.setAdminLeave_FromDate(jsonObj.getString("from_date"));
                        adminleavelppSetget.setAdminLeave_DaysAvailable(jsonObj.getString("days_available"));
                        adminleavelppSetget.setAdminLeave_Leavedate(jsonObj.getString("applied_date"));
                        adminleavelppSetget.setAdminDate(jsonObj.getString("applied_date"));
                        arrayList.add(adminleavelppSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                adapter = new AdminLeaveAppsAdapter(getActivity(), arrayList);
                list = (ListView) getActivity().findViewById(R.id.adminleavapp_listview);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}