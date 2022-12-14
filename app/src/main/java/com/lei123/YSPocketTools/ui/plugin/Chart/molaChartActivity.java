/*
package com.lei123.YSPocketTools.ui.plugin.Chart;

import static com.lei123.YSPocketTools.action.ToastAction.toast;
import static com.lei123.YSPocketTools.unit.setToolbar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.lei123.YSPocketTools.PlugIn.Chart.DateNums;
import com.lei123.YSPocketTools.R;
import com.lei123.YSPocketTools.check.cc;

import java.util.ArrayList;

public class molaChartActivity extends AppCompatActivity {

    Context context;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mola_chart);
        context = this;

        Button test = findViewById(R.id.test);
        mToolbar = findViewById(R.id.toolbar);
        setToolbar(context, mToolbar);
        loadUser();
        toast(context.getString(R.string.PleaseWait));
        getyuanshi();
        getmola();

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    String uid;
    String cookie;

    */
/**
     * ???????????????????????????
     *//*

    private void loadUser() {
        SharedPreferences preferences = context.getSharedPreferences("uid", Activity.MODE_PRIVATE);
        uid = preferences.getString("uid", "");
        cookie = preferences.getString("cookies", "");
    }


    //?????????????????????
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                CombinedChart chart = findViewById(R.id.chart1);
                drowplot(chart,yuanshiDateAndNums,"??????");
                PieChart chart2 = findViewById(R.id.piechart1);
                drowPiePlot(chart2,yuanshiDateAndNums,"??????");
                toast(context.getString(R.string.YuanShiSuccess));
            }
            else if (msg.what == 1) {
                CombinedChart chart = findViewById(R.id.chart2);
                drowplot(chart, molaDateAndNums,"??????");
                PieChart chart2 = findViewById(R.id.piechart2);
                drowPiePlot(chart2, molaDateAndNums,"??????");
                toast(context.getString(R.string.MolaSuccess));
            }
        }
    };

    //region ?????????????????????????????????
    private final int count = 30;
    protected Typeface tfgenshin;
    ArrayList<ArrayList> yuanshiDateAndNums = new ArrayList<>();
    ArrayList<ArrayList> molaDateAndNums = new ArrayList<>();
    */
/**
     * ??????????????????
     * @param chart
     * @param DateAndNums
     * @param str
     *//*

    private void drowplot(CombinedChart chart, ArrayList<ArrayList> DateAndNums, String str){

        chart.getDescription().setEnabled(false);

        chart.setBackgroundColor(getResources().getColor(R.color.background4));
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);
        chart.setExtraOffsets(5, 0, 0, 0);//????????????????????????

        // draw bars behind lines
        chart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        Legend l = chart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawLabels(false);        //????????????Y?????????

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setTypeface(tfgenshin);
        leftAxis.setTextSize(3f);            //??????Y?????????????????????
        leftAxis.setDrawAxisLine(true);            //??????????????????
        //leftAxis.setSpaceTop(5f);//???????????????????????????????????????????????????????????????????????????????????????

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);//??????x???????????????
        xAxis.setGranularity(1f);


        if (count > DateAndNums.size()-1){
            xAxis.setLabelCount(count/2);  //??????X??????????????????
        }else {
            xAxis.setLabelCount((DateAndNums.get(0).size()-1)/2);  //??????X??????????????????
        }
        xAxis.setDrawAxisLine(true);//??????????????????
        xAxis.setAvoidFirstLastClipping(true);//???????????????????????????????????????????????????????????????????????????????????????
        xAxis.setTypeface(tfgenshin);
        //xAxis.setValueFormatter();

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                //System.out.println(molaDateAndNums.get(0).get((int) value));
                return String.valueOf(DateAndNums.get(0).get((int) value));
            }
        });

        CombinedData data = new CombinedData();

        data.setData(generateLineData(DateAndNums, str));
        data.setData(generateBarData(DateAndNums, str));

        data.setValueTypeface(tfgenshin);

        xAxis.setAxisMaximum(data.getXMax() + 0.5f);//???????????????

        chart.setData(data);
        chart.invalidate();
    }

    */
/**
     * ?????????
     * @param chart
     * @param DateAndNums
     * @param str
     *//*

    private void drowPiePlot(PieChart chart, ArrayList<ArrayList> DateAndNums, String str){
        chart.setUsePercentValues(true);//??????????????????????????????
        chart.setBackgroundColor(getResources().getColor(R.color.background4));

        //chart.setExtraOffsets(5, 10, 5, 5);//????????????????????????
        chart.setExtraOffsets(0, 0, 0, 0);//????????????????????????

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfgenshin);//?????????????????????
        chart.setCenterText(generateCenterSpannableText(str));//?????????????????????

        chart.setDrawHoleEnabled(true);//??????????????????
        chart.setHoleColor(getResources().getColor(R.color.background2));//?????????????????????

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);//?????????????????????

        chart.setHoleRadius(55f);//????????????????????????
        chart.setTransparentCircleRadius(58f);//??????????????????????????????

        chart.setDrawCenterText(true);//???????????????????????????

        chart.setRotationAngle(270);//??????????????????????????????

        //??????
        chart.setRotationEnabled(true); //??????????????????
        chart.setHighlightPerTapEnabled(true);//????????????????????????

        */
