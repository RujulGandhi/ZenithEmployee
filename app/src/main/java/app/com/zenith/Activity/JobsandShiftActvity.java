package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import petrov.kristiyan.colorpicker.ColorPicker;

public class JobsandShiftActvity extends AppCompatActivity {
    // TODO Activity for JobsandShift Page  **** Sanjay Umaraniya *******
    private TextView jobsandshift_eventusername0;
    private TextView jobsandshift_edit_eventhours3;
    private TextView jobsandshift_edit_starttime4;
    private TextView jobsandshift_edit_endtime5;
    private Button jobsandshift_btnunpaidbreak;
    private EditText jobsandshift_edit_eventname2;
    private EditText jobsandshift_editeventhoursrate6;
    public Utils utils;
    public Context context = this;
    public String str_eventusername0;
    public String str_eventshiftname;
    public String str_starttime;
    public String str_endtime;
    public String str_eventcolorcode;
    public String str_eventhours;
    public String str_eventhourerate;
    public String str_currentdate;
    private TextView jobsandshifttoolbar_title;
    private ImageView jobsandshifttoolbar_backbutton;
    private ImageView jobsandshifttoolbar_rightbutton;
    private String id;
    private int minut, hour;
    private Calendar calendar;
    private CircleImageView shift_circle_txtcolor_redark1;
    private String str_color;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsand_shift_actvity);
        utils = new Utils(context);
        toolbar = (Toolbar) findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);

        // TODO  Initialize variable...

        jobsandshift_eventusername0 = (TextView) findViewById(R.id.jobsandshiftactivity_eventusername0);
        jobsandshift_edit_eventhours3 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_eventhours3);
        jobsandshift_edit_starttime4 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_starttime4);
        jobsandshift_edit_endtime5 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_endtime5);
        jobsandshift_btnunpaidbreak = (Button) findViewById(R.id.jobsand_shift_btnunpaidbreak);
        jobsandshift_edit_eventname2 = (EditText) findViewById(R.id.jobsandshiftactivity_edit_eventname2);
        jobsandshift_editeventhoursrate6 = (EditText) findViewById(R.id.jobsandshiftactivity_editeventhoursrate6);
        shift_circle_txtcolor_redark1 = (CircleImageView) findViewById(R.id.shift_circle_txtcolor_redark1);
        jobsandshifttoolbar_title = (TextView) findViewById(R.id.jobsandshifttoolbar_title);
        jobsandshifttoolbar_rightbutton = (ImageView) findViewById(R.id.jobsandshifttoolbar_right_button);
        // TODO Get Extra to get Event
        /*Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        id = intent.getExtras().getString("Id");
        Log.d("IDANdEMPNAME",""+id+name);
        jobsandshift_eventusername0.setText(name);
   */
        jobsandshifttoolbar_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // TODO COLOR CODE
        shift_circle_txtcolor_redark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shift_circle_txtcolor_redark1 != null) {
                    shift_circle_txtcolor_redark1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final ColorPicker colorPicker = new ColorPicker(context);
                            final ArrayList<String> colors = new ArrayList<>();
                            colors.add("#82B926");
                            colors.add("#a276eb");
                            colors.add("#6a3ab2");
                            colors.add("#666666");
                            colors.add("#FFFF00");
                            colors.add("#3C8D2F");
                            colors.add("#FA9F00");
                            colors.add("#FF0000");
                            colors.add("#325EFF");
                            colors.add("#9032B7");
                            colors.add("#E33257");
                            colors.add("#551A8B");

                            // COlorCode Formate:- UIExtendedSRGBColorSpace 0 0.588235 0.533333 1
                            colorPicker.setColors(colors).setDefaultColorButton(Color.parseColor("#f84c44")).setColumns(5).setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color)
                                {
                                    Log.d("Color", "" + color + " -- " + position + " : " + colors.get(position));
                                    int colorData = (int) Long.parseLong(colors.get(position).substring(1, colors.get(position).length()), 16);
                                    int r = (colorData >> 16) & 0xFF;
                                    int g = (colorData >> 8) & 0xFF;
                                    int b = (colorData >> 0) & 0xFF;
                                    StringBuilder messages = new StringBuilder();
                                    messages.append(String.valueOf("UIExtendedSRGBColorSpace" + r + "," + g + "," + b));
                                    str_color = String.valueOf(messages);
                                }

                                @Override
                                public void onCancel() {

                                }
                            }).setRoundColorButton(true).show();
                        }
                    });
                }

            }
        });


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        str_currentdate = df.format(c.getTime());
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minut = calendar.get(Calendar.MINUTE);
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minut = minute;
                String s = "";
                if (hour > 12) {
                    hour -= 12;
                    s = "PM";
                } else if (hour == 0) {
                    hour += 12;
                    s = "AM";
                } else if (hour == 12) {
                    s = "PM";
                } else s = "AM";
                String minut1 = "";
                if (minut <= 10) {
                    minut1 = "0" + minut;
                } else {
                    minut1 = String.valueOf(minut);
                }
                StringBuffer sb = new StringBuffer();
                String s1 = sb.append(hour).append(":").append(minut).append(":").append(s).toString();
                jobsandshift_edit_starttime4.setText(s1);
            }
        };

        jobsandshift_edit_starttime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context, time, hour, minut, false).show();
            }
        });


        final TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minut = minute;
                String s = "";
                if (hour > 12)
                    {
                    hour -= 12;
                    s = "PM";
                } else if (hour == 0) {
                    hour += 12;
                    s = "AM";
                } else if (hour == 12) {
                    s = "PM";
                } else s = "AM";
                String minut1 = "";
                if (minut <= 10) {
                    minut1 = "0" + minut;
                } else {
                    minut1 = String.valueOf(minut);
                }
                StringBuffer sb = new StringBuffer();
                String s1 = sb.append(hour).append(":").append(minut).append(":").append(s).toString();
                jobsandshift_edit_endtime5.setText(s1);

                // Code Is Time Difference
                try {
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm:aa");
                    Date Date1 = format.parse(jobsandshift_edit_starttime4.getText().toString());
                    Date Date2 = format.parse(jobsandshift_edit_endtime5.getText().toString());
                    long mills = Date2.getTime() - Date1.getTime();
                    int Hours = (int) (mills / (1000 * 60 * 60));
                    int Mins = (int) (mills / (1000 * 60)) % 60;
                    String diff = Hours + ":" + Mins; // updated value every1 second
                    jobsandshift_edit_eventhours3.setText(diff);
                } catch (Exception e) {
                }
            }
        };
        jobsandshift_edit_endtime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context, time1, hour, minut, false).show();
            }
        });


        // TODO BUTTON UNPAID CLICK EVENT
        jobsandshift_btnunpaidbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jobsandshift_edit_eventname2.getText().toString().trim().length() == 0) {
                    jobsandshift_edit_eventname2.setError("Enter Event Name");
                    return;
                } else if (jobsandshift_edit_starttime4.getText().toString().equals("")) {
                    Toast.makeText(context, "Select Start Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (jobsandshift_edit_endtime5.getText().toString().equals("")) {
                    Toast.makeText(context, "Select End Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (jobsandshift_editeventhoursrate6.getText().toString().trim().length() == 0) {
                    jobsandshift_editeventhoursrate6.setError("Enter Hourerate");
                    return;
                } else {
                    new json_AddShift().execute();
                    utils = new Utils(JobsandShiftActvity.this);
                }
            }
        });
    }

    private class json_AddShift extends AsyncTask<String, String, String> {
        ProgressDialog pd;

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
            stringValue();
            Log.d("URL --", Constant.BASE_URL + "add_job_shift.php?event_name=" + str_eventshiftname + "&event_date=" + str_currentdate + "&event_start_time=" + str_starttime + "&event_end_time=" + str_endtime + "&event_hours=" + str_eventhours + "&event_hourly_rate=" + str_eventhourerate + "&event_RGBColor_code=" + str_color + "&emp_id=" + id);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("event_name", str_eventshiftname);
            hashMap.put("event_date", str_currentdate);
            hashMap.put("event_start_time", str_starttime);
            hashMap.put("event_end_time", str_endtime);
            hashMap.put("event_hours", str_eventhours);
            hashMap.put("event_hourly_rate", str_eventhourerate);
            hashMap.put("event_RGBColor_code", String.valueOf(str_color));
            hashMap.put("emp_id", id);
            return utils.getResponseofPost(Constant.BASE_URL + "add_job_shift.php?event_name", hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pd.isShowing()) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                        Toast.makeText(context, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        Clear();
                    } else {
                        Toast.makeText(context, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void Clear() {
        jobsandshift_eventusername0.setText("");
        jobsandshift_edit_eventname2.setText("");
        jobsandshift_edit_starttime4.setText("");
        jobsandshift_edit_endtime5.setText("");
        jobsandshift_edit_eventhours3.setText("");
        jobsandshift_editeventhoursrate6.setText("");
    }

    private void stringValue() {
        str_eventusername0 = jobsandshift_eventusername0.getText().toString().trim();
        str_eventshiftname = jobsandshift_edit_eventname2.getText().toString().trim();
        str_starttime = jobsandshift_edit_starttime4.getText().toString().trim();
        str_endtime = jobsandshift_edit_endtime5.getText().toString().trim();
        str_eventhours = jobsandshift_edit_eventhours3.getText().toString().trim();
        str_eventhourerate = jobsandshift_editeventhoursrate6.getText().toString().trim();
    }
}