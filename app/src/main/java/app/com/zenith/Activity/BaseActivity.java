package app.com.zenith.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import app.com.zenith.Utils.Utils;

/**
 * Created by archirayan on 14-Feb-17.
 */

public class BaseActivity extends AppCompatActivity
{
    // TODO Activity for BaseActivity   Page  **** Sanjay Umaraniya *******

    @Override
    public void setContentView(int layoutResId) {

        super.setContentView(layoutResId);
    }

    protected void toast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int resId) {
        toast(this.getResources().getText(resId));
    }

    protected void startActivity(Class klass) {
        startActivity(new Intent(this, klass));
    }

    protected String getText(EditText eTxt) {
        return eTxt == null ? "" : eTxt.getText().toString().trim();
    }

    protected boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    protected void write(String key, String val) {
        Utils.WriteSharePrefrence(this, key, val);
    }

    protected String read(String key) {
        return Utils.ReadSharePrefrence(this, key);
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
