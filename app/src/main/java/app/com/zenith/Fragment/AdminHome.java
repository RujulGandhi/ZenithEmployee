package app.com.zenith.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.com.zenith.Activity.EmployeeList;
import app.com.zenith.Activity.StudentListActivity;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * Created by archirayan on 15-Feb-17.
 */

public class AdminHome extends Fragment {
    // TODO Fragment Class For Admin Home Page  *******   Sanjay Umaraniya  **********
    private Utils utils;
    private LinearLayout classshedual_layoutid;
    private LinearLayout classshedual_layoutid4;
    private TextView total_emp_event_txt;
    private TextView total_std_event_txt;
    private Toolbar toolbar;
    private TextView toolbar_title2;
    ActionBar actionBar;
    private Button admin_addjobshift;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        utils = new Utils(getActivity());
        setHasOptionsMenu(true);
        total_emp_event_txt = (TextView) view.findViewById(R.id.admin_total_emp_event);
        total_std_event_txt = (TextView) view.findViewById(R.id.admin_total_std_event);
        //admin_addjobshift=find
        classshedual_layoutid = (LinearLayout) view.findViewById(R.id.classshedual_layoutid1);
        classshedual_layoutid4 = (LinearLayout) view.findViewById(R.id.classshedual_layoutid4);

        total_emp_event_txt.setText(utils.ReadSharePrefrence(getActivity(), Constant.ADMIN_TOTAL_EMP));
        total_std_event_txt.setText(utils.ReadSharePrefrence(getActivity(), Constant.ADMIN_TOTAL_STD));

        classshedual_layoutid4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), StudentListActivity.class);
                startActivity(intent);
                onPause();
            }
        });

        classshedual_layoutid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployeeList.class);
                startActivity(intent);
                onPause();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.adminhome_profilelogo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(getActivity(), "Profile", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}