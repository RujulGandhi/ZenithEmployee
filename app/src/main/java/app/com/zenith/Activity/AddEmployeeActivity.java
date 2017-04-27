package app.com.zenith.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.recurrencepicker.Utils;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.GetFilePathFromDevice;


public class AddEmployeeActivity extends AppCompatActivity {
    private EditText add_employeename;
    private EditText add_email;
    private EditText add_password;
    private EditText add_contactno;
    private EditText add_salary;
    private EditText add_address;
    private Spinner add_gender;
    private ImageView addbtn_image;
    private Button add_btnaddemp;
    private String str_employeename;
    private String str_email;
    private String str_password;
    private String str_contactno;
    private String str_salary;
    private String str_address;
    private String str_gender;
    private String str_btn_image;
    private String imgDecodableString;
    public final int REQUEST_CODE = 100;
    private static final int SELECT_PICTURE = 100;
    public Context context = this;
    public Utils utils;
    private Toolbar toolbar;
    private ImageView toolbar_icon;
    private TextView toolbar_title2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        toolbar = (Toolbar) findViewById(R.id.employee_toolbar);
        setSupportActionBar(toolbar);
        toolbar_icon = (ImageView) findViewById(R.id.employeelist_backbutton);
        toolbar_title2 = (TextView) findViewById(R.id.employeelist_title);
        toolbar_title2.setText("Add Employee");
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        findViewById();
        genderSelectItem();
        addbtn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
        add_btnaddemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_employeename.getText().toString().trim().length() == 0) {
                    add_employeename.setError("Please Enter Name");
                    return;
                } else if (add_email.getText().toString().trim().length() == 0) {
                    add_email.setError("Please Enter Email");
                    return;
                } else if (add_password.getText().toString().trim().length() == 0) {
                    add_password.setError("Please Enter Password");
                    return;
                } else if (add_contactno.toString().trim().length() == 0) {
                    add_contactno.setError("Please Enter Contact No");
                    return;
                } else if (add_gender.getSelectedItem().equals("SelectLeaveType")) {
                    Toast.makeText(context, "Select Leave Type", Toast.LENGTH_SHORT).show();
                    return;
                } else if (add_salary.toString().trim().length() == 0) {
                    add_salary.setError("Please Enter Salary");
                    return;
                } else if (add_address.toString().trim().length() == 0) {
                    add_address.setError("Please Enter Address");
                    return;
                } else {
                    str_employeename = add_employeename.getText().toString().trim();
                    str_email = add_email.getText().toString().trim();
                    str_password = add_password.getText().toString().trim();
                    str_contactno = add_contactno.getText().toString().trim();
                    str_salary = add_salary.getText().toString().trim();
                    str_address = add_address.getText().toString().trim();
                    str_gender = add_gender.getSelectedItem().toString().trim();
                    try {
                        Ion.with(context)
                                .load(Constant.BASE_URL + "add_employee.php?")
                                .setMultipartParameter("emp_name", URLEncoder.encode(str_employeename, "UTF-8"))
                                .setMultipartParameter("emp_email", URLEncoder.encode(str_email, "UTF-8"))
                                .setMultipartParameter("emp_password", URLEncoder.encode(str_password, "UTF-8"))
                                .setMultipartParameter("emp_phone", URLEncoder.encode(str_contactno, "UTF-8"))
                                .setMultipartParameter("emp_gender", URLEncoder.encode(str_gender, "UTF-8"))
                                .setMultipartParameter("emp_address", URLEncoder.encode(str_address, "UTF-8"))
                                .setMultipartParameter("emp_salary", URLEncoder.encode(str_salary, "UTF-8"))
                                .setMultipartFile("file", new File(imgDecodableString))
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
                                        Log.d("JSONRESULT@@" + "", result);
                                        Toast.makeText(context, "Employee Add Successfully", Toast.LENGTH_SHORT).show();
                                        Clear();
                                    }
                                });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void Clear() {
        add_employeename.setText("");
        add_email.setText("");
        add_password.setText("");
        add_contactno.setText("");
        add_salary.setText("");
        add_address.setText("");
    }

    private void genderSelectItem() {
        String strType[] = {"SelectLeaveType", "Male", "Female"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strType);       // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        add_gender.setAdapter(dataAdapter);
        add_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = add_gender.getSelectedItemPosition();
                if (position != 0) {
                    String st = add_gender.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
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
            Toast.makeText(AddEmployeeActivity.this, "Permission is not granted.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                imgDecodableString = GetFilePathFromDevice.getPath(this, data.getData());
                addbtn_image.setImageURI(Uri.fromFile(new File(imgDecodableString)));

            }
        }
    }

    private void findViewById() {
        add_employeename = (EditText) findViewById(R.id.activity_add_employeename);
        add_email = (EditText) findViewById(R.id.activity_add_email);
        add_password = (EditText) findViewById(R.id.activity_add_password);
        add_contactno = (EditText) findViewById(R.id.activity_add_contactno);
        add_salary = (EditText) findViewById(R.id.activity_add_salary);
        add_address = (EditText) findViewById(R.id.activity_add_address);
        add_gender = (Spinner) findViewById(R.id.activity_add_gender);
        addbtn_image = (ImageView) findViewById(R.id.activity_add_btn_image);
        add_btnaddemp = (Button) findViewById(R.id.activity_add_btnaddemp);
    }
}