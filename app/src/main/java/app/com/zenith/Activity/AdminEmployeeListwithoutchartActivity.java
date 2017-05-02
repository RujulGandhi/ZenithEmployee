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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Adapter.EmployeeListWithoutChartAdapter;
import app.com.zenith.Model.EmployeeListSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

public class AdminEmployeeListwithoutchartActivity extends AppCompatActivity {
    // TODO Activity for Employee List without Chart    **** Sanjay Umaraniya *******
    private ListView list;
    public EmployeeListSetget employeeListSetget;
    public ArrayList<EmployeeListSetget> arrayList;
    public Context context = this;
    public Utils utils;
    private EmployeeListWithoutChartAdapter adapter;
    public ImageView employeelist_shareimage, backbtn;
    public Button btn_showchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_listwithoutchart);
        utils = new Utils(AdminEmployeeListwithoutchartActivity.this);
        backbtn = (ImageView) findViewById(R.id.employeelist_backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_showchart = (Button) findViewById(R.id.admin_employee_list_btnshowchart);
        btn_showchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminEmployeeListwithoutchartActivity.this, AddEmployeeActivity.class);
                startActivity(in);
            }
        });
        new EmployeegetListwithoutchart().execute();
    }

    private class EmployeegetListwithoutchart extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return utils.getResponseofGet(Constant.BASE_URL + "get_employee_list.php");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("employee_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        employeeListSetget = new EmployeeListSetget();
                        employeeListSetget.setEmp_listId(jsonObj.getString("emp_id"));
                        employeeListSetget.setEmp_listName(jsonObj.getString("emp_name"));
                        employeeListSetget.setEmp_listImage(jsonObj.getString("emp_image"));
                        employeeListSetget.setEmp_listDatetime(jsonObj.getString("emp_join_date"));
                        arrayList.add(employeeListSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (arrayList.size() > 0) {
                list = (ListView) findViewById(R.id.employee_getlistview);
                adapter = new EmployeeListWithoutChartAdapter(context, arrayList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent in = new Intent(AdminEmployeeListwithoutchartActivity.this, JobsAndShiftActvity.class);
                        in.putExtra("Name", String.valueOf((arrayList.get(i).getEmp_listName())));
                        in.putExtra("Id", arrayList.get(i).getEmp_listId());
                        startActivity(in);

                    }
                });
            } else {
                Toast.makeText(context, "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
