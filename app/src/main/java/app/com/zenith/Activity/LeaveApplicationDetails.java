package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import app.com.zenith.Fragment.LeaveDaysInfo;
import app.com.zenith.Interface.ApiInterface;
import app.com.zenith.Model.EmpLeaveInfo;
import app.com.zenith.Model.EmployeeLeaveMainDetails;
import app.com.zenith.R;
import app.com.zenith.Utils.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.com.zenith.Utils.Constant.USERID;

/**
 * Created by archirayan on 21-Feb-17.
 */
public class LeaveApplicationDetails extends BaseActivity implements View.OnClickListener
{
    // TODO Activity for Employee Leave Detail Page  **** Sanjay Umaraniya *******
    public List<EmpLeaveInfo> listEmpLeaveInfo;
    @BindView(R.id.activity_leave_app_details_viewpager)
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_leave_app_details);
        ButterKnife.bind(this);
        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.leaveapplication);
        ((ImageView) findViewById(R.id.toolbar_icon)).setImageResource(R.drawable.ic_backspace_dark);
        ((ImageView) findViewById(R.id.toolbar_icon)).setOnClickListener(this);

        final ProgressDialog pd = new ProgressDialog(LeaveApplicationDetails.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("emp_id", read(USERID));
        Call<EmployeeLeaveMainDetails> call = apiService.getEmployeeLeaveInfo(hashMap);
        call.enqueue(new Callback<EmployeeLeaveMainDetails>() {
            @Override
            public void onResponse(Call<EmployeeLeaveMainDetails> call, Response<EmployeeLeaveMainDetails> response) {
                pd.dismiss();
                if (response.body().getStatus().equalsIgnoreCase("true"))
                {
                    listEmpLeaveInfo = response.body().getEmpLeaveInfo();
                    viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), listEmpLeaveInfo));
                }
            }

            @Override
            public void onFailure(Call<EmployeeLeaveMainDetails> call, Throwable t)
            {
                Toast.makeText(LeaveApplicationDetails.this, getString(R.string.somethingwentwrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.toolbar_icon:
                onBackPressed();
                break;
        }
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter
    {
        List<EmpLeaveInfo> listEmpLeaveInfo;
        public MyFragmentAdapter(FragmentManager fm, List<EmpLeaveInfo> listEmpLeaveInfo) {
            super(fm);
            this.listEmpLeaveInfo = listEmpLeaveInfo;
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0)
        {
            // TODO Auto-generated method stub
            Fragment fragment = new LeaveDaysInfo();
            Bundle args = new Bundle();
            Gson gson = new Gson();
            args.putString("key", gson.toJson(listEmpLeaveInfo.get(arg0)));
            args.putString("EXTRA_DATA", "" + arg0);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return listEmpLeaveInfo.size();
        }
    }
}
