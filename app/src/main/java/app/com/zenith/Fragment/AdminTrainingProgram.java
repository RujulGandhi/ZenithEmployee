package app.com.zenith.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Activity.TrainingDetailsActivity;
import app.com.zenith.Adapter.AdminTrainingProgramAdapter;
import app.com.zenith.Model.AdmintrainingprogramSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminTrainingProgram extends Fragment {

    // TODO Fragment Class For Admin Training Program Page  *******   Sanjay Umaraniya  **********
    private ListView list;
    private AdmintrainingprogramSetget admintrainingprogramSetget;
    public ArrayList<AdmintrainingprogramSetget> arrayList;
    public Context context;
    public Utils utils;
    private Toolbar toolbar;
    public AdminTrainingProgramAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_training_program, container, false);
        utils = new Utils(getActivity());
        new ADminTrainingProgramGetList().execute();
        return view;
    }

    private class ADminTrainingProgramGetList extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return utils.getResponseofGet(Constant.BASE_URL + "get_traning_program.php");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("traning_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        admintrainingprogramSetget = new AdmintrainingprogramSetget();
                        admintrainingprogramSetget.setTraining_image(jsonObj.getString("image"));
                        admintrainingprogramSetget.setTraining_title(jsonObj.getString("title"));
                        admintrainingprogramSetget.setTraining_subtitle(jsonObj.getString("subtitle"));
                        admintrainingprogramSetget.setTraining_subject(jsonObj.getString("subject"));
                        arrayList.add(admintrainingprogramSetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (arrayList.size() > 0) {
                list = (ListView) getActivity().findViewById(R.id.adminttraining_fragment_listview);
                adapter = new AdminTrainingProgramAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(getActivity(), TrainingDetailsActivity.class);
                        in.putExtra("title", ((arrayList.get(i).getTraining_title())));
                        in.putExtra("subject", String.valueOf((arrayList.get(i).getTraining_subject())));
                        in.putExtra("subtitle", arrayList.get(i).getTraining_subtitle());
                        in.putExtra("image", arrayList.get(i).getTraining_image());
                        getActivity().startActivity(in);
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
