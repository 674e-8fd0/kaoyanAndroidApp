package com.androidbook.kaoyanapp.activity;

import android.graphics.Point;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.adapter.ItemAdapter;
import com.androidbook.kaoyanapp.adapter.ManagerCenterAdapter;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.androidbook.kaoyanapp.listviewitems.CustomLinearLayout;
import com.androidbook.kaoyanapp.util.DPIUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerCenterActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RefreshLayout refreshLayout;
    private ManagerCenterAdapter managerCenterAdapter;
    private ImageView backto;
    private LinearLayout publishArt;


    @Override
    protected int initLayout() {
        return R.layout.layout_management_center;
    }

    @Override
    protected void initView() {
    mRecyclerView=findViewById(R.id.recyclerView);
    backto=findViewById(R.id.backto);
        backto.setOnClickListener(this);
        publishArt=findViewById(R.id.publishArt);
        publishArt.setOnClickListener(this);
        // 设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DPIUtil.setDensity(getResources().getDisplayMetrics().density);

        View root = findViewById(R.id.root);
        if (root instanceof CustomLinearLayout) {
            CustomLinearLayout cll = (CustomLinearLayout) root;
            cll.setOnTouchListener(new CustomLinearLayout.OnTouchListener() {
                @Override
                public void doTouch(Point point) {
                    if (managerCenterAdapter != null) {
                        managerCenterAdapter.restoreItemView(point);
                    }
                }
            });
        }

        mRecyclerView.setLayoutManager(linearLayoutManager);
        getArticleList();


    }

    @Override
    protected void initData() {

    }


    private void getArticleList(){
        Gson gson= new Gson();
        Api.config("/mananger/getAllArt",null).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(String res) {


                List<ArtitleBean> artitleBeans = gson.fromJson(res, new TypeToken<List<ArtitleBean>>(){}.getType()); // 参数二：需要指定类型，类型来决定解析的集合
                List<ArtitleBean> _artitleBeans = new ArrayList<>();
                for (ArtitleBean artitleBean : artitleBeans){
                        _artitleBeans.add(artitleBean);
                }

                managerCenterAdapter = new ManagerCenterAdapter(ManagerCenterActivity.this, mRecyclerView,_artitleBeans);
                (ManagerCenterActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ManagerCenterActivity 68");
//                        mRecyclerView.setAdapter(itemAdapter);
                        mRecyclerView.setAdapter(managerCenterAdapter);
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backto:
                ManagerCenterActivity.this.finish();
                break;
            case R.id.publishArt:
                navigateTo(EditTextActivity.class);
                break;

        }
    }


}
