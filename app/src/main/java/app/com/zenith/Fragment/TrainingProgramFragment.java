package app.com.zenith.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.zenith.Activity.Trainingprogram_addpostActivity;
import app.com.zenith.Adapter.TrainingProgramAdapter;
import app.com.zenith.Interface.ApiInterface;
import app.com.zenith.Model.TraningDetail;
import app.com.zenith.Model.TraningMainDetails;
import app.com.zenith.R;
import app.com.zenith.Utils.ApiClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ravi-archi on 2/15/2017.
 */

public class TrainingProgramFragment extends Fragment
{

    // TODO Fragment Class For Admin Training Program Page  *******   Sanjay Umaraniya  **********

    // TODO: 2/15/2017 nidhi

    @BindView(R.id.fragment_training_program_recycleview_training)
    public RecyclerView recyclerView;
    public Context context;
    public List<TraningDetail> trainingInfoList;
    public TrainingProgramAdapter trainingListAdapter;
    public TextView titleTv;
    private LinearLayoutManager layoutManager;
    Button btn_addpost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_training_program, container, false);
        ButterKnife.bind(this, view);
        btn_addpost= (Button) view.findViewById(R.id.admin_trainingprogram_addpostbutton);
        btn_addpost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(),Trainingprogram_addpostActivity.class);
                startActivity(intent);
                onPause();
            }
        });
          // TODO: 16-Feb-17 Rujul Training List api call
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TraningMainDetails> call = apiService.getTraningList();
        call.enqueue(new Callback<TraningMainDetails>() {
            @Override
            public void onResponse(Call<TraningMainDetails> call, Response<TraningMainDetails> response) {

                Log.d("##$", response.body().getTraningDetail().toString());
                trainingInfoList = response.body().getTraningDetail();
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                trainingListAdapter = new TrainingProgramAdapter(getActivity(), trainingInfoList);
                recyclerView.setAdapter(trainingListAdapter);
            }

            @Override
            public void onFailure(Call<TraningMainDetails> call, Throwable t) {
               // textView.setText("Error");
            }
        });
        init();
        return view;
    }

    private void init() {

        trainingInfoList = new ArrayList<>();
        // TODO: 16-Feb-17 Rujul setup recycler view
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        trainingListAdapter = new TrainingProgramAdapter(context, trainingInfoList);
        recyclerView.setAdapter(trainingListAdapter);

    }
}