package app.com.zenith.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.zenith.Model.StudentListSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi_info on 3/25/2017.
 */
public class StudentListAdapter extends BaseAdapter
{
    // TODO Adapter Class for Student List Page
    public Context context;
    public StudentListSetget studentListSetget;
    public ArrayList<StudentListSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private CircleImageView studentlist_studimage;
    private TextView studentlist_studname;
    private TextView employeelist_datetime;
    private TextView employeelist_schoolname;

    public StudentListAdapter(Context context, ArrayList<StudentListSetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        Log.d("Length", "" + arrayList.size());
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            view = inflater.inflate(R.layout.studentgetlist_cust_list, null);
        studentListSetget = arrayList.get(position);
        studentlist_studimage = (CircleImageView) view.findViewById(R.id.student_list_studimg);
        studentlist_studname = (TextView) view.findViewById(R.id.student_list_studname);
        employeelist_datetime = (TextView) view.findViewById(R.id.student_list_studjoindate);
        employeelist_schoolname = (TextView) view.findViewById(R.id.student_list_studschoolname);

        if (studentListSetget.getS_stu_image().isEmpty()) {
            studentlist_studimage.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(studentListSetget.getS_stu_image()).into(studentlist_studimage);
        }
        studentlist_studname.setText(studentListSetget.getS_stu_firstname());
        employeelist_datetime.setText(studentListSetget.getStu_join_date());
        employeelist_schoolname.setText(studentListSetget.getS_stu_school());
        return view;
    }
}
