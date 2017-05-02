package app.com.zenith.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.zenith.Adapter.NavigationAdapter;
import app.com.zenith.Fragment.EmployeeTrainingProgramFragment;
import app.com.zenith.Fragment.EmplyeeClassshedulaFragment;
import app.com.zenith.Fragment.LeaveApplication;
import app.com.zenith.Fragment.PayrollFragment;
import app.com.zenith.Fragment.ProfileFragment;
import app.com.zenith.Interface.ClickListener;
import app.com.zenith.R;
import app.com.zenith.Utils.RecyclerTouchListener;

import static app.com.zenith.Utils.Constant.USERID;
import static app.com.zenith.Utils.Utils.ClearaSharePrefrence;
import static app.com.zenith.Utils.Utils.ReadSharePrefrence;

public class MainActivity extends AppCompatActivity {
    // TODO Activity for Employee MainActivity  Page  **** Sanjay Umaraniya *******
    public RecyclerView recyclerView;
    public LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private TextView toolbar_title2;
    private ImageView toolbar_icon;
    public ArrayList<Integer> arrayList;
    private NavigationView navigationView;


    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.emp_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.exitdailoge_logout);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.btn_no);
            dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            Button dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_yes);
            dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            dialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.emp_toolbar);
        setSupportActionBar(toolbar);
        toolbar_icon = (ImageView) findViewById(R.id.emp_toolbar_icon);
        drawer = (DrawerLayout) findViewById(R.id.emp_drawer_layout);
        toolbar_title2 = (TextView) findViewById(R.id.emp_toolbar_title);
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(GravityCompat.START))
                    drawer.openDrawer(GravityCompat.START);
            }
        });
        if (ReadSharePrefrence(MainActivity.this, USERID).equalsIgnoreCase("")) {
            Intent in = new Intent(this, LoginActivity.class);
            startActivity(in);
        } else {
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            openDefaultFragment();
            recyclerView = (RecyclerView) findViewById(R.id.lst_menu_items);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new NavigationAdapter(MainActivity.this));

            // TODO: 15-Feb-17 Rujul Recycler View on Click
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this,
                    recyclerView, new ClickListener() {
                @Override
                public void onClick(View view, final int position) {
                    Fragment fragment = null;
                    // TODO: 15-Feb-17 Rujul On Click of item
                    switch (position) {
                        case 0:
                            fragment = new EmplyeeClassshedulaFragment();
                            toolbar_title2.setText("Home");
                            break;

                        case 1:
                            fragment = new EmployeeTrainingProgramFragment();
                            toolbar_title2.setText("Training Program");
                            break;
                        case 2:
                            fragment = new LeaveApplication();
                            toolbar_title2.setText("Leave Application");
                            break;
                        case 3:
                            fragment = new PayrollFragment();
                            toolbar_title2.setText("Payroll");
                            break;
                        case 4:
                            fragment = new ProfileFragment();
                            toolbar_title2.setText("Profile");
                            break;
                        case 5:
                            ClearaSharePrefrence(MainActivity.this);
                            Intent in = new Intent(MainActivity.this, LoginActivity.class);
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                            break;
                        default:
                            break;
                    }
                    if (fragment != null) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.content_main, fragment).commit();
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            }));
        }
    }

    private void openDefaultFragment() {
        Fragment fragment = null;
        fragment = new EmplyeeClassshedulaFragment();
        toolbar_title2.setText("Home");
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment).commit();
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}