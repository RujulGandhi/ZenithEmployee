package app.com.zenith.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.zenith.Model.AdminSetget;
import app.com.zenith.Model.EventDetails;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * Created by archi_info on 3/9/2017.
 */

public class AdminjobinformationAdapter extends BaseAdapter {
    // TODO Adpater Class For Admin Job information Page
    public Context context;
    public EventDetails adminjobinfoSetget;
    public AdminSetget arrayList;
    public LayoutInflater inflater;
    public Utils utils;
    private TextView admin_jobinformation_eventname;
    private TextView admin_jobinformation_startdate;
    private TextView admin_jobinformation_enddate;
    private TextView admin_jobinformation_hourerate;
    private TextView admin_jobinformation_colorcode;
    private TextView admin_delete_event;


    public AdminjobinformationAdapter(Context context, AdminSetget arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrayList.getEventArray().size();
    }

    public Object getItem(int position) {
        return arrayList.getEventArray().get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        utils = new Utils(context);

        if (convertView == null)
            vi = inflater.inflate(R.layout.admin_cust_jobinformation, null);
        adminjobinfoSetget = arrayList.getEventArray().get(position);
        admin_jobinformation_eventname = (TextView) vi.findViewById(R.id.admin_jobinformation_eventname);
        admin_jobinformation_startdate = (TextView) vi.findViewById(R.id.admin_jobinformation_startdate);
        admin_jobinformation_enddate = (TextView) vi.findViewById(R.id.admin_jobinformation_enddate);
        admin_jobinformation_hourerate = (TextView) vi.findViewById(R.id.admin_jobinformation_hourerate);
        admin_jobinformation_colorcode = (TextView) vi.findViewById(R.id.admin_jobinformation_colorcode);
        admin_delete_event = (TextView) vi.findViewById(R.id.admin_jobinformation_btndelete);

        String array[] = adminjobinfoSetget.getEvent_colorcode().split(",");
        String hexColor = String.format("#%02x%02x%02x", Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
        admin_jobinformation_eventname.setText(adminjobinfoSetget.getEvent_name());
        admin_jobinformation_startdate.setText(adminjobinfoSetget.getEvent_start_time());
        admin_jobinformation_enddate.setText(adminjobinfoSetget.getEvent_end_time());
        admin_jobinformation_hourerate.setText(adminjobinfoSetget.getEvent_hourly_rate());
        admin_jobinformation_colorcode.setBackgroundColor(Color.parseColor(hexColor));
        admin_delete_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteEventById(arrayList.getEventArray().get(position).getEvent_id(), position).execute();

            }
        });

        return vi;
    }

    private class DeleteEventById extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String eventId;
        int pos;

        private DeleteEventById(String eventId, int pos) {
            this.eventId = eventId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return utils.getResponseofGet(Constant.BASE_URL + "delete_employee_event.php?event_id=" + eventId);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("status").equalsIgnoreCase("true")) {

                    arrayList.getEventArray().remove(pos);
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context, context.getString(R.string.somethingwentwrong), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}

