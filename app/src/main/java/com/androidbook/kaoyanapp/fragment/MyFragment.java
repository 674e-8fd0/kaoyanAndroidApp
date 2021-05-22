package com.androidbook.kaoyanapp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.activity.LoginActivity;
import com.androidbook.kaoyanapp.activity.MyCollectActivity;

import butterknife.BindView;
import butterknife.OnClick;
import skin.support.SkinCompatManager;


public class MyFragment extends BaseFragment {
    private String tittle;



    public MyFragment() {
    }

    public static MyFragment newInstance(String tittle) {
        MyFragment fragment = new MyFragment();
        fragment.tittle=tittle;
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_header, R.id.rl_creat, R.id.setting, R.id.sign_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_header:
                System.out.println("123");
                break;
            case R.id.rl_creat:

                break;
            case R.id.setting:

                break;
            case R.id.rl_signout:
                removeByKey("token");
                navigateToWithFlag(LoginActivity.class,
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }
    }
}