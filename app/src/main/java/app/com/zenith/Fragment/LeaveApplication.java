package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import app.com.zenith.Activity.LeaveApplicationDetails;
import app.com.zenith.Adapter.LeaveApplicationAdapter;
import app.com.zenith.Interface.ApiInterface;
import app.com.zenith.Model.LeaveDatum;
import app.com.zenith.Model.LeaveMainDetails;
import app.com.zenith.R;
import app.com.zenith.Utils.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.com.zenith.Utils.Constant.USERID;
import static app.com.zenith.Utils.Utils.ReadSharePrefrence;
import static app.com.zenith.Utils.Utils.isConnectingToInternet;

/**
 * Created by archirayan on 20-Feb-17.
 */
public class LeaveApplication extends Fragment {
    // TODO Fragment Class For Employee Leave Page  *******   Sanjay Umaraniya  **********
    @BindView(R.id.fragment_leave_app_recycler)
    public RecyclerView recyclerView;
    private List<LeaveDatum> leaveInfoList;
    private LinearLayoutManager layoutManager;
    private LeaveApplicationAdapter leaveAdapter;
    public ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_leave_app, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        if (isConnectingToInternet(getActivity())) {
            getLeaveApplicationList();
        } else {
            Toast.makeText(getActivity(), getString(R.string.error_nointernet), Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getActivity(), LeaveApplicationDetails.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getLeaveApplicationList() {

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        ApiInterface apiINterface = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("emp_id", ReadSharePrefrence(getActivity(), USERID));
        Call<LeaveMainDetails> call = apiINterface.getLeaveApplicationList(hashMap);
        call.enqueue(new Callback<LeaveMainDetails>() {
            @Override
            public void onResponse(Call<LeaveMainDetails> call, Response<LeaveMainDetails> response) {

                if (response.body().getStatus().equalsIgnoreCase("true")) {
                    pd.dismiss();
                    leaveInfoList = response.body().getLeaveData();
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    leaveAdapter = new LeaveApplicationAdapter(getActivity(), leaveInfoList);
                    recyclerView.setAdapter(leaveAdapter);
                }
            }

            @Override
            public void onFailure(Call<LeaveMainDetails> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), getString(R.string.somethingwentwrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
}