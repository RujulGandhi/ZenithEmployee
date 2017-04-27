package app.com.zenith.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.com.zenith.Model.AdminSetget;
import app.com.zenith.R;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Created by archi_info on 3/8/2017.
 */
public class AdminjoblistAdpter extends BaseAdapter
{
    // TODO  Adapter Class for Admin Joblist Page
    public Context context;
    public AdminSetget adminSetget;
    public ArrayList<AdminSetget> arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView adminjoblist_txtname;
    private TextView adminjoblist_txtshift;
    private TextView adminjoblist_txtdate;
    private CircleImageView adminjoblist_img;

    public AdminjoblistAdpter(Context context, ArrayList<AdminSetget> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return arrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        utils = new Utils(context);
        if (convertView == null)
            vi = inflater.inflate(R.layout.admin_cust_joblist, null);
        adminSetget = arrayList.get(position);
        adminjoblist_txtname = (TextView) vi.findViewById(R.id.admin_joblist_name);
        adminjoblist_txtshift = (TextView) vi.findViewById(R.id.admin_joblist_shift);
        adminjoblist_txtdate = (TextView) vi.findViewById(R.id.admin_joblist_date);
        adminjoblist_img = (CircleImageView) vi.findViewById(R.id.admin_joblist_img);

        Picasso.with(context).load(adminSetget.getE_img()).placeholder(R.drawable.ic_placeholder).into(adminjoblist_img);
        adminjoblist_txtname.setText(adminSetget.getE_name());
        adminjoblist_txtshift.setText(adminSetget.getE_shift());
        adminjoblist_txtdate.setText(adminSetget.getE_date());
        return vi;
    }
}