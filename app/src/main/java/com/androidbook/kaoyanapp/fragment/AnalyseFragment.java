package com.androidbook.kaoyanapp.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.activity.ListViewMultiChartActivity;
import com.androidbook.kaoyanapp.api.ApiConfig;
import com.androidbook.kaoyanapp.listviewitems.BarChartItem;
import com.androidbook.kaoyanapp.listviewitems.ChartItem;
import com.androidbook.kaoyanapp.listviewitems.LineChartItem;
import com.androidbook.kaoyanapp.listviewitems.PieChartItem;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.scwang.smartrefresh.layout.api.RefreshLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AnalyseFragment extends BaseFragment  implements ActivityCompat.OnRequestPermissionsResultCallback{

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;//创建一个数组适配器
    private List<String> schoolList = new ArrayList<String>();

    private int[] enrollments={1023,899,1134,1567,1200,1023,899,};


    private LinearLayoutManager linearLayoutManager;
    private int pageNum = 1;

    private Context mContext;
    protected Typeface tfRegular;
    protected Typeface tfLight;
    private String title;

    public AnalyseFragment() {
        super();
    }

    public static AnalyseFragment newInstance(String tittle) {
        AnalyseFragment fragment = new AnalyseFragment();
        fragment.title=tittle;


        return fragment;
    }

    @Override
    protected int initLayout() {

        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {

        AssetManager mAssetManger = getActivity().getAssets();
        tfRegular = Typeface.createFromAsset(mAssetManger, "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(mAssetManger, "OpenSans-Light.ttf");
        ListView lv = mRootView.findViewById(R.id.listView1);


        HashMap<String, int[]> schoolInfos = new HashMap<>();
        //向这个HashMap里面添加元素

        schoolInfos.put("二工大", new int[]{1023,899, 1134, 1567, 1200, 1023,1234});
        schoolInfos.put("上海大学", new int[]{1134,899,899,1134,1200,1023,899});
        schoolInfos.put("复旦大学", new int[]{899,1134,1134,1567,1023,899,1200});

        Iterator entries =schoolInfos.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String)entry.getKey();
            schoolList.add(key);
        }


        ArrayList<ChartItem> list = new ArrayList<>();
        list.add(new LineChartItem(generateDataLine(schoolInfos.get("二工大")), getActivity().getApplicationContext()));
        list.add(new BarChartItem(generateDataBar(schoolInfos.get("二工大")), getActivity().getApplicationContext()));
        list.add(new PieChartItem(generateDataPie(), getActivity().getApplicationContext()));
        ChartDataAdapter cda = new ChartDataAdapter(getActivity().getApplicationContext(), list);
        lv.setAdapter(cda);

        mySpinner = (Spinner) mRootView.findViewById(R.id.spinner1);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,schoolList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                // 将所选mySpinner 的值带入myTextView 中
                String str=adapter.getItem(arg2);
                showToast("您选择的是：" + str);
                ArrayList<ChartItem> lists = new ArrayList<>();
                lists.add(new LineChartItem(generateDataLine(schoolInfos.get(str)), getActivity().getApplicationContext()));
                lists.add(new BarChartItem(generateDataBar(schoolInfos.get(str)), getActivity().getApplicationContext()));
                lists.add(new PieChartItem(generateDataPie(), getActivity().getApplicationContext()));
                ChartDataAdapter cda = new ChartDataAdapter(getActivity().getApplicationContext(), lists);
                lv.setAdapter(null);
                lv.setAdapter(cda);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                showToast("Nothing");
            }
        });


    }

    @Override
    protected void initData() {


    }

    private void getNewsList(final boolean isRefresh) {


    }


    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }













    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Line data
     */
    private LineData generateDataLine(int[] datas) {

        ArrayList<Entry> values1 = new ArrayList<>();
//        enrollments=datas;
        for (int i = 0; i < datas.length; i++) {
            int cnt = 2016+i;
            values1.add(new Entry(2015+i, datas[i] ));
        }

        LineDataSet d1 = new LineDataSet(values1, "复试人数 ");
        d1.setLineWidth(3.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < datas.length; i++) {
            int cnt = 2016+i;
            values2.add(new Entry(2015+i, values1.get(i).getY()-530));
        }
        LineDataSet d2 = new LineDataSet(values2, "录取人数");
        d2.setLineWidth(3.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);

        return new LineData(sets);
    }
    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar(int[] data) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        //enrollments=data;
        //设置x,y轴
        for (int i = 0; i < data.length; i++) {
            entries.add(new BarEntry(2015+i, data[i]));
        }

        BarDataSet d = new BarDataSet(entries, "历年录取人数折线柱状图 " );
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Pie data
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "分数在"+String.valueOf(420-i*30)+"及以上的人数"));
        }




        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        return new PieData(d);
    }
}