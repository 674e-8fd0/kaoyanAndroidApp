package com.androidbook.kaoyanapp.fragment;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.adapter.VideoAdapter;

import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.androidbook.kaoyanapp.entity.ArtittleReponse;
import com.androidbook.kaoyanapp.entity.VideoEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class VideoFragment extends Fragment {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;


    private String tittle;

    public static VideoFragment newInstance(String tittle){
        VideoFragment fragment =new VideoFragment();
        fragment.tittle=tittle;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_video,container,false);
        recyclerView=v.findViewById(R.id.videoRecyclerView);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
       getArticleList();

    }

    private void getArticleList(){
        Gson gson= new Gson();
        Api.config("/artitle/getAllArtitle",null).getRequest(getActivity(), new TtitCallback() {
            @Override
            public void onSuccess(String res) {

                List<VideoEntity> datas=new ArrayList<>();
                for(int i=0;i<6;i++){
                    VideoEntity ve=new VideoEntity();
                    ve.setVtitle("93");
                    ve.setCategoryName("94");
                    datas.add(ve);
                }
                System.out.println(97107);

                List<ArtitleBean> artitleBeans = gson.fromJson(res, new TypeToken<List<ArtitleBean>>(){}.getType()); // 参数二：需要指定类型，类型来决定解析的集合
                System.out.println("_100");
                System.out.println(artitleBeans.size());
                videoAdapter = new VideoAdapter(getActivity(),datas,artitleBeans);
                (getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(videoAdapter);
                    }
            });
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}