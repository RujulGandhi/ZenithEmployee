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

import java.util.regex.Pattern;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentProfileFragment extends Fragment
{
    // TODO Fragment Class For Studnet Profile Page  *******   Sanjay Umaraniya  **********
    private EditText edit_password;
    private EditText edit_comfirmpassword;
    private EditText edit_name;
    private EditText  edit_emailid;
    private Button btn_updatepassword;
    private Button  btn_updateLogin;
    public Utils utils;
    private  String strpassword;
    private  String strconfirmpassword;
    private  String stud_id;
    private  String strusername;
    private  String stremailid;
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
   private String  stu_id;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);
        utils = new Utils(getActivity());

        edit_password = (EditText) view.findViewById(R.id.studentprofile_fragment_edittext_password);
        edit_comfirmpassword = (EditText) view.findViewById(R.id.studentprofile_fragment_edittext_confirmpassword);
        edit_name = (EditText) view.findViewById(R.id.studentprofile_fragment_edittext_name);
        edit_emailid = (EditText) view.findViewById(R.id.studentprofile_fragment_edittext_email);
        btn_updatepassword = (Button)view .findViewById(R.id.studentprofile_fragment_button_changepassword_update);
        btn_updateLogin = (Button) view.findViewById(R.id.studentprofile_fragment_button_login_update);

        btn_updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strpassword = edit_password.getText().toString().trim();
                strconfirmpassword = edit_comfirmpassword.getText().toString().trim();
                if (edit_password.getText().toString().trim().length() == 0)
                {
                    edit_password.setError("Enter Password");
                    return;
                }
                else if (edit_comfirmpassword.getText().toString().trim().length() == 0)
                {
                    edit_comfirmpassword.setError("Enter Confirm Password");
                    return;
                }
                if (strpassword.equals(strconfirmpassword))
                {
                    new StudentProfile_Changepassoword().execute();
                }
                else
                {
                    Toast.makeText(getActivity(), "Password do not macth.", Toast.LENGTH_SHORT).show();
                    Clear();
                }
            }
        });


        // TODO Profile Update ******* Sanjay Umaraniya **********
        btn_updateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_name.getText().toString().trim().length() == 0)
                {
                    edit_name.setError("Enter Username");
                    return;
                } else if (edit_emailid.getText().toString().trim().length() == 0)
                {
                    edit_emailid.setError("Enter Email Id");
                    return;
                }
                strusername = edit_name.getText().toString().trim();
                stremailid=edit_emailid.getText().toString().trim();
                if (checkEmail(stremailid))
                {
                    new StudentProfile_UpdateLogin().execute();
                }
                else
                {
                    Toast.makeText(getActivity(), "Email id not macth", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }



    // TODO Password Update  **** Execute() ****
    private class StudentProfile_Changepassoword extends AsyncTask<String, String, String>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params)
        {
            stud_id = utils.ReadSharePrefrence(getActivity(), Constant.USERID);
            Log.d("ID", "" + stud_id);
            String str_url = utils.getResponseofGet(Constant.BASE_URL + "stu_change_password.php?" + "stu_id=" + stud_id + "&stu_password=" + strpassword);
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
                    String status = jsonObject.optString("status");
                    Toast.makeText(getActivity(), "Password Change Successfully", Toast.LENGTH_SHORT).show();
                    Clear();
                }
                else
                {
                    Toast.makeText(getActivity(), "No Action Found", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void Clear()
    {
        edit_emailid.setText("");
        edit_password.setText("");
        edit_name.setText("");
        edit_comfirmpassword.setText("");
    }


    // TODO Profile Update  **** Execute() ****
    private class StudentProfile_UpdateLogin extends AsyncTask<String, String, String> {
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
        {  stud_id = utils.ReadSharePrefrence(getActivity(), Constant.USERID);
            String strurl_updateprofil = utils.getResponseofGet(Constant.BASE_URL + "stu_update_profile.php?" + "stu_id=" + stud_id + "&stu_firstname=" + strusername + "&stu_email=" + stremailid);
            Log.d("URL", "" + strurl_updateprofil);
            return utils.MakeServiceCall(strurl_updateprofil);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true"))
                {
                    Toast.makeText(getActivity(), "Profile Update Sccessfully...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}