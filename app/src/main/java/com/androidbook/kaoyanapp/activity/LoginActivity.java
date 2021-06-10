package com.androidbook.kaoyanapp.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.ApiConfig;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {

    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                login(account, pwd);
            }
        });
    }

    private void login(String account, String pwd) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        else if (StringUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }else{
            navigateToWithFlag(HomeActivity.class,
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }

    }

//    private void login(String account, String pwd) {
//        if (StringUtils.isEmpty(account)) {
//            showToast("请输入账号");
//            return;
//        }
//        if (StringUtils.isEmpty(pwd)) {
//            showToast("请输入密码");
//            return;
//        }
//
//        //第一步创建OKHttpClient
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        Map m = new HashMap();
//        m.put("mobile", account);
//        m.put("password", pwd);
//        JSONObject jsonObject = new JSONObject(m);
//        String jsonStr = jsonObject.toString();
//        RequestBody requestBodyJson =
//                RequestBody.create(MediaType.parse("application/json;charset=utf-8")
//                        , jsonStr);
//        //第三步创建Rquest
//        Request request = new Request.Builder()
//                .url(AppConfig.BASE_URl + "/app/login")
//                .addHeader("contentType", "application/json;charset=UTF-8")
//                .post(requestBodyJson)
//                .build();
//        //第四步创建call回调对象
//        final Call call = client.newCall(request);
//        //第五步发起请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("onFailure", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast(result);
//                    }
//                });
//            }
//        });
//    }
}