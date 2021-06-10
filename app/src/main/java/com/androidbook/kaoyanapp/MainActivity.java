package com.androidbook.kaoyanapp;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidbook.kaoyanapp.activity.BaseActivity;
import com.androidbook.kaoyanapp.activity.EditTextActivity;
import com.androidbook.kaoyanapp.activity.HomeActivity;
import com.androidbook.kaoyanapp.activity.ListViewMultiChartActivity;
import com.androidbook.kaoyanapp.activity.LoginActivity;
import com.androidbook.kaoyanapp.activity.ManagerCenterActivity;

public class MainActivity extends BaseActivity {

    private Button btnLogin;

    private Button btnRegister;


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {
        //navigateTo(HomeActivity.class);
//        navigateTo(ManagerCenterActivity.class);
//        navigateTo(EditTextActivity.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigateTo(ListViewMultiChartActivity.class);
             //  navigateTo(HomeActivity.class);
                navigateTo(LoginActivity.class);
            }
        });

    }


}