package app.com.zenith.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.com.zenith.R;
import app.com.zenith.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminProfileFragment extends Fragment
{
    // TODO Fragment Class For  Admin Profile  Page  *******   Sanjay Umaraniya  **********
    private Button button_PasswordUpdate;
    private Button button_LoginUpdate;
    private EditText edittext_Password;
    private EditText edittext_confirmPassword;
    private EditText edittext_Name;
    private EditText edittext_Email;
    private static final String TAG = "TAG";
    public Utils utils;


    //  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.adminfragment_profile, container, false);
        edittext_Name = (EditText) view.findViewById(R.id.profile_fragment_edittext_name);
        edittext_Email = (EditText) view.findViewById(R.id.profile_fragment_edittext_email);
        edittext_Password = (EditText) view.findViewById(R.id.profile_fragment_edittext_password);
        edittext_confirmPassword = (EditText) view.findViewById(R.id.profile_fragment_edittext_confirmpassword);
        button_LoginUpdate = (Button) view.findViewById(R.id.profile_fragment_button_login_update);
        button_PasswordUpdate = (Button) view.findViewById(R.id.profile_fragment_button_changepassword_update);
        return view;
    }
}