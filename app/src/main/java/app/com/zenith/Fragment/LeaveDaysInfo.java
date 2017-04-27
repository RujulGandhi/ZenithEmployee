package app.com.zenith.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import app.com.zenith.Activity.AddLeaveActivity;
import app.com.zenith.Adapter.LeaveDaysAdapter;
import app.com.zenith.Model.EmpLeaveInfo;
import app.com.zenith.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by archirayan on 22-Feb-17.
 */
public class LeaveDaysInfo extends Fragment
{
    // TODO Fragment Class For Employee Leave one Page  *******   Sanjay Umaraniya  **********
    private EmpLeaveInfo empLeaveInfo;
    private GridLayoutManager layoutManager;
    private LeaveDaysAdapter leaveAdapter;
    private String modleClassString;
    private int totalDays;
    private int usedDays;

    @BindView(R.id.fragment_leave_app_total_days)
    public TextView totalDaysTv;

    @BindView(R.id.fragment_leave_app_attened_days)
    public TextView attendedDaysTv;

    @BindView(R.id.activity_leave_ap_details_gridView)
    public RecyclerView recyclerView;

    @BindView(R.id.fragment_leave_app_details_apply)
    public Button applyBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_leave_one, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            modleClassString = getArguments().getString("key");
            Gson gson = new Gson();
            empLeaveInfo = gson.fromJson(modleClassString, EmpLeaveInfo.class);
            totalDays = Integer.parseInt(empLeaveInfo.getLeaveDays());
            usedDays = empLeaveInfo.getUsedLeave();
            totalDaysTv.setText(empLeaveInfo.getLeaveDays());
            attendedDaysTv.setText("/" + (totalDays - usedDays));
            applyBtn.setText(getString(R.string.applyfor) + " " + empLeaveInfo.getLeaveName());
            layoutManager = new GridLayoutManager(getActivity(), 4);
            recyclerView.setLayoutManager(layoutManager);
            leaveAdapter = new LeaveDaysAdapter(getActivity(), totalDays, (totalDays - usedDays));
            recyclerView.setAdapter(leaveAdapter);
        }
        return view;
    }

    @OnClick(R.id.fragment_leave_app_details_apply)
    public void getData() {
        Intent in = new Intent(getActivity(), AddLeaveActivity.class);
        startActivity(in);
    }
}
