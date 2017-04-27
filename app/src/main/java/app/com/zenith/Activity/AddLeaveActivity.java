package app.com.zenith.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.GetFilePathFromDevice;
import app.com.zenith.Utils.Utils;
import butterknife.ButterKnife;

import static app.com.zenith.Utils.Constant.USERID;

/**
 * Created by archirayan on 23-Feb-17.
 */
public class AddLeaveActivity extends BaseActivity {
    // TODO Activity for Training Program ADD POST Page  **** Sanjay Umaraniya *******

    private static final String FRAG_TAG_DATE_PICKER_START_DATE = "startDate";
    private static final String FRAG_TAG_DATE_PICKER_END_DATE = "endDate";
    private TextView addleave_duration;
    private TextView startDateTv, endDateTv;
    private EditText noteEdt;
    private Spinner add_leave_type;
    private ImageView pick_Imagev;
    public Calendar cal;
    private Context context = this;
    private Button button_post;
    public Utils utils;
    private String strUserId;
    private ImageView toolbar_icon;
    private String str_leavetype, str_startdate, str_enddate, str_notes;
    public final int REQUEST_CODE = 100;
    private String imgDecodableString;
    private static final int SELECT_PICTURE = 100;


    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leave);
        ButterKnife.bind(this);
        utils = new Utils(AddLeaveActivity.this);
        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.leaveapplication);
        toolbar_icon = (ImageView) findViewById(R.id.toolbar_icon);
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar_icon.setImageResource(R.drawable.cancelimage);
        cal = Calendar.getInstance();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        findViewsById();
        setDateTimeField();


        strUserId = utils.ReadSharePrefrence(AddLeaveActivity.this, USERID);
        add_leave_type = (Spinner) findViewById(R.id.activity_add_leave_type);
        addleave_duration = (TextView) findViewById(R.id.activity_add_leave_duration);
        noteEdt = (EditText) findViewById(R.id.activity_add_leave_edit_note);
        button_post = (Button) findViewById(R.id.activity_add_leave_button_post);
        pick_Imagev = (ImageView) findViewById(R.id.activity_add_leave_pick_image);

        String strType[] = {"SelectLeaveType", "AnnualLeave", "MedicalLeave", "UnpaidLeave"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strType);       // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_leave_type.setAdapter(dataAdapter);
        add_leave_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = add_leave_type.getSelectedItemPosition();
                if (position != 0) {
                    String st = add_leave_type.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        button_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add_leave_type.getSelectedItem().equals("SelectLeaveType")) {
                    Toast.makeText(context, "Select Leave Type", Toast.LENGTH_SHORT).show();
                    return;
                } else if (startDateTv.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "Select Start Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (endDateTv.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "Select End Date ", Toast.LENGTH_SHORT).show();
                } else if (noteEdt.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "Enter Note", Toast.LENGTH_SHORT).show();
                } else {
                    str_leavetype = add_leave_type.getSelectedItem().toString();
                    str_startdate = startDateTv.getText().toString().trim();
                    str_enddate = endDateTv.getText().toString().trim();
                    str_notes = noteEdt.getText().toString().trim();
                    try {

                        Ion.with(context)
                                .load(Constant.BASE_URL + "add_leave_application.php?")
                                .setMultipartParameter("leave_type_id", URLEncoder.encode(str_leavetype, "UTF-8"))
                                .setMultipartParameter("from_date", startDateTv.getText().toString())
                                .setMultipartParameter("to_date", endDateTv.getText().toString())
                                .setMultipartParameter("leave_notes", URLEncoder.encode(str_notes, "UTF-8"))
                                .setMultipartParameter("emp_id", URLEncoder.encode(strUserId, "UTF-8"))
                                .setMultipartFile("file", new File(imgDecodableString))
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
                                        Log.d("JSONRESULT@@" + "", result);
                                    }
                                });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        startDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
        endDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();

            }
        });
        pick_Imagev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
    }

    private void findViewsById() {
        startDateTv = (TextView) findViewById(R.id.activity_add_leave_start_date);
        endDateTv = (TextView) findViewById(R.id.activity_add_leave_end_date);
        str_startdate = startDateTv.getText().toString().trim();
        str_enddate = endDateTv.getText().toString().trim();
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDateTv.setText(dateFormatter.format(newDate.getTime()));
                Log.d("EndDete", "" + str_enddate);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDateTv.setText(dateFormatter.format(newDate.getTime()));
                Log.d("StartDete", "" + startDateTv);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {

        } else {
            Toast.makeText(AddLeaveActivity.this, "Permission is not granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                imgDecodableString = GetFilePathFromDevice.getPath(this, data.getData());
                pick_Imagev.setImageURI(Uri.fromFile(new File(imgDecodableString)));
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}