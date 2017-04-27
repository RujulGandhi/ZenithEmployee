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
import app.com.zenith.Adapter.StudentNavigationAdapter;
import app.com.zenith.Fragment.StudentHistoryFragment;
import app.com.zenith.Fragment.StudentHomeFragment;
import app.com.zenith.Fragment.StudentProfileFragment;
import app.com.zenith.Fragment.StudentTrainingprogram;
import app.com.zenith.Interface.ClickListener;
import app.com.zenith.R;
import app.com.zenith.Utils.RecyclerTouchListener;
import static app.com.zenith.Utils.Utils.ClearaSharePrefrence;

public class StudentMainActivity extends AppCompatActivity
{
    // TODO Activity for Student MainActivity   Page  **** Sanjay Umaraniya *******
    ImageView toolbar_icon;
     DrawerLayout drawer2;
    public RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ImageView drawerIcon;
    private TextView drawerTitle;
    private Toolbar toolbar1;
    private TextView toolbar_title2;

    @Override
    public void onBackPressed()
    {
         this.drawer2 = (DrawerLayout) findViewById(R.id.std_drawer_layout);
        if (drawer2.isDrawerOpen(GravityCompat.START))
        {
            drawer2.closeDrawer(GravityCompat.START);
        } else {
            final Dialog dialog = new Dialog(StudentMainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.show();
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
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        this.drawer2 = (DrawerLayout) findViewById(R.id.std_drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.std_toolbar);
        setSupportActionBar(toolbar);
        toolbar_icon = (ImageView) findViewById(R.id.std_toolbar_icon);
        toolbar_title2 = (TextView) findViewById(R.id.std_toolbar_title);
        toolbar_icon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!drawer2.isDrawerOpen(GravityCompat.START))
                    drawer2.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView = (RecyclerView) navigationView.findViewById(R.id.lst_menu_items);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StudentNavigationAdapter((StudentMainActivity.this)));
        // TODO: 15-Feb-17 Rujul Recycler View on Click
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(StudentMainActivity.this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Fragment fragment = null;
                // TODO: 15-Feb-17 Rujul On Click of item
                switch (position) {
                    case 0:
                        fragment = new StudentHomeFragment();
                        toolbar_title2.setText("Home");
                        break;
                    case 1:
                        fragment = new StudentTrainingprogram();
                        toolbar_title2.setText("Training Program");
                        break;
                    case 2:
                        fragment = new StudentHistoryFragment();
                        toolbar_title2.setText("History");
                        break;
                    case 3:
                        fragment = new StudentProfileFragment();
                        toolbar_title2.setText("Profile");
                        break;

                    case 4:
                        ClearaSharePrefrence(StudentMainActivity.this);
                        Intent in = new Intent(StudentMainActivity.this, LoginActivity.class);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(in);
                        finish();
                        break;
                    default:
                        break;
                }
                if (fragment != null)
                {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.content_student_main, fragment).commit();
                    drawer2.closeDrawer(GravityCompat.START);
                }
            }
        }));
    }
}