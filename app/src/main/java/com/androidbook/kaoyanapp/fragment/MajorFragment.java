package com.androidbook.kaoyanapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.adapter.ItemAdapter;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MajorFragment  extends Fragment{
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private ItemAdapter itemAdapter;
    private String tittle;

    public static MajorFragment newInstance(String tittle){
        MajorFragment fragment =new MajorFragment();
        fragment.tittle=tittle;
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_items,container,false);
        recyclerView=v.findViewById(R.id.itemRecycle);
        refreshLayout = v.findViewById(R.id.refreshLayout);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        getArticleList();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getContext(), "专业课文章刷新", Toast.LENGTH_SHORT).show();
                getArticleList();
                refreshLayout.finishRefresh(true);
            }
        });

    }
    private void getArticleList(){
        Gson gson= new Gson();
        Api.config("/artitle/getAllArtitle",null).getRequest(getActivity(), new TtitCallback() {
            @Override
            public void onSuccess(String res) {


                List<ArtitleBean> artitleBeans = gson.fromJson(res, new TypeToken<List<ArtitleBean>>(){}.getType()); // 参数二：需要指定类型，类型来决定解析的集合
                List<ArtitleBean> _artitleBeans = new ArrayList<>();
                for (ArtitleBean artitleBean : artitleBeans){
                    if(artitleBean.getCategory().equals("major"))
                        _artitleBeans.add(artitleBean);
                }

                itemAdapter = new ItemAdapter(getActivity(),_artitleBeans);
                (getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("PoliticsFragment 69");
                        recyclerView.setAdapter(itemAdapter);
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
