package com.androidbook.kaoyanapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.adapter.HomeAdapter;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.ApiConfig;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles={"数学","英语","政治","专业课"};
    //private final String[] mTitles={"数学"};
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    private String title;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String tittle) {
        HomeFragment fragment = new HomeFragment();
        fragment.title=tittle;
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        viewPager = mRootView.findViewById(R.id.fixedViewPager);
        slidingTabLayout = mRootView.findViewById(R.id.slidingTabLayout);
    }

    @Override
    protected void initData() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home,container,false);
        viewPager=v.findViewById(R.id.fixedViewPager);
        slidingTabLayout=v.findViewById(R.id.slidingTabLayout);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        for(String str:mTitles){
           // mFragments.add(VideoFragment.newInstance(str));
        }
        mFragments.add(MathFragment.newInstance(mTitles[0]));
        mFragments.add(EnglishFragment.newInstance(mTitles[1]));
        mFragments.add(PoliticsFragment.newInstance(mTitles[2]));
        mFragments.add(MajorFragment.newInstance(mTitles[3]));
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.setAdapter(new HomeAdapter(getFragmentManager(),mTitles,mFragments));
        slidingTabLayout.setViewPager(viewPager);

    }
}