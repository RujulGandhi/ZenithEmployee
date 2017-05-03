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
import android.widget.LinearLayout;
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
    private LinearLayout jobsandshift_colorshowactivity;

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
        shift_circle_txtcolor_redark1 = (TextView) findViewById(R.id.jobsandshiftactivity_edit_eventcolor);
        jobsandshift_colorshowactivity = (LinearLayout) findViewById(R.id.jobsandshift_colorshowactivity);
        // TODO Get Extra to get Event

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
                            colors.add("#FFEBEE");
                            colors.add("#FFCDD2");
                            colors.add("#EF9A9A");
                            colors.add("#E57373");
                            colors.add("#EF5350");
                            colors.add("#F44336");
                            colors.add("#E53935");
                            colors.add("#D32F2F");
                            colors.add("#C62828");
                            colors.add("#B71C1C");
                            colors.add("#FF8A80");

                            colors.add("#FF1744");
                            colors.add("#D50000");
                            colors.add("#FCE4EC");
                            colors.add("#F8BBD0");
                            colors.add("#F48FB1");
                            colors.add("#F06292");
                            colors.add("#EC407A");
                            colors.add("#E91E63");
                            colors.add("#D81B60");
                            colors.add("#C2185B");
                            colors.add("#AD1457");
                            colors.add("#880E4F");
                            colors.add("#FF80AB");
                            colors.add("#FF4081");
                            colors.add("#F50057");
                            colors.add("#C51162");
                            colors.add("#F3E5F5");


                            colors.add("#E1BEE7");
                            colors.add("#CE93D8");
                            colors.add("#BA68C8");
                            colors.add("#AB47BC");
                            colors.add("#9C27B0");
                            colors.add("#8E24AA");
                            colors.add("#7B1FA2");
                            colors.add("#6A1B9A");
                            colors.add("#4A148C");
                            colors.add("#EA80FC");
                            colors.add("#E040FB");
                            colors.add("#D500F9");
                            colors.add("#AA00FF");

                            colors.add("#EDE7F6");
                            colors.add("#D1C4E9");
                            colors.add("#B39DDB");
                            colors.add("#9575CD");
                            colors.add("#7E57C2");
                            colors.add("#673AB7");


                            colors.add("#5E35B1");
                            colors.add("#512DA8");
                            colors.add("#4527A0");
                            colors.add("#311B92");
                            colors.add("#B388FF");
                            colors.add("#7C4DFF");
                            colors.add("#651FFF");
                            colors.add("#6200EA");
                            colors.add("#E8EAF6");
                            colors.add("#C5CAE9");
                            colors.add("#9FA8DA");
                            colors.add("#7986CB");
                            colors.add("#5C6BC0");
                            colors.add("#3F51B5");
                            colors.add("#3949AB");
                            colors.add("#303F9F");
                            colors.add("#283593");
                            colors.add("#1A237E");
                            colors.add("#8C9EFF");
                            colors.add("#536DFE");
                            colors.add("#3D5AFE");
                            colors.add("#304FFE");

                            colors.add("#E3F2FD");
                            colors.add("#BBDEFB");
                            colors.add("#90CAF9");
                            colors.add("#64B5F6");
                            colors.add("#42A5F5");
                            colors.add("#2196F3");
                            colors.add("#1E88E5");
                            colors.add("#1976D2");
                            colors.add("#1565C0");
                            colors.add("#0D47A1");
                            colors.add("#82B1FF");
                            colors.add("#448AFF");
                            colors.add("#2979FF");
                            colors.add("#2962FF");

                            colors.add("#E1F5FE");
                            colors.add("#B3E5FC");
                            colors.add("#81D4fA");
                            colors.add("#4fC3F7");
                            colors.add("#29B6FC");
                            colors.add("#03A9F4");
                            colors.add("#039BE5");
                            colors.add("#0288D1");
                            colors.add("#0277BD");
                            colors.add("#01579B");
                            colors.add("#80D8FF");
                            colors.add("#40C4FF");
                            colors.add("#00B0FF");
                            colors.add("#0091EA");


                            colors.add("#E0F7FA");
                            colors.add("#B2EBF2");
                            colors.add("#80DEEA");
                            colors.add("#4DD0E1");
                            colors.add("#26C6DA");
                            colors.add("#00BCD4");
                            colors.add("#00ACC1");
                            colors.add("#00838F");
                            colors.add("#006064");

                            colors.add("#84FFFF");
                            colors.add("#18FFFF");
                            colors.add("#00E5FF");
                            colors.add("#00B8D4");

                            colors.add("#E0F2F1");
                            colors.add("#B2DFDB");
                            colors.add("#80CBC4");
                            colors.add("#4DB6AC");


                            colors.add("#26A69A");
                            colors.add("#009688");
                            colors.add("#00897B");
                            colors.add("#00796B");
                            colors.add("#00695C");
                            colors.add("#004D40");
                            colors.add("#A7FFEB");
                            colors.add("#64FFDA");
                            colors.add("#1DE9B6");
                            colors.add("#00BFA5");

                            colors.add("#E8F5E9");
                            colors.add("#C8E6C9");
                            colors.add("#A5D6A7");
                            colors.add("#81C784");
                            colors.add("#66BB6A");
                            colors.add("#43A047");
                            colors.add("#388E3C");
                            colors.add("#2E7D32");
                            colors.add("#B9F6CA");
                            colors.add("#69F0AE");
                            colors.add("#00E676");
                            colors.add("#00C853");

                            colors.add("#F1F8E9");
                            colors.add("#DCEDC8");
                            colors.add("#C5E1A5");
                            colors.add("#AED581");
                            colors.add("#9CCC65");
                            colors.add("#8BC34A");
                            colors.add("#7CB342");
                            colors.add("#689F38");
                            colors.add("#558B2F");
                            colors.add("#33691E");
                            colors.add("#CCFF90");
                            colors.add("#B2FF59");
                            colors.add("#76FF03");
                            colors.add("#64DD17");

                            colors.add("#F9FBE7");
                            colors.add("#F0F4C3");
                            colors.add("#E6EE9C");
                            colors.add("#DCE775");
                            colors.add("#D4E157");
                            colors.add("#CDDC39");
                            colors.add("#C0CA33");
                            colors.add("#A4B42B");
                            colors.add("#9E9D24");
                            colors.add("#827717");
                            colors.add("#F4FF81");
                            colors.add("#EEFF41");
                            colors.add("#C6FF00");
                            colors.add("#AEEA00");

                            colors.add("#FFFDE7");
                            colors.add("#FFF9C4");
                            colors.add("#FFF590");
                            colors.add("#FFF176");
                            colors.add("#FFEE58");
                            colors.add("#FFEB3B");
                            colors.add("#FDD835");


                            colors.add("#FBC02D");
                            colors.add("#F9A825");
                            colors.add("#F57F17");
                            colors.add("#FFFF82");
                            colors.add("#FFFF00");
                            colors.add("#FFEA00");
                            colors.add("#FFD600");

                            colors.add("#FFF8E1");
                            colors.add("#FFECB3");
                            colors.add("#FFE082");
                            colors.add("#FFD54F");
                            colors.add("#FFCA28");
                            colors.add("#FFC107");
                            colors.add("#FFB300");
                            colors.add("#FFA000");
                            colors.add("#FF8F00");
                            colors.add("#FF6F00");
                            colors.add("#FFE57F");
                            colors.add("#FFD740");
                            colors.add("#FFC400");
                            colors.add("#FFAB00");

                            colors.add("#FFF3E0");
                            colors.add("#FFE0B2");
                            colors.add("#FFCC80");
                            colors.add("#FFB74D");
                            colors.add("#FFA726");
                            colors.add("#FF9800");
                            colors.add("#FB8C00");
                            colors.add("#F57C00");
                            colors.add("#EF6C00");
                            colors.add("#E65100");
                            colors.add("#FFD180");
                            colors.add("#FFAB40");
                            colors.add("#FF9100");
                            colors.add("#FF6D00");

                            colors.add("#FBE9A7");
                            colors.add("#FFCCBC");
                            colors.add("#FFAB91");
                            colors.add("#FF8A65");
                            colors.add("#FF7043");
                            colors.add("#FF5722");
                            colors.add("#F4511E");
                            colors.add("#E64A19");
                            colors.add("#D84315");
                            colors.add("#FF9E80");
                            colors.add("#FF6E40");
                            colors.add("#FF3D00");
                            colors.add("#DD2600");

                            colors.add("#EFEBE9");
                            colors.add("#D7CCC8");
                            colors.add("#BCAAA4");
                            colors.add("#A1887F");
                            colors.add("#8D6E63");
                            colors.add("#795548");
                            colors.add("#6D4C41");
                            colors.add("#5D4037");
                            colors.add("#4E342E");
                            colors.add("#3E2723");

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

                            colors.add("#ECEFF1");
                            colors.add("#CFD8DC");
                            colors.add("#B0BBC5");
                            colors.add("#90A4AE");
                            colors.add("#78909C");
                            colors.add("#607D8B");
                            colors.add("#546E7A");
                            colors.add("#455A64");
                            colors.add("#37474F");
                            colors.add("#263238");

                            colorPicker.setColors(colors).setDefaultColorButton(Color.parseColor("#f84c44")).setColorButtonSize(30, 30).setColumns(5).setTitle("Choose Your Color").
                                    setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                                        @Override
                                        public void setOnFastChooseColorListener(int position, int color) {
                                            int colorData = (int) Long.parseLong(colors.get(position).substring(1, colors.get(position).length()), 16);
                                            float red = (colorData >> 16) & 0xFF;
                                            float green = (colorData >> 8) & 0xFF;
                                            float blue = (colorData >> 0) & 0xFF;

                                            jobsandshift_colorshowactivity.setBackgroundColor(color);
                                            StringBuilder messages = new StringBuilder();
                                            messages.append(String.valueOf(+red + "," + green + "," + blue));
                                            str_color = String.valueOf(messages);
                                            Log.d("FINCALCOLOR", "" + str_color);
                                        }

                                        @Override
                                        public void onCancel() {

                                        }
                                    }).setRoundColorButton(true).show();
                            // COlorCode Formate:- UIExtendedSRGBColorSpace 0 0.588235 0.533333 1

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