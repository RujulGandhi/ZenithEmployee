package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
import petrov.kristiyan.colorpicker.ColorPicker;

import static app.com.zenith.R.id.jobsandshiftactivity_edit_eventhours3;


public class JobsAndShiftActvity extends AppCompatActivity {
    // TODO Activity for JobsandShift Page  **** Sanjay Umaraniya *******
    private TextView jobsandshift_eventusername0;
    private TextView jobsandshift_edit_eventhours3;
    private TextView jobsandshift_edit_starttime4;
    private TextView jobsandshift_edit_endtime5;
    private ImageView admin_btnadddshift;
    private EditText jobsandshift_edit_eventname2;
    private EditText jobsandshift_editeventhoursrate6;
    public Utils utils;
    public String str_eventusername0;
    private long diffHours;
    public String str_eventshiftname, str_starttime, str_endtime, str_eventhours, str_eventhourerate, str_currentdate;
    private ImageView jobsandshifttoolbar_backbutton;
    private String id;
    private int minut, hour;
    private Calendar calendar;
    private TextView shift_circle_txtcolor_redark1;
    private String str_color;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsand_shift_actvity);
        utils = new Utils(JobsAndShiftActvity.this);
        toolbar = (Toolbar) findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);

        // TODO  Initialize variable...
        jobsandshift_eventusername0 = (TextView) findViewById(R.id.jobsandshiftactivity_eventusername0);
        jobsandshift_edit_eventhours3 = (TextView) findViewById(jobsandshiftactivity_edit_eventhours3);
        jobsandshift_edit_starttime4 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_starttime4);
        jobsandshift_edit_endtime5 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_endtime5);
        admin_btnadddshift = (ImageView) findViewById(R.id.jobsandshifttoolbar_right_button);
        jobsandshift_edit_eventname2 = (EditText) findViewById(R.id.jobsandshiftactivity_edit_eventname2);
        jobsandshift_editeventhoursrate6 = (EditText) findViewById(R.id.jobsandshiftactivity_editeventhoursrate6);
        shift_circle_txtcolor_redark1 = (TextView) findViewById(R.id.shift_circle_txtcolor_redark1);

        // TODO Get Extra to get Event

        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        id = intent.getExtras().getString("Id");
        jobsandshift_eventusername0.setText(name);
        jobsandshifttoolbar_backbutton = (ImageView) findViewById(R.id.jobsandshifttoolbar_backbutton);
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
                            final ColorPicker colorPicker = new ColorPicker(JobsAndShiftActvity.this);
                            final ArrayList<String> colors = new ArrayList<>();
                            colors.add("#FAFAFA");
                            colors.add("#F5F5F5");
                            colors.add("#EEEEEE");
                            colors.add("#E0E0E0");
                            colors.add("#BDBDBD");
                            colors.add("#9E9E9E");
                            colors.add("#757575");
                            colors.add("#616161");
                            colors.add("#424242");
                            colors.add("#212121");
                            colors.add("#000000");
                            colors.add("#ffffff");


                            // COlorCode Formate:- UIExtendedSRGBColorSpace 0 0.588235 0.533333 1
                            colorPicker.setColors(colors).setDefaultColorButton(Color.parseColor("#f84c44")).setColumns(5).setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                @Override
                                public void onChooseColor(int position, int color) {
                                    int colorData = (int) Long.parseLong(colors.get(position).substring(1, colors.get(position).length()), 16);
                                    float r = (colorData >> 16) & 0xFF;
                                    float g = (colorData >> 8) & 0xFF;
                                    float b = (colorData >> 0) & 0xFF;
                                    float red = r / 255;
                                    float green = g / 255;
                                    float blue = b / 255;
                                    shift_circle_txtcolor_redark1.setTextColor(color);
                                    StringBuilder messages = new StringBuilder();
                                    messages.append(String.valueOf(+red + "," + green + "," + blue));
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
                if (minut <= 0) {
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
                new TimePickerDialog(JobsAndShiftActvity.this, time, hour, minut, false).show();

            }
        });


        final TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {

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
                if (minut <= -1) {
                    minut1 = "0" + minut;
                } else {
                    minut1 = String.valueOf(minut);
                }
                StringBuffer sb = new StringBuffer();
                String s1 = sb.append(hour).append(":").append(minut).append(":").append(s).toString();
                jobsandshift_edit_endtime5.setText(s1);
                getDateTimeDifference();


            }
        };
        jobsandshift_edit_endtime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(JobsAndShiftActvity.this, time1, hour, minut, false).show();
            }
        });


        // TODO BUTTON UNPAID CLICK EVENT
        admin_btnadddshift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jobsandshift_edit_eventname2.getText().toString().trim().length() == 0) {
                    jobsandshift_edit_eventname2.setError("Enter Event Name");
                    return;
                } else if (jobsandshift_edit_starttime4.getText().toString().equals("")) {
                    Toast.makeText(JobsAndShiftActvity.this, "Select Start Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (jobsandshift_edit_endtime5.getText().toString().equals("")) {
                    Toast.makeText(JobsAndShiftActvity.this, "Select End Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (jobsandshift_editeventhoursrate6.getText().toString().trim().length() == 0) {
                    jobsandshift_editeventhoursrate6.setError("Enter Hourerate");
                    return;
                } else {
                    new json_AddShift().execute();
                    utils = new Utils(JobsAndShiftActvity.this);
                }
            }
        });
    }

    private void getDateTimeDifference() {
        // // TODO: 5/1/2017   Code Is Time Difference in milliseconds
        try {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:aa");
            Date Date1 = format.parse(jobsandshift_edit_starttime4.getText().toString());
            Date Date2 = format.parse(jobsandshift_edit_endtime5.getText().toString());
            long diff = Date2.getTime() - Date1.getTime();
            long diffSeconds = diff / 1000;
            long diffMinutes = diff / (60 * 1000);
            diffHours = diff / (60 * 60 * 1000);
            jobsandshift_edit_eventhours3.setText("" + diffHours);
        } catch (Exception e) {
        }
    }


    private class json_AddShift extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(JobsAndShiftActvity.this);
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
            hashMap.put("event_RGBColor_code", str_color);
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
                        Toast.makeText(JobsAndShiftActvity.this, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                        Clear();
                    } else {
                        Toast.makeText(JobsAndShiftActvity.this, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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