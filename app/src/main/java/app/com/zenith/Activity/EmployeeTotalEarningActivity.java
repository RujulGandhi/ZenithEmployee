package app.com.zenith.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.Utils;
public class EmployeeTotalEarningActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener
{
    // TODO Activity for Employee Total Earninig    Page  **** Sanjay Umaraniya *******
    private LineChart mChart;
    private PieChart pieChart;
    private String emp_Id;
   private String str_url;
    public Utils utils;
   private TextView emp_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_total_earning);
        utils = new Utils(EmployeeTotalEarningActivity.this);
        emp_Name = (TextView) findViewById(R.id.emptotalearningchart_empname);
        // TODO  Chart Cretae  Sanjay Umaraniya
        mChart = (LineChart) findViewById(R.id.linechart);
        mChart.setOnChartGestureListener(this);
        mChart.setDescription("Total Earning");
        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        mChart.moveViewToX(10);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        new totalearningChart().execute();
    }




    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {
    }


    // TODO TOTAL EARNING CHART ......
    private class totalearningChart extends AsyncTask<String,String,String>
    {
        ProgressDialog pd;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pd=new ProgressDialog(EmployeeTotalEarningActivity.this);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params)
        {

            Log.d("UserId",""+emp_Id);
            emp_Id=utils.ReadSharePrefrence(EmployeeTotalEarningActivity.this,Constant.USERID);
            str_url=utils.getResponseofGet(Constant.BASE_URL+"get_chart_employee.php?emp_id="+2);
            Log.d("Response",""+str_url);
            return str_url;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject mainObejct = new JSONObject(s);
                if (mainObejct.getString("status").equalsIgnoreCase("true")) {
                    emp_Name.setText(mainObejct.getString("employee_name"));
                    utils.WriteSharePrefrence(EmployeeTotalEarningActivity.this, Constant.USERID, mainObejct.getString("employee_id"));
                    emp_Id = utils.ReadSharePrefrence(EmployeeTotalEarningActivity.this, Constant.USERID);
                    ArrayList<String> xVals = new ArrayList<String>();
                    xVals.add("Jan");
                    xVals.add("Feb");
                    xVals.add("Mar");
                    xVals.add("Apr");
                    xVals.add("May");
                    xVals.add("Jun");
                    xVals.add("Jul");
                    xVals.add("Aug");
                    xVals.add("Sap");
                    xVals.add("Oct");
                    xVals.add("Nav");
                    xVals.add("Dec");

                    ArrayList<Entry> yVals = new ArrayList<Entry>();

                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("january").getString("event_monthly_earning")), 0));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("february").getString("event_monthly_earning")), 1));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("march").getString("event_monthly_earning")), 2));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("april").getString("event_monthly_earning")), 3));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("may").getString("event_monthly_earning")), 4));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("june").getString("event_monthly_earning")), 5));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("july").getString("event_monthly_earning")), 6));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("august").getString("event_monthly_earning")), 7));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("septmber").getString("event_monthly_earning")), 8));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("october").getString("event_monthly_earning")), 9));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("november").getString("event_monthly_earning")), 10));
                    yVals.add(new Entry(Float.parseFloat(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("december").getString("event_monthly_earning")), 11));

                    LineDataSet set1;

                    set1 = new LineDataSet(yVals, "");
                    set1.setFillAlpha(110);
                    set1.setColor(Color.BLACK);
                    set1.setCircleColor(Color.BLACK);
                    set1.setLineWidth(1f);
                    set1.setCircleRadius(3f);
                    set1.setDrawCircleHole(false);
                    set1.setValueTextSize(9f);
                    set1.setDrawFilled(true);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                    dataSets.add(set1); // add the datasets
                    LineData data = new LineData(xVals, dataSets);
                    mChart.setData(data);

                    PieChart pieChart = (PieChart) findViewById(R.id.piechart);
                    pieChart.setUsePercentValues(true);
                    ArrayList<String> xVals1 = new ArrayList<String>();
                    xVals1.add("Time");
                    xVals1.add("Amount");
                    ArrayList<Entry> yVals1 = new ArrayList<Entry>();

                    yVals1.add(new Entry(Float.parseFloat(String.valueOf(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("january").getString("total_working_hours"))), 0));
                    yVals1.add(new Entry(Float.parseFloat(String.valueOf(mainObejct.getJSONArray("employee_event_details").getJSONObject(0).getJSONObject("january").getString("total_earning"))), 1));
                    PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
                    PieData data1 = new PieData(xVals, dataSet);
                    data.setValueFormatter(new PercentFormatter());
                    dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                    dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
                    dataSet.setColors(ColorTemplate.PASTEL_COLORS);
                    pieChart.setData(data1);
                }
                else
                {
                    Toast.makeText(EmployeeTotalEarningActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}