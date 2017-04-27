package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import app.com.zenith.Model.StudenthistorySetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

/**
 * Created by archi_info on 3/20/2017.
 */
public class StudenthistoryAdapter extends BaseAdapter
{
    // TODO  Adapter Class for Student History Page
    public Context context;
    public StudenthistorySetget studenthistorySetget;
    public ArrayList<StudenthistorySetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView studenthistory_matchdate;
    private TextView studenthistory_teamonename;
    private TextView studenthistory_teamtwoname;
    private TextView studenthistory_set1_team1;
    private TextView studenthistory_set1_team2;
    private TextView studenthistory_set3_team1;
    private TextView studenthistory_set2_team2;
    private TextView studenthistory_set2_team1;
    private TextView studenthistory_set3_team2;

    public StudenthistoryAdapter(FragmentActivity context, ArrayList<StudenthistorySetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Log.d("Length", "" + arrayList.size());
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        utils = new Utils(context);
        if (convertView == null)
            view = inflater.inflate(R.layout.studenthistory_cust_infolist, null);
        studenthistorySetget = arrayList.get(position);

        studenthistory_matchdate = (TextView) view.findViewById(R.id.studenthistory_fragment_stud_matchdate);
        studenthistory_teamonename = (TextView) view.findViewById(R.id.studenthistory_fragment_stud_team_one_name);
        studenthistory_teamtwoname = (TextView) view.findViewById(R.id.studenthistory_fragment_team_two_name);
        studenthistory_set1_team1 = (TextView) view.findViewById(R.id.studenthistory_fragment_set1_score_team1);
        studenthistory_set1_team2 = (TextView) view.findViewById(R.id.studenthistory_fragment_set1_score_team2);
        studenthistory_set2_team1 = (TextView) view.findViewById(R.id.studenthistory_fragment_set2_score_team1);
        studenthistory_set2_team2 = (TextView) view.findViewById(R.id.studenthistory_fragment_set2_score_team2);
        studenthistory_set3_team1 = (TextView) view.findViewById(R.id.studenthistory_fragment_set3_score_team1);
        studenthistory_set3_team2 = (TextView) view.findViewById(R.id.studenthistory_fragment_set3_score_team2);

        studenthistory_matchdate.setText(studenthistorySetget.getMatch_datestr1());
        studenthistory_teamonename.setText(studenthistorySetget.getTeam_one_name_str2());
        studenthistory_teamtwoname.setText(studenthistorySetget.getTeam_two_name_str3());
        studenthistory_set1_team1.setText(studenthistorySetget.getSet1_score_team1_str4());
        studenthistory_set1_team2.setText(studenthistorySetget.getSet1_score_team2_str7());
        studenthistory_set1_team2.setText(studenthistorySetget.getSet1_score_team2_str7());
        studenthistory_set2_team1.setText(studenthistorySetget.getSet2_score_team1str_5());
        studenthistory_set2_team2.setText(studenthistorySetget.getSet2_score_team2_str8());
        studenthistory_set3_team1.setText(studenthistorySetget.getSet3_score_team1_str6());
        studenthistory_set3_team2.setText(studenthistorySetget.getSet3_score_team2_str9());
        return view;
    }
}