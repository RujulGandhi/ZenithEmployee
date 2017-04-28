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

import app.com.zenith.Adapter.AdminNavigationAdapter;
import app.com.zenith.Fragment.AdminHome;
import app.com.zenith.Fragment.AdminJoblistFragment;
import app.com.zenith.Fragment.AdminLeaveFragment;
import app.com.zenith.Fragment.AdminProfileFragment;
import app.com.zenith.Fragment.AdminTrainingProgram;
import app.com.zenith.Fragment.PayrollFragment;
import app.com.zenith.Interface.ClickListener;
import app.com.zenith.R;
import app.com.zenith.Utils.RecyclerTouchListener;

import static app.com.zenith.Utils.Utils.ClearaSharePrefrence;

public class AdminMainActivity extends AppCompatActivity {
// TODO Activity for Admin MainActivity Page  **** Sanjay Umaraniya *******

    public ImageView toolbar_icon;
    private DrawerLayout drawer;
    public RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    public ImageView drawerIcon;
    public TextView drawerTitle;
    // private Button
    private Toolbar toolbar;
    private TextView toolbar_title2;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.admin_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final Dialog dialog = new Dialog(AdminMainActivity.this);
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
        setContentView(R.layout.activity_admin_main);
        toolbar = (Toolbar) findViewById(R.id.admin_toolbar);
        setSupportActionBar(toolbar);
        toolbar_icon = (ImageView) findViewById(R.id.admin_toolbar_icon);
        drawer = (DrawerLayout) findViewById(R.id.admin_drawer_layout);
        toolbar_title2 = (TextView) findViewById(R.id.admin_toolbar_title);
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawer.isDrawerOpen(GravityCompat.START))
                    drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        recyclerView = (RecyclerView) navigationView.findViewById(R.id.lst_menu_items);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new AdminNavigationAdapter(AdminMainActivity.this));
        // TODO: 15-Feb-17 Rujul Recycler View on Click
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(AdminMainActivity.this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Fragment fragment = null;
                // TODO: 15-Feb-17 Rujul On Click of item
                switch (position) {
                    case 0:
                        fragment = new AdminHome();
                        toolbar_title2.setText("Home");
                        break;
                    case 1:
                        fragment = new AdminJoblistFragment();
                        toolbar_title2.setText("Job & Shift");
                        break;
                    case 2:
                        fragment = new AdminTrainingProgram();
                        toolbar_title2.setText("Training Program");
                        break;
                    case 3:
                        fragment = new AdminLeaveFragment();
                        toolbar_title2.setText("Leave Application");
                        break;
                    case 4:
                        fragment = new PayrollFragment();
                        toolbar_title2.setText("Payroll");
                        break;
                    case 5:
                        fragment = new AdminProfileFragment();
                        toolbar_title2.setText("Profile");
                        break;
                    case 6:
                        ClearaSharePrefrence(AdminMainActivity.this);
                        Intent in = new Intent(AdminMainActivity.this, LoginActivity.class);
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
                    ft.replace(R.id.content_admin_main, fragment).commit();
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        }));
    }
}