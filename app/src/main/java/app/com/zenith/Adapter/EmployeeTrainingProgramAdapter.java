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
import app.com.zenith.Model.EmployeetrainingprogramSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archi_info on 4/6/2017.
 */
public class EmployeeTrainingProgramAdapter extends BaseAdapter
{
    // TODO  Adapter Class For Employee Training Program Page
    public Context context;
    public EmployeetrainingprogramSetget employeetrainingprogramSetget;
    public ArrayList<EmployeetrainingprogramSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView employee_trainingprogram_title;
    private TextView employee_trainingprogram_subtitle;
    private TextView employee_trainingprogram_subject;
    private CircleImageView employee_trainingprogram_image;

    public EmployeeTrainingProgramAdapter(FragmentActivity context, ArrayList<EmployeetrainingprogramSetget> arrayList) {
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
        employeetrainingprogramSetget = arrayList.get(position);
        employee_trainingprogram_title = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_title);
        employee_trainingprogram_subtitle = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_subtitle);
        employee_trainingprogram_subject = (TextView) view.findViewById(R.id.employeetraining_program_txt_traing_subject);
        employee_trainingprogram_image = (CircleImageView) view.findViewById(R.id.employeetraining_program_image);

        if (employeetrainingprogramSetget.getTraining_image().isEmpty()) {
            employee_trainingprogram_image.setImageResource(R.drawable.ic_placeholder);
        } else {
            Picasso.with(context).load(employeetrainingprogramSetget.getTraining_image()).into(employee_trainingprogram_image);
        }

        employee_trainingprogram_title.setText(employeetrainingprogramSetget.getTraining_title());
        employee_trainingprogram_subtitle.setText(employeetrainingprogramSetget.getTraining_subtitle());
        employee_trainingprogram_subject.setText(employeetrainingprogramSetget.getTraining_subject());
        return view;
    }
}