/**
         // chart.setUnit(" ???");
         // chart.setDrawUnitsInChart(true);
         chart.setOnChartValueSelectedListener(this);

         seekBarX.setProgress(4);
         seekBarY.setProgress(10);
         chart.animateY(1400, Easing.EaseInOutQuad);
         // chart.spin(2000, 0, 360);
         *//*


        Legend l = chart.getLegend();
        l.setEnabled(false);//???????????????
        */
/**
         l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//???????????????
         l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//???????????????
         l.setOrientation(Legend.LegendOrientation.VERTICAL);//???????????????
         l.setDrawInside(false);//?????????????????????
         l.setXEntrySpace(7f);//??????????????????
         l.setYEntrySpace(0f);//??????????????????
         l.setYOffset(0f);
         *//*



        chart.getDescription().setEnabled(false);//??????

        chart.setDrawEntryLabels(true);//???????????????????????????

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);//?????????????????????
        chart.setEntryLabelTypeface(tfgenshin);//?????????????????????
        chart.setEntryLabelTextSize(12f);//?????????????????????

        //setData(4, 10);
        setData(chart, DateAndNums, str);//??????

    }

    */
/**
     * ??????????????????
     * @param chart
     * @param DateAndNums
     * @param str
     *//*

    private void setData(PieChart chart, ArrayList<ArrayList> DateAndNums, String str) {
        //????????????
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        int totel = 0;
        for (int index = 0; index < DateAndNums.get(2).size(); index++) {
            int num = (int) DateAndNums.get(3).get(index);
            String label = (String) DateAndNums.get(2).get(index);
            entries.add(new PieEntry(num, label));
            totel = totel + num;
        }

        PieDataSet dataSet = new PieDataSet(entries, str + "??????????????????");

        dataSet.setDrawIcons(false);//??????????????????
        dataSet.setIconsOffset(new MPPointF(0, 40));//???????????????

        dataSet.setSliceSpace(3f);//?????????????????????
        dataSet.setSelectionShift(5f);//???????????????

        dataSet.setValueLinePart1OffsetPercentage(50.f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setUsingSliceColorAsValueLineColor(true);

        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);//????????????

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);//????????????
        data.setValueTextColor(Color.BLACK);//????????????
        data.setValueTypeface(tfgenshin);//??????

        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    */
/**
     * ??????????????????
     * @param str
     * @return
     *//*

    private SpannableString generateCenterSpannableText(String str) {
        SpannableString s = new SpannableString(str + "??????????????????\nmaked by ??????????????????");
        s.setSpan(new RelativeSizeSpan(1.8f), 0, 3, 0);
        s.setSpan(new RelativeSizeSpan(1.7f), 3, 8, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 8, s.length() - 7, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length() - 7, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 8, s.length() - 7, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 6, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 6, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.rgb(255,215,0)), 0, 2, 0);
        return s;
    }

    */
/**
     * ????????????
     * @param DateAndNums
     * @param str
     * @return
     *//*

    private LineData generateLineData(ArrayList<ArrayList> DateAndNums, String str) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        int totel = 0;
        int count2 = 0;
        if (count > DateAndNums.get(0).size()-1){
            count2 = DateAndNums.get(0).size()-1;
        }else {
            count2 = count;
        }
        for (int index = 0; index < count2; index++) {
            int num = (int) DateAndNums.get(1).get(index);
            entries.add(new Entry(index + 0.5f, num));
            totel = totel + num;
        }

        LineDataSet set = new LineDataSet(entries, "???30???" + str + "??????(???" + totel + ")");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        //set.setCircleColor(Color.rgb(244, 117, 117));
        set.setCircleColor(Color.rgb(240, 238, 70));
        //set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(4.5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //set.setDrawValues(true);
        //set.setValueTextSize(10f);
        //set.setValueTextColor(Color.rgb(240, 238, 70));
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setDrawValues(false);


        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return d;
    }

    */
/**
     * ????????????
     * @param DateAndNums
     * @param str
     * @return
     *//*

    private BarData generateBarData(ArrayList<ArrayList> DateAndNums, String str) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        int totel = 0;
        int count2 = 0;
        if (count > DateAndNums.get(0).size()-1){
            count2 = DateAndNums.get(0).size()-1;
        }else {
            count2 = count;
        }
        for (int index = 0; index < count2; index++) {
            int num = (int) DateAndNums.get(1).get(index);
            entries.add(new BarEntry(index + 0.5f, num));
            totel = totel + num;
        }

        BarDataSet set1 = new BarDataSet(entries, "");


        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(0, 0, 0));
        set1.setValueTextSize(5f);

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);


        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        set1.setHighLightAlpha(255);

        float barWidth = 0.9f; // x2 dataset

        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);
        return d;
    }

    */
/**
     * ????????????????????????
     *//*

    private void getyuanshi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                yuanshiDateAndNums = DateNums.getDateAndNums(1,cookie,uid);
                //????????????????????????
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }).start();
    }

    */
/**
     * ????????????????????????
     *//*

    private void getmola(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                molaDateAndNums = DateNums.getDateAndNums(2,cookie,uid);
                //System.out.println(molaDateAndNums);
                //????????????????????????
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }
    //endregion

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}*/
