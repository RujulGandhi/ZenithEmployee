package app.com.zenith.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.zenith.R;

public class TrainingDetailsActivity extends AppCompatActivity
{
    private Context  context=this;
    private String training_title;
    private String training_subject;
    private String training_subtitle;
    private String training_image;
    private TextView training_details_title;
    private TextView training_details_subject;
    private TextView training_details_content;
    private ImageView training_details_image;
    private Toolbar toolbar;
    private ImageView toolbar_icon;
    private ImageView alertdailog_image;
    private TextView toolbar_title2;
    private  LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);
        toolbar = (Toolbar) findViewById(R.id.employee_toolbar);
        setSupportActionBar(toolbar);
        toolbar_icon= (ImageView) findViewById(R.id.employeelist_backbutton);
        toolbar_title2= (TextView) findViewById(R.id.employeelist_title);
        toolbar_title2.setText("Training Details");
        toolbar_icon.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        findviewById();
        Intent intent = getIntent();
        training_title = intent.getExtras().getString("title");
        training_details_title.setText(training_title);
        training_subject = intent.getExtras().getString("subject");
        training_details_subject.setText(training_subject);
        training_subtitle = intent.getExtras().getString("subtitle");
        training_details_content.setText(training_subtitle);
        training_image = intent.getExtras().getString("image");
        Picasso.with(context).load(training_image).placeholder(R.drawable.ic_placeholder).into(training_details_image);
        training_details_image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                View view=inflater.inflate(R.layout.cust_alertimage_dailog,null);
                setContentView(view);
                alertdailog_image= (ImageView) findViewById(R.id.alertdailog_image);
                Picasso.with(context).load(training_image).placeholder(R.drawable.ic_placeholder).into(alertdailog_image);
                builder.show();
            }
        });
    }
    private void findviewById()
    {
        training_details_title= (TextView) findViewById(R.id.activity_training_details_title);
        training_details_subject= (TextView) findViewById(R.id.activity_training_details_subject);
        training_details_content= (TextView) findViewById(R.id.activity_training_details_content);
        training_details_image= (ImageView) findViewById(R.id.activity_training_details_imaige);
    }
}