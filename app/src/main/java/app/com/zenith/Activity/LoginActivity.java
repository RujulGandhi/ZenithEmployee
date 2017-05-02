package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.com.zenith.Utils.Constant.ADMIN;
import static app.com.zenith.Utils.Constant.ADMIN_TOTAL_EMP;
import static app.com.zenith.Utils.Constant.ADMIN_TOTAL_STD;
import static app.com.zenith.Utils.Constant.EMPLOYEE;
import static app.com.zenith.Utils.Constant.STUDENT;
import static app.com.zenith.Utils.Constant.USERID;
import static app.com.zenith.Utils.Constant.USERNAME;
import static app.com.zenith.Utils.Constant.USERROLE;

public class LoginActivity extends BaseActivity {
    // TODO Activity for Loginscreen  Page  **** Sanjay Umaraniya *******

    private static final String TAG = "TAG";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    @BindView(R.id.activity_login_email)
    public EditText emailEdt;
    @BindView(R.id.activity_login_password)
    public EditText passwordEdt;
    @BindView(R.id.activity_login_submit)
    public Button submitBtn;
    public String email, password;
    public ImageView toolbar_icon;
    public Utils utils;
    private SharedPreferences sp;
    private int strtotal_emp;
    private int strtotal_std;
    private int struserid;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO/* Developed By :- Sanjay Umaraniya.   [ Login Screen ] */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        utils = new Utils(LoginActivity.this);
        submitBtn = (Button) findViewById(R.id.activity_login_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    public void getData() {
        if (emailEdt.getText().toString().trim().length() == 0) {
            emailEdt.setError("Enter Email Id");
            return;
        } else if (passwordEdt.getText().toString().trim().length() == 0) {
            passwordEdt.setError("Enter  Password");
            return;
        } else if (validateusername() == false) {
            Toast.makeText(LoginActivity.this,
                    "Enter Valid Email Address ",
                    Toast.LENGTH_SHORT).show();
        } else {
            email = emailEdt.getText().toString();
            password = passwordEdt.getText().toString();
            new Login().execute();
        }
    }


    private void Clear() {
        emailEdt.setText("");
        passwordEdt.setText("");
    }

    protected boolean validateusername() {
        if (emailEdt.getText().toString().matches(emailPattern)) {
            return true;
        } else {
            return false;
        }
    }

    private class Login extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(LoginActivity.this);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("email", email);
            hashMap.put("password", password);
            hashMap.put("device_token_android", FirebaseInstanceId.getInstance().getId());
            return utils.getResponseofPost(Constant.BASE_URL + "login_for_all.php", hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    if (jsonObject.getString("user_type").equals("1")) {
                        JSONObject object = jsonObject.getJSONObject("login_detail");

                        struserid = object.getInt("id");
                        username = object.getString("username");
                        strtotal_emp = jsonObject.getInt("total_employee");
                        strtotal_std = jsonObject.getInt("total_student");

                        write(USERID, String.valueOf(struserid));
                        write(USERNAME, username);
                        write(USERROLE, ADMIN);
                        write(ADMIN_TOTAL_EMP, String.valueOf(strtotal_emp));
                        write(ADMIN_TOTAL_STD, String.valueOf(strtotal_std));

                        Toast.makeText(LoginActivity.this, "Admin Login Successfully", Toast.LENGTH_SHORT).show();

                        Intent intentAdmin = new Intent(LoginActivity.this, AdminMainActivity.class);
                        startActivity(intentAdmin);
                        Clear();
                        finish();
                    } else if (jsonObject.getString("user_type").equals("2")) {

                        JSONObject object = jsonObject.getJSONObject("login_detail");
                        struserid = object.getInt("emp_id");
                        username = object.getString("emp_name");

                        write(USERID, String.valueOf(struserid));
                        write(USERNAME, username);
                        write(USERROLE, EMPLOYEE);

                        Toast.makeText(LoginActivity.this, "Employee Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intentemployee = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentemployee);
                        Clear();
                        finish();

                    } else if (jsonObject.getString("user_type").equals("3")) {
                        JSONObject object = jsonObject.getJSONObject("login_detail");
                        struserid = object.getInt("stu_id");
                        username = object.getString("stu_firstname");

                        write(USERID, String.valueOf(struserid));
                        write(USERNAME, username);
                        write(USERROLE, STUDENT);

                        Toast.makeText(LoginActivity.this, "Student Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intentStudent = new Intent(LoginActivity.this, StudentMainActivity.class);
                        startActivity(intentStudent);
                        Clear();
                        finish();

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login Denied...", Toast.LENGTH_SHORT).show();
                }
                Log.d("RESPONSE@@@", "" + s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}