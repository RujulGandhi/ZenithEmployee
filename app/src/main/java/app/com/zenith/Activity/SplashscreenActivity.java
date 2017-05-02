package app.com.zenith.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;

import static app.com.zenith.Utils.Constant.ADMIN;
import static app.com.zenith.Utils.Constant.EMPLOYEE;
import static app.com.zenith.Utils.Constant.STUDENT;

public class SplashscreenActivity extends AppCompatActivity {
    // TODO Activity for Splashscreen   Page  **** Sanjay Umaraniya *******
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private SharedPreferences sp;
    public Utils utils;
    private String struserid;
    private String strstudentid;
    private String stradminid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        utils = new Utils(SplashscreenActivity.this);
        struserid = utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID);
        stradminid = utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID);
        strstudentid = utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals("")) {
                    Intent in = new Intent(SplashscreenActivity.this, LoginActivity.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();
                }else{
                    if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(ADMIN)) {
                        Intent in = new Intent(SplashscreenActivity.this, AdminMainActivity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }else if(Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(EMPLOYEE)){
                        Intent in = new Intent(SplashscreenActivity.this, MainActivity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }else if(Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(STUDENT)){
                        Intent in = new Intent(SplashscreenActivity.this, StudentMainActivity.class);
                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                    }
                }
//
//                if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(ADMIN)) {
//                    if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID).equals("")) {
//                        Intent in = new Intent(SplashscreenActivity.this, LoginActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    } else {
//                        Intent in = new Intent(SplashscreenActivity.this, AdminMainActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    }
//                }
//                if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(EMPLOYEE)) {
//                    if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID).equals("")) {
//                        Intent in = new Intent(SplashscreenActivity.this, LoginActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    } else {
//                        Intent in = new Intent(SplashscreenActivity.this, MainActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    }
//                }
//                if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERROLE).equals(STUDENT)) {
//                    if (Utils.ReadSharePrefrence(SplashscreenActivity.this, Constant.USERID).equals("")) {
//                        Intent in = new Intent(SplashscreenActivity.this, LoginActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    } else {
//                        Intent in = new Intent(SplashscreenActivity.this, StudentMainActivity.class);
//                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(in);
//                        finish();
//                    }
//                }


            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}