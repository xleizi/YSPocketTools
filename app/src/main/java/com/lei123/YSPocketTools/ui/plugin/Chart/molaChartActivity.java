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
        cc.ccc();

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
     * 加载本地的用户信息
     *//*

    private void loadUser() {
        SharedPreferences preferences = context.getSharedPreferences("uid", Activity.MODE_PRIVATE);
        uid = preferences.getString("uid", "");
        cookie = preferences.getString("cookies", "");
    }


    //主线程接收数据
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                CombinedChart chart = findViewById(R.id.chart1);
                drowplot(chart,yuanshiDateAndNums,"原石");
                PieChart chart2 = findViewById(R.id.piechart1);
                drowPiePlot(chart2,yuanshiDateAndNums,"原石");
                toast(context.getString(R.string.YuanShiSuccess));
            }
            else if (msg.what == 1) {
                CombinedChart chart = findViewById(R.id.chart2);
                drowplot(chart, molaDateAndNums,"摩拉");
                PieChart chart2 = findViewById(R.id.piechart2);
                drowPiePlot(chart2, molaDateAndNums,"摩拉");
                toast(context.getString(R.string.MolaSuccess));
            }
        }
    };

    //region 制作原石摩拉获取统计图
    private final int count = 30;
    protected Typeface tfgenshin;
    ArrayList<ArrayList> yuanshiDateAndNums = new ArrayList<>();
    ArrayList<ArrayList> molaDateAndNums = new ArrayList<>();
    */
/**
     * 画复合折线图
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
        chart.setExtraOffsets(5, 0, 0, 0);//上下左右隔了多少

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
        rightAxis.setDrawLabels(false);        //是否显示Y轴刻度

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setTypeface(tfgenshin);
        leftAxis.setTextSize(3f);            //设置Y轴刻度字体大小
        leftAxis.setDrawAxisLine(true);            //是否绘制轴线
        //leftAxis.setSpaceTop(5f);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);//设置x轴的最小值
        xAxis.setGranularity(1f);


        if (count > DateAndNums.size()-1){
            xAxis.setLabelCount(count/2);  //设置X轴的显示个数
        }else {
            xAxis.setLabelCount((DateAndNums.get(0).size()-1)/2);  //设置X轴的显示个数
        }
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
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

        xAxis.setAxisMaximum(data.getXMax() + 0.5f);//设置最大值

        chart.setData(data);
        chart.invalidate();
    }

    */
/**
     * 画饼图
     * @param chart
     * @param DateAndNums
     * @param str
     *//*

    private void drowPiePlot(PieChart chart, ArrayList<ArrayList> DateAndNums, String str){
        chart.setUsePercentValues(true);//内部是否按百分比绘制
        chart.setBackgroundColor(getResources().getColor(R.color.background4));

        //chart.setExtraOffsets(5, 10, 5, 5);//上下左右隔了多少
        chart.setExtraOffsets(0, 0, 0, 0);//上下左右隔了多少

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterTextTypeface(tfgenshin);//设置中心的字体
        chart.setCenterText(generateCenterSpannableText(str));//设置中心的字体

        chart.setDrawHoleEnabled(true);//设置中心为空
        chart.setHoleColor(getResources().getColor(R.color.background2));//设置中心为白色

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);//中间的透明程度

        chart.setHoleRadius(55f);//设置中间孔占位置
        chart.setTransparentCircleRadius(58f);//设置中间透明圆占位置

        chart.setDrawCenterText(true);//是否显示中间的文本

        chart.setRotationAngle(270);//设置旋转动画起始位置

        //点击
        chart.setRotationEnabled(true); //是否允许旋转
        chart.setHighlightPerTapEnabled(true);//设置点击之后高亮

        */
/**
         // chart.setUnit(" €");
         // chart.setDrawUnitsInChart(true);
         chart.setOnChartValueSelectedListener(this);

         seekBarX.setProgress(4);
         seekBarY.setProgress(10);
         chart.animateY(1400, Easing.EaseInOutQuad);
         // chart.spin(2000, 0, 360);
         *//*


        Legend l = chart.getLegend();
        l.setEnabled(false);//不显示图例
        */
/**
         l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//标签的上下
         l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//标签的左右
         l.setOrientation(Legend.LegendOrientation.VERTICAL);//标签的方向
         l.setDrawInside(false);//标签是否在里面
         l.setXEntrySpace(7f);//设置水平间距
         l.setYEntrySpace(0f);//设置垂直间距
         l.setYOffset(0f);
         *//*



        chart.getDescription().setEnabled(false);//题目

        chart.setDrawEntryLabels(true);//显示饼图标签的文字

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);//标签文字的颜色
        chart.setEntryLabelTypeface(tfgenshin);//标签文字的字体
        chart.setEntryLabelTextSize(12f);//标签文字的大小

        //setData(4, 10);
        setData(chart, DateAndNums, str);//返回

    }

    */
/**
     * 饼图填充数据
     * @param chart
     * @param DateAndNums
     * @param str
     *//*

    private void setData(PieChart chart, ArrayList<ArrayList> DateAndNums, String str) {
        //数量范围
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

        PieDataSet dataSet = new PieDataSet(entries, str + "获取途径统计");

        dataSet.setDrawIcons(false);//是否显示图标
        dataSet.setIconsOffset(new MPPointF(0, 40));//图标的位置

        dataSet.setSliceSpace(3f);//中间的空隙大小
        dataSet.setSelectionShift(5f);//边缘的距离

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

        dataSet.setColors(colors);//设置颜色

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);//文字大小
        data.setValueTextColor(Color.BLACK);//文字颜色
        data.setValueTypeface(tfgenshin);//字体

        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    */
/**
     * 饼图中心文字
     * @param str
     * @return
     *//*

    private SpannableString generateCenterSpannableText(String str) {
        SpannableString s = new SpannableString(str + "获取途径统计\nmaked by 原神口袋工具");
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
     * 画折线图
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

        LineDataSet set = new LineDataSet(entries, "近30天" + str + "收入(总" + totel + ")");
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
     * 画条形图
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
     * 获取原石展示列表
     *//*

    private void getyuanshi(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                yuanshiDateAndNums = DateNums.getDateAndNums(1,cookie,uid);
                //向主线程发送数据
                Message msg = Message.obtain();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }).start();
    }

    */
/**
     * 获取摩拉展示列表
     *//*

    private void getmola(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                molaDateAndNums = DateNums.getDateAndNums(2,cookie,uid);
                //System.out.println(molaDateAndNums);
                //向主线程发送数据
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
