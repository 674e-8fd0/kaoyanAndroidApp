package com.androidbook.kaoyanapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.androidbook.kaoyanapp.util.EmptyUtil;

public class DispayArt  extends BaseActivity {
    private TextView author,title,times,artitle;
    @Override
    protected int initLayout() {
        return R.layout.display_artile;
    }

    @Override
    protected void initView() {
    author=findViewById(R.id.author);
    title=findViewById(R.id.title);
    times=findViewById(R.id.times);
    artitle=findViewById(R.id.artContent);
        artitle.setMovementMethod(ScrollingMovementMethod.getInstance());
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        //youJavaBean= (YouJavaBean)bundle.get("youJavaBean");
        ArtitleBean artitleBean= (ArtitleBean) intent.getSerializableExtra("art");
//        ArtitleBean artitleBean=new ArtitleBean();
//        artitleBean.setText(intent.getStringExtra("text"));
//        artitleBean.setTime(intent.getStringExtra("time"));
//        artitleBean.setUser(intent.getStringExtra("user"));
//        artitleBean.setTittle(intent.getStringExtra("tittle"));
        System.out.println("34"+intent.getStringExtra("tittle"));
        System.out.println("35"+artitleBean.getTittle());


        if (!EmptyUtil.isEmpty(artitleBean)){
            System.out.println("DispayArt 28");
            author.setText(artitleBean.getUser());
            title.setText(artitleBean.getTittle());
            times.setText(artitleBean.getTime());
            artitle.setText(artitleBean.getText());
        }
        else {
            System.out.println("DispayArt 35");
            System.out.println("传送的JavaBean对象为空");
        }
        System.out.println(intent.getStringExtra("1"));

    }

    @Override
    protected void initData() {


    }
}
