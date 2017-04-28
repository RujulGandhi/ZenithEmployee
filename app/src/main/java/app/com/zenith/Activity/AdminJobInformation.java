package app.com.zenith.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import app.com.zenith.Adapter.AdminjobinformationAdapter;
import app.com.zenith.Model.AdminSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

public class AdminJobInformation extends AppCompatActivity {
    // TODO Activity for AdminJobInformation Page  **** Sanjay Umaraniya *******
    public TextView admin_jobinformation_empname;
    public TextView admin_jobinformation_eventcount;
    public Button admin_jobinformation_btnaddshift;
    public ListView admin_jobinformation_lv;
    public AdminSetget adminSetget;
    public AdminSetget arrayList;
    public ImageView backbtn;
    public Button admin_jobinformation_btnemplist;
    public Utils utils;
    public AdminjobinformationAdapter adpter;


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
        admin_jobinformation_lv = (ListView) AdminJobInformation.this.findViewById(R.id.admin_jobinformation_lv);
        admin_jobinformation_btnemplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminJobInformation.this, EmployeeList.class));
                finish();
            }
        });

        utils = new Utils(AdminJobInformation.this);
        setValues();
    }

    // TODO: 4/26/2017 setthe value of perticular employee
    private void setValues() {
        if (getIntent().getExtras() != null) {
            Gson gson = new Gson();
            String strObjList = getIntent().getExtras().getString("jobdetails");
            arrayList = gson.fromJson(strObjList, AdminSetget.class);

            adpter = new AdminjobinformationAdapter(AdminJobInformation.this, arrayList);
            admin_jobinformation_lv.setAdapter(adpter);
            admin_jobinformation_eventcount.setText("" + (arrayList.getEventArray().size()));
        } else {
            Toast.makeText(AdminJobInformation.this, "Data not found", Toast.LENGTH_SHORT).show();
        }
    }

}