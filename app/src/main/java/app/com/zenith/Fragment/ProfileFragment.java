package app.com.zenith.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Pattern;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.R.id.employeeprofile_fragment_edittext_name;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment
{
    // TODO Fragment Class For Employee Profile  Page  *******   Sanjay Umaraniya  **********

    private Button button_PasswordUpdate;
    private Button button_LoginUpdate;
    private EditText edittext_Password;
    private EditText edittext_confirmPassword;
    private EditText edittext_Name;
    private EditText edittext_Email;
    private String str_password;
    private String str_confirmpassword;
    private String str_name;
    private String str_email;
    private String emp_id;
    private Utils utils;
    private List nameValuePairs;
    String strpassword, strconfirmpassword, stud_id, strusername, stremailid;
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee_profile, container, false);
        // TODO Initialization Variable...
        edittext_Name = (EditText) view.findViewById(employeeprofile_fragment_edittext_name);
        edittext_Email = (EditText) view.findViewById(R.id.employeeprofile_fragment_edittext_email);
        edittext_Password = (EditText) view.findViewById(R.id.employeeprofile_fragment_edittext_password);
        edittext_confirmPassword = (EditText) view.findViewById(R.id.employeeprofile_fragment_edittext_confirmpassword);
        button_LoginUpdate = (Button)view.findViewById(R.id.employeeprofile_fragment_button_login_update);
        button_PasswordUpdate = (Button) view.findViewById(R.id.employeeprofile_fragment_button_changepassword_update);

        emp_id = utils.ReadSharePrefrence(getActivity(), Constant.USERID);

        // TODO PASSWORD UPDATE BUTTON
        button_PasswordUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                str_password = edittext_Password.getText().toString().trim();
                strconfirmpassword = edittext_confirmPassword.getText().toString().trim();
                if (edittext_Password.getText().toString().trim().length() == 0) {
                    edittext_Password.setError("Enter Password");
                    return;
                } else if (edittext_confirmPassword.getText().toString().trim().length() == 0) {
                    edittext_confirmPassword.setError("Enter Confirm Password");
                    return;
                }
                if (strpassword.equals(strconfirmpassword)) {
                    utils = new Utils(getActivity());
                    new emp_changePassword().execute();
                } else {
                    Toast.makeText(getActivity(), "Password do not macth.", Toast.LENGTH_SHORT).show();
                    Clear();
                }
            }
        });



        // TODO PROFILE UPDATE BUTTON
        button_LoginUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name = edittext_Name.getText().toString().trim();
                str_email = edittext_Email.getText().toString().trim();
                if (edittext_Name.getText().toString().trim().length() == 0) {
                    edittext_Name.setError("Please Enter Name");
                    return;
                } else if (edittext_Email.getText().toString().trim().length() == 0) {
                    edittext_Email.setError("Please Enter Email");
                    return;
                }
                if (checkEmail(str_email)) {
                    utils = new Utils(getActivity());
                    new emp_logingprofileUpdate().execute();
                } else {
                    Toast.makeText(getActivity(), "Email id not macth", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }



    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private void Clear() {
        edittext_Email.setText("");
        edittext_Password.setText("");
        edittext_Name.setText("");
        edittext_confirmPassword.setText("");
    }


    // TODO PASSWORD CHANGE    ***** Sanjay Umaraniya  *****
    private class emp_changePassword extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            String str_url = utils.getResponseofGet(Constant.BASE_URL + "change_password.php?" + "emp_id=" + emp_id + "&emp_password=" + str_password);
            Log.d("URL", "" + str_url);
            return utils.MakeServiceCall(str_url);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    Toast.makeText(getActivity(), "Password Change Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // TODO Profile UPDATE     *********
    private class emp_logingprofileUpdate extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            String str_url = utils.getResponseofGet(Constant.BASE_URL + "update_profile.php?" + "emp_id=" + emp_id + "&emp_name=" + str_name + "&emp_email=" + str_email);
            Log.d("URL", "" + str_url);
            return utils.MakeServiceCall(str_url);
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true"))
                {
                    Toast.makeText(getActivity(), "Profile Update Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}