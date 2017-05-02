package app.com.zenith.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import app.com.zenith.Adapter.StudentListAdapter;
import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;


public class AdminLeaveAppsListActivity extends AppCompatActivity {
    Context context = this;
    TextView adminleave_list_name, studentlist_title;

    public Utils utils;
    ImageView backbtn, admin_leaveapps_getlist_img;
    StudentListAdapter adapter;
    Button approve_btnconfirm,addpost_btn_reject;
    String[] eventusernmae;
    CircleImageView adminleaveapp_getlist_image;
    TextView admin_leaveapp_getlist_applied_date, admin_leaveapps_getlist_to_date, admin_leaveapps_getlist_from_date, admin_leaveapps_getlist_leave_notes, admin_leaveapps_getlist_leavedays_available, admin_leaveapps_getlist_leavedays_used, admin_leaveapps_list_leavetype, admin_leaveapps_list_totalday;
    String id, status;
    String str_leave_id, str_leave_status;
    CircleImageView getlist_img;
    private static final int timeout = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_leave_apps_list);
        backbtn = (ImageView) findViewById(R.id.adminleavapplist__backbutton);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        String name = intent.getExtras().getString("Name");
        status = intent.getExtras().getString("Leavestatus");
        Toast.makeText(context, "" + id + "+" + status, Toast.LENGTH_SHORT).show();
        String leave_type = intent.getExtras().getString("leave_type");
        String fromdate = intent.getExtras().getString("FromDate");
        String todate = intent.getExtras().getString("ToDate");
        String daysused = intent.getExtras().getString("DaysUsed");
        String leave_notes = intent.getExtras().getString("LeaveNotes");
        String daysavailable = intent.getExtras().getString("DaysAvailable");
        String applieddate = intent.getExtras().getString("appliedDate");
        String leavedays = intent.getExtras().getString("leave_days");
        String TotalDays = intent.getExtras().getString("DaysUsed");
        String emp_image = intent.getExtras().getString("emp_image");
        utils = new Utils(AdminLeaveAppsListActivity.this);

        init();
        adminleave_list_name.setText(name);
        admin_leaveapps_list_leavetype.setText(leave_type);
        admin_leaveapps_getlist_from_date.setText(fromdate);
        admin_leaveapps_getlist_to_date.setText(todate);
        admin_leaveapps_getlist_leave_notes.setText(leave_notes);
        admin_leaveapps_getlist_leavedays_available.setText(daysavailable);
        admin_leaveapp_getlist_applied_date.setText(applieddate);
        admin_leaveapps_getlist_leavedays_used.setText(daysused);
        admin_leaveapps_list_totalday.setText(TotalDays);
        Picasso.with(context).load(emp_image).placeholder(R.drawable.ic_placeholder).into(getlist_img);

    }

    private void init() {
        adminleave_list_name = (TextView) findViewById(R.id.admin_leaveapp_list_name);
        admin_leaveapps_getlist_to_date = (TextView) findViewById(R.id.admin_leaveapps_getlist_to_date);
        admin_leaveapps_getlist_from_date = (TextView) findViewById(R.id.admin_leaveapps_getlist_from_date);
        admin_leaveapps_getlist_leave_notes = (TextView) findViewById(R.id.admin_leaveapps_getlist_leave_notes);
        admin_leaveapps_getlist_leavedays_available = (TextView) findViewById(R.id.admin_leaveapps_getlist_leavedays_available);
        admin_leaveapps_getlist_leavedays_used = (TextView) findViewById(R.id.admin_leaveapps_getlist_leavedays_used);
        admin_leaveapps_list_totalday = (TextView) findViewById(R.id.admin_leaveapps_list_totalday);
        getlist_img = (CircleImageView) findViewById(R.id.admin_leaveapps_getlist_img);
        admin_leaveapp_getlist_applied_date = (TextView) findViewById(R.id.admin_leaveapp_getlist_applied_date);
        admin_leaveapps_list_leavetype = (TextView) findViewById(R.id.admin_leaveapps_list_leavetype);
        approve_btnconfirm = (Button) findViewById(R.id.addpost_btn_approve);
        addpost_btn_reject= (Button) findViewById(R.id.addpost_btn_reject);


        // TODO  REJECT BUTTON Click  {2}.....
        addpost_btn_reject.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.adminleaveapplist_reject_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.adminleave_reject_cancel);

                dialogBtn_cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button dialogBtn_okay = (Button) dialog.findViewById(R.id.adminleave_approvebtnconfirm);
                dialogBtn_okay.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                        utils = new Utils(context);
                        new leavestatusREJECT().execute();
                    }
                });



            }
        });

        // TODO  UPDATE BUTTON Click  {1}.....
        approve_btnconfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.adminleaveapplist_approve_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.adminleave_reject_cancel);

                dialogBtn_cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button dialogBtn_okay = (Button) dialog.findViewById(R.id.adminleave_approvebtnconfirm);
                dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.cancel();
                        utils = new Utils(context);
                        new leavestatusUpdate().execute();
                    }
                });
            }
        });
    }


    //         // TODO  UPDATE  API CALL   {1}.....
    private class leavestatusUpdate extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String str_url = utils.getResponseofGet(Constant.BASE_URL + "update_leave_status.php?leave_id=" + id + "&leave_status=1"  );
            Log.d("Response", "" + str_url);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true"))
                {

                    Toast.makeText(context, "" + str_leave_id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "" + str_leave_status, Toast.LENGTH_SHORT).show();
                    final Dialog dialogright = new Dialog(context);
                    dialogright.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogright.setCancelable(true);
                    dialogright.setContentView(R.layout.addminleave_dailogapprove_right);
                    dialogright.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialogright.show();
                    dialogright.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            dialog.dismiss();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // TODO LEAVE REJECT API CALL  {2} ...
    private class leavestatusREJECT extends AsyncTask<String, String, String>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd=new ProgressDialog(context);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            String str_url = utils.getResponseofGet(Constant.BASE_URL + "update_leave_status.php?leave_id=" + id + "&leave_status=" + 0);
            Log.d("Response", "" + str_url);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject=new JSONObject(s);
                if(jsonObject.getString("status").equalsIgnoreCase("true"))
                {
                    final Dialog dialogright = new Dialog(context);
                    dialogright.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogright.setCancelable(true);
                    dialogright.setContentView(R.layout.addminleave_dailogapprove_right);
                    dialogright.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialogright.show();
                    dialogright.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            dialog.dismiss();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context, ""+jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}