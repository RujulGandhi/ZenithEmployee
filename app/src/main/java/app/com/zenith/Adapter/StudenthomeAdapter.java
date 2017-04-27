package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import app.com.zenith.Model.StudenthomeSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by archi_info on 3/20/2017.
 */
public class StudenthomeAdapter extends BaseAdapter
{
    // TODO Adpater Class for STUDENT HOME Page
    public Context context;
    public StudenthomeSetget studenthomeSetget;
    public ArrayList<StudenthomeSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView studenthome_txtstd_id;
    private TextView studenthome_txtstd_name;
    private TextView studenthome_txtstd_winmatch;
    private TextView studenthome_txtstd_playmatch;
    private CircleImageView studenthome_fragment_profileimage;

    public StudenthomeAdapter(FragmentActivity context, ArrayList<StudenthomeSetget> arrayList) {
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
            view = inflater.inflate(R.layout.studenthome_cust_rank_score, null);
        studenthomeSetget = arrayList.get(position);
        studenthome_txtstd_id = (TextView) view.findViewById(R.id.studenthome_fragment_stud_id);
        studenthome_txtstd_name = (TextView) view.findViewById(R.id.studenthome_fragment_stu_firstname);
        studenthome_txtstd_winmatch = (TextView) view.findViewById(R.id.studenthome_fragment_winmatch);
        studenthome_txtstd_playmatch = (TextView) view.findViewById(R.id.studenthome_fragment_palymatch);
         studenthome_fragment_profileimage = (CircleImageView) view.findViewById(R.id.studenthome_fragment_stu_profile);
        studenthome_txtstd_id.setText(studenthomeSetget.getStudhome_id());
        studenthome_txtstd_name.setText(studenthomeSetget.getStudhome_name());
        studenthome_txtstd_winmatch.setText(studenthomeSetget.getStudhome_winmatch());
        studenthome_txtstd_playmatch.setText(studenthomeSetget.getStudhome_playmatch());
        Picasso.with(context).load(studenthomeSetget.getStudhome_image()).placeholder(R.drawable.ic_placeholder).into(studenthome_fragment_profileimage);
        return view;
    }
}
