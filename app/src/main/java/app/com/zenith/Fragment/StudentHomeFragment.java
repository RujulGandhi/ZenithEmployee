package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.com.zenith.Adapter.StudenthomeAdapter;
import app.com.zenith.Model.StudenthomeSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentHomeFragment extends Fragment {
    // TODO Fragment Class For Studnet Home Page  *******   Sanjay Umaraniya  **********

    private ListView list;
    public Utils utils;
    private String date;
    public StudenthomeSetget studenthomeSetget;
    public ArrayList<StudenthomeSetget> arrayList;
    private CompactCalendarView compactCalendarView;
    public StudenthomeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_home, container, false);
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(dateClicked);
                utils = new Utils(getActivity());
                new StudenthomegetList().execute();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d("TAG", "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
        return view;
    }

    private class StudenthomegetList extends AsyncTask<String, String, String> {
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
            return utils.getResponseofGet(Constant.BASE_URL + "stu_ranking_score.php");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    arrayList = new ArrayList<>();
                    JSONArray array = jsonObject.getJSONArray("ranking_detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObj = array.getJSONObject(i);
                        studenthomeSetget = new StudenthomeSetget();
                        studenthomeSetget.setStudhome_id(jsonObj.getString("stu_id"));
                        Log.d("jsonObj", "" + jsonObj.getString("stu_id"));
                        studenthomeSetget.setStudhome_image(jsonObj.getString("stu_profile"));
                        Log.d("jsonObj", "" + jsonObj.getString("stu_profile"));
                        studenthomeSetget.setStudhome_name(jsonObj.getString("stu_firstname"));
                        Log.d("jsonObj", "" + jsonObj.getString("stu_firstname"));
                        studenthomeSetget.setStudhome_winmatch(jsonObj.getString("winmatch"));
                        Log.d("jsonObj", "" + jsonObj.getString("winmatch"));
                        studenthomeSetget.setStudhome_playmatch(jsonObj.getString("palymatch"));
                        Log.d("jsonObj", "" + jsonObj.getString("palymatch"));
                        arrayList.add(studenthomeSetget);
                    }
                }
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                list = (ListView) getActivity().findViewById(R.id.studenthome_fragment_listview);
                adapter = new StudenthomeAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Data Not Found ? Please Try Again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}