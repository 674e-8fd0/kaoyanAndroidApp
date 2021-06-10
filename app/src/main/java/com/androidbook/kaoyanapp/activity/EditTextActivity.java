package com.androidbook.kaoyanapp.activity;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.ApiConfig;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class EditTextActivity extends BaseActivity implements View.OnClickListener  {

    private EditText contentTitle,contentDetail;
    private ImageView backto;
    private LinearLayout submit;
    private String category;

    @Override
    protected int initLayout() {
        return R.layout.layout_dispaly_art;
    }

    @Override
    protected void initView() {

        contentTitle=findViewById(R.id.content_title);
        contentDetail=findViewById(R.id.content_detail);
        backto=findViewById(R.id.backto);
        submit=findViewById(R.id.submit);
        backto.setOnClickListener(this);
        submit.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backto:
                EditTextActivity.this.finish();
                break;
            case R.id.submit:
                showToast("提交");
                showBottomDialog();
                break;

        }
    }

    private void showBottomDialog(){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this,R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(this,R.layout.dialog_custom_layout,null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        dialog.findViewById(R.id.sub_math).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category="math";
                setSubmit();
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.sub_eng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category="english";
                setSubmit();
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.sub_pol).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category="politics";
                setSubmit();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.sub_major).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category="major";
                setSubmit();
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });


    }
    private void setSubmit(){
        System.out.println("127");
        showToast("选择的是类别是:"+category);
        String title=contentTitle.getText().toString().trim();
        String content=contentDetail.getText().toString().trim();
        if(StringUtils.isEmpty(title)) {
            showToast("标题不能为空！");
            return;
        }
        if(StringUtils.isEmpty(content)) {
            showToast("文章内容不能为空！！");
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("tittle", title);
        params.put("text", content);
        params.put("category", category); //先默认1号类别
        params.put("issue", "0");
        params.put("weight","99");
        System.out.println("144");
        Api.config("/mananger/SaveArticle", params).postRequest(mContext, new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                String response = res.toString();

                if (response.equals("1") ) {
                    showToastSync(response);

                } else {
                    showToastSync("添加失败1");
                }

            }

            @Override
            public void onFailure(Exception e) {
                showToastSync("添加失败2");
            }
        });

    }


}
