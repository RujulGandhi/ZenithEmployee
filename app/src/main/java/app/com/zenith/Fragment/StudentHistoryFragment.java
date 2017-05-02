package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.Adapter.StudenthistoryAdapter;
import app.com.zenith.Model.StudenthistorySetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.Utils.Constant.USERID;
import static app.com.zenith.Utils.Constant.USERNAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentHistoryFragment extends Fragment {
    // TODO Fragment Class For Student History   Page  *******   Sanjay Umaraniya  **********

    public StudenthistorySetget studenthistorySetget;
    public ArrayList<StudenthistorySetget> arrayList;
    public StudenthistoryAdapter adapter;
    private ListView list;
    private Context context;
    public Utils utils;
    private TextView studenthistory_username;
    private String str_studid;
    private SharedPreferences sp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_history, container, false);
        studenthistory_username = (TextView) view.findViewById(R.id.studenthistory_fragment_username);
        utils = new Utils(getActivity());
        new StudenthistorygetList().execute();
        studenthistory_username.setText(utils.ReadSharePrefrence(getActivity(), USERNAME));
        str_studid = utils.ReadSharePrefrence(getActivity(), USERID);
        return view;
    }

    private class StudenthistorygetList extends AsyncTask<String, String, String> {
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
            return utils.getResponseofGet(Constant.BASE_URL + "get_student_history_score.php?stu_id=" + 3);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("StdId", "" + s);
            Log.d("StdfId", "" + str_studid);

            pd.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("event_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        studenthistorySetget = new StudenthistorySetget();
                        studenthistorySetget.setMatch_datestr1(jsonObj.getString("match_date"));
                        studenthistorySetget.setTeam_one_name_str2(jsonObj.getString("team_one_name"));
                        studenthistorySetget.setTeam_two_name_str3(jsonObj.getString("team_two_name"));
                        studenthistorySetget.setSet1_score_team1_str4(jsonObj.getString("set1_score_team1"));
                        studenthistorySetget.setSet1_score_team2_str7(jsonObj.getString("set1_score_team2"));
                        studenthistorySetget.setSet2_score_team1str_5(jsonObj.getString("set2_score_team1"));
                        studenthistorySetget.setSet2_score_team2_str8(jsonObj.getString("set2_score_team2"));
                        studenthistorySetget.setSet3_score_team1_str6(jsonObj.getString("set3_score_team1"));
                        studenthistorySetget.setSet3_score_team2_str9(jsonObj.getString("set3_score_team2"));
                        arrayList.add(studenthistorySetget);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (arrayList.size() > 0) {
                list = (ListView) getActivity().findViewById(R.id.studenthistory_fragment_listview);
                adapter = new StudenthistoryAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


