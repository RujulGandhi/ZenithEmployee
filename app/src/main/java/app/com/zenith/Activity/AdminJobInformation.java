package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Adapter.AdminjobinformationAdapter;
import app.com.zenith.Model.AdminSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

public class AdminJobInformation extends AppCompatActivity {
    // TODO Activity for AdminJobInformation Page  **** Sanjay Umaraniya *******
    private TextView admin_jobinformation_empname;
    private TextView admin_jobinformation_eventcount;
    private Button admin_jobinformation_btnaddshift;
    private ListView admin_jobinformation_lv;
    private AdminSetget adminSetget;
    private ArrayList<AdminSetget> arrayList;
    private Context context = this;
    private ImageView backbtn;
    private Button admin_jobinformation_btnemplist;
    public Utils utils;
    private String name;
    private String id;
    private AdminjobinformationAdapter adpter;
    private String totalshift;

    private String event_eventid;
    private String event_colorcode;
    private String event_name;
    private String event_start_time;
    private String event_end_time;
    private String event_hourly_rate;


    private TextView job_eventname;


    //TODO    Zenith App ********** Sanjay Umaraniya *************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_job_information);
        backbtn = (ImageView) findViewById(R.id.adminjobinformation_toolbar_backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin_jobinformation_eventcount = (TextView) findViewById(R.id.admin_jobinformation_eventcount);
        admin_jobinformation_empname = (TextView) findViewById(R.id.admin_jobinformation_empname);
        admin_jobinformation_btnemplist = (Button) findViewById(R.id.admin_jobinformation_btnemplist);
        admin_jobinformation_btnemplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, EmployeeList.class));
                finish();
            }
        });

        utils = new Utils(context);
        new AdminGetJobInformation().execute();
    }

    private class AdminGetJobInformation extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<>();
            pd = new ProgressDialog(AdminJobInformation.this);
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
            // Delete API http://181.224.157.105/~hirepeop/host2/zenith_coach/api/delete_employee_event.php?event_id=


            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        JSONArray datearray = jsonObj.getJSONArray("event_detail");
                        for (int j = 0; j < datearray.length(); j++) {
                            JSONObject secobj = datearray.getJSONObject(j);
                            if (secobj.isNull("club")) {
                            /*    adminSetget = new AdminSetget();
                                adminSetget.setEvent_name(event_name);*/

                                if (getIntent().getExtras() != null)
                                {
                                    Gson gson = new Gson();
                                    String strObj = getIntent().getExtras().getString("jobdetails");
                                    adminSetget = gson.fromJson(strObj, AdminSetget.class);
                                    admin_jobinformation_eventcount.setText(adminSetget.getEvent_name());

                                    adminSetget.getEvent_name();
                                    adminSetget.getE_shift();
                                     adminSetget.getEvent_id();
                                    adminSetget.getEvent_start_time();
                                    adminSetget.getEvent_end_time();
                                    adminSetget.getEvent_hourly_rate();
                                    adminSetget.getEvent_colorcode();
                                } else {
                                    Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        arrayList.add(adminSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                admin_jobinformation_lv = (ListView) AdminJobInformation.this.findViewById(R.id.admin_jobinformation_lv);
                adpter = new AdminjobinformationAdapter(context, arrayList);
                admin_jobinformation_lv.setAdapter(adpter);
                admin_jobinformation_lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, ""+id+name, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else {
                Toast.makeText(context, "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}