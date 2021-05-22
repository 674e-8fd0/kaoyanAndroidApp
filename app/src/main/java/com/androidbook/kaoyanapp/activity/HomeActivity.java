package com.androidbook.kaoyanapp.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.adapter.MyPagerAdapter;
import com.androidbook.kaoyanapp.entity.TabEntity;
import com.androidbook.kaoyanapp.fragment.AnalyseFragment;
import com.androidbook.kaoyanapp.fragment.HomeFragment;
import com.androidbook.kaoyanapp.fragment.MsgFragment;
import com.androidbook.kaoyanapp.fragment.MyFragment;
import com.androidbook.kaoyanapp.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

//    "搜索","发布",
    private String[] mTitles = {"首页","分析", "消息", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.home,R.mipmap.anaysis, R.mipmap.msg,
            R.mipmap.my};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected,R.mipmap.anaysis_selected, R.mipmap.msg_selected,
            R.mipmap.my_selected};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
      mFragments.add(HomeFragment.newInstance("1"));
       mFragments.add(AnalyseFragment.newInstance("1"));
        mFragments.add(MsgFragment.newInstance("1"));
        mFragments.add(MyFragment.newInstance("1"));
        //mFragments.add(VideoFragment.newInstance("1"));
        for (int i = 0; i < mTitles.length; i++) {

            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        commonTabLayout.setTabData(mTabEntities);

        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }
}