package app.com.zenith.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.zenith.Model.StudenttrainingprogramSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi_info on 3/21/2017.
 */
public class StudentTrainingProgramAdapter extends BaseAdapter
{
    //TODO Adapter Class For Student-Training Page
    public Context context;
    public StudenttrainingprogramSetget studenttrainingprogramSetget;
    public ArrayList<StudenttrainingprogramSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView studenttraingprogram_title;
    private TextView studenttraingprogram_subtitle;
    private TextView studenttraingprogram_subject;
    private CircleImageView studenttraing_image;
    private ImageView alertdailog_image;

    public StudentTrainingProgramAdapter(FragmentActivity context, ArrayList<StudenttrainingprogramSetget> arrayList) {
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
            view = inflater.inflate(R.layout.student_tariningprogram_custlist, null);
        studenttrainingprogramSetget = arrayList.get(position);
        studenttraingprogram_title = (TextView) view.findViewById(R.id.studenttraining_program_txt_traing_title);
        studenttraingprogram_subtitle = (TextView) view.findViewById(R.id.studenttraining_program_txt_traing_subtitle);
        studenttraingprogram_subject = (TextView) view.findViewById(R.id.studenttraining_program_txt_traing_subject);
        studenttraing_image = (CircleImageView) view.findViewById(R.id.studenttraining_program_image);


        if (studenttrainingprogramSetget.getTraining_image().isEmpty()) {
            studenttraing_image.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(studenttrainingprogramSetget.getTraining_image()).into(studenttraing_image);
        }

        studenttraing_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.cust_alertimage_dailog, null);
                alertdailog_image = (ImageView) v.findViewById(R.id.alertdailog_image);
                Picasso.with(context).load(studenttrainingprogramSetget.getTraining_image()).into(alertdailog_image);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setView(v);
                builder.show();
            }
        });

        studenttraingprogram_title.setText(studenttrainingprogramSetget.getTraining_title());
        studenttraingprogram_subtitle.setText(studenttrainingprogramSetget.getTraining_subtitle());
        studenttraingprogram_subject.setText(studenttrainingprogramSetget.getTraining_subject());
        return view;
    }
}