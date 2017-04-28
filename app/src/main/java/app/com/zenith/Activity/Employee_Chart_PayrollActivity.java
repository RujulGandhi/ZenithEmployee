package app.com.zenith.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import app.com.zenith.R;

public class Employee_Chart_PayrollActivity extends TabActivity {
    // TODO Activity for Payroll-> EmployeeChart  Page  **** Sanjay Umaraniya *******

    private Toolbar toolbar;
    private ImageView empchart_btncancel;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__chart__payroll);
        toolbar = (Toolbar) findViewById(R.id.empchart_toolbar);
        setSupportActionBar(toolbar);
        empchart_btncancel = (ImageView) findViewById(R.id.empcharttoolbar_btncancle);
        empchart_btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        spec = tabHost.newTabSpec("Total Earning"); // Create a new TabSpec using tab host
        spec.setIndicator("Total Earning"); // set the “HOME” as an indicator
        Intent intent1 = new Intent(this, EmployeeTotalEarningActivity.class);
        spec.setContent(intent1);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Working Hours"); // Create a new TabSpec using tab host
        spec.setIndicator("Working Hours"); // set the “CONTACT” as an indicator
        Intent intent3 = new Intent(this, EmployeeTotalWorkingHoursActivity.class);
        spec.setContent(intent3);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
}