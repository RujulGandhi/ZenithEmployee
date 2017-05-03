package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.com.zenith.Adapter.StudentListAdapter;
import app.com.zenith.Model.StudentListSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.Utils.Utils.isConnectingToInternet;

public class StudentListActivity extends AppCompatActivity {
    // TODO Activity for Student List Page  **** Sanjay Umaraniya *******
    private ListView list;
    public StudentListSetget studentListSetget;
    public ArrayList<StudentListSetget> arrayList;
    public Context context = this;
    public Utils utils;
    public ImageView backbtn;
    public StudentListAdapter adapter;
    private String eventusernmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        utils = new Utils(StudentListActivity.this);
        if (isConnectingToInternet(StudentListActivity.this)) {
            new StudentList().execute();
        } else {
            Toast.makeText(StudentListActivity.this, getString(R.string.error_nointernet), Toast.LENGTH_SHORT).show();
        }

        backbtn = (ImageView) findViewById(R.id.studentlist_backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class StudentList extends AsyncTask<String, String, String> {
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
            String strurl;
            strurl = utils.getResponseofGet(Constant.BASE_URL + "get_student_list.php");
            Log.d("Response..", "" + strurl);
            return strurl;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("student_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        eventusernmae = (jsonObj.getString("stu_firstname"));
                        studentListSetget = new StudentListSetget();
                        studentListSetget.setS_stu_firstname(jsonObj.getString("stu_firstname"));
                        studentListSetget.setS_stu_image(jsonObj.getString("stu_image"));
                        studentListSetget.setS_stu_school(jsonObj.getString("stu_school"));
                        studentListSetget.setStu_join_date(parseDateToddMMyyyy(jsonObj.getString("stu_join_date")));
                        arrayList.add(studentListSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                list = (ListView) findViewById(R.id.student_getlistview);
                adapter = new StudentListAdapter(context, arrayList);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(context, "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}