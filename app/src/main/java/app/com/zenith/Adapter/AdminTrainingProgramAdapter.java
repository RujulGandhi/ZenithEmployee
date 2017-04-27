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

import app.com.zenith.Model.AdmintrainingprogramSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi_info on 4/25/2017.
 */

public  class AdminTrainingProgramAdapter   extends BaseAdapter
{
    // TODO  Adapter Class For Admin Training Program Page
    public Context context;
    public AdmintrainingprogramSetget admintrainingprogramSetget;
    public ArrayList<AdmintrainingprogramSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView admin_trainingprogram_title;
    private TextView admin_trainingprogram_subtitle;
    private TextView admin_trainingprogram_subject;
    private CircleImageView admin_trainingprogram_image;

    public AdminTrainingProgramAdapter(FragmentActivity context, ArrayList<AdmintrainingprogramSetget> arrayList) {
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
            view = inflater.inflate(R.layout.employee_trainingprogram_custlist, null);
       admintrainingprogramSetget = arrayList.get(position);
        admin_trainingprogram_title = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_title);
        admin_trainingprogram_subtitle = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_subtitle);
        admin_trainingprogram_subject = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_subject);
        admin_trainingprogram_image = (CircleImageView) view.findViewById(R.id.employeetraining_program_image);

        if (admintrainingprogramSetget.getTraining_image().isEmpty()) {
            admin_trainingprogram_image.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(admintrainingprogramSetget.getTraining_image()).into(admin_trainingprogram_image);
        }

        admin_trainingprogram_title.setText(admintrainingprogramSetget.getTraining_title());
        admin_trainingprogram_subtitle.setText(admintrainingprogramSetget.getTraining_subtitle());
        admin_trainingprogram_subject.setText(admintrainingprogramSetget.getTraining_subject());
        return view;
    }

}
