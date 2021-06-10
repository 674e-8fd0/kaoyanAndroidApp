package com.androidbook.kaoyanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.activity.DispayArt;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.androidbook.kaoyanapp.listviewitems.LeftSlideView;
import com.androidbook.kaoyanapp.listviewitems.LeftSliderDoubleView;
import com.androidbook.kaoyanapp.util.DPIUtil;

import java.util.HashMap;
import java.util.List;

public class ManagerCenterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ArtitleBean> artittleReponses;


    private RecyclerView mRecyclerView;

    private LeftSliderDoubleView mLeftSlideView;

    public ManagerCenterAdapter(Context context,RecyclerView recyclerView, List<ArtitleBean> artittleReponse) {
        System.out.println("ItemAdapter 27");
        this.mContext = context;
        this.artittleReponses=artittleReponse;
        this.mRecyclerView=recyclerView;
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int position=viewType;

        final LeftSliderDoubleView leftSlideView = new LeftSliderDoubleView(mContext);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT
//                , DPIUtil.dip2px(20000.f));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        leftSlideView.setLayoutParams(params);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View contentView = inflater.inflate(R.layout.layout_manart_content, null);
        View menuView = inflater.inflate(R.layout.layout_issue_menu, null);
        View delmenuView = inflater.inflate(R.layout.layout_del_menu, null);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击发布按钮" , Toast.LENGTH_SHORT).show();
                HashMap<String, Object> params = new HashMap<>();
                params.put("tittle", artittleReponses.get(position).getTittle());
                if(artittleReponses.get(position).getIssue().equals("0")){
                    params.put("issue", "1");
                }else
                {
                    params.put("issue", "0");
                }

                //deleteArt
                Api.config("/mananger/updateIssue", params).postRequest(mContext, new TtitCallback() {
                    @Override
                    public void onSuccess(String res) {
                        String response = res.toString();

                        if (response.equals("1") ) {
                            Looper.prepare();
                            Toast.makeText(mContext, "成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        } else {
                            Looper.prepare();
                            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Looper.prepare();
                        Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                });

            }
        });

        delmenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击删除按钮" , Toast.LENGTH_SHORT).show();
                HashMap<String, Object> params = new HashMap<>();
                params.put("tittle", artittleReponses.get(position).getTittle());
                //deleteArt
                Api.config("/mananger/deleteArt", params).postRequest(mContext, new TtitCallback() {
                    @Override
                    public void onSuccess(String res) {
                        String response = res.toString();

                        if (response.equals("1") ) {
                            Looper.prepare();
                            Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        } else {
                            Looper.prepare();
                            Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Looper.prepare();
                        Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                });
            }
        });
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击内容区域"+artittleReponses.get(position).getTittle(), Toast.LENGTH_SHORT).show();
                System.out.println(position);
            }
        });

        leftSlideView.addContentView(contentView);
        leftSlideView.addMenuView1(menuView);
        leftSlideView.addMenuView2(delmenuView);
        leftSlideView.setRecyclerView(mRecyclerView);
        leftSlideView.setStatusChangeLister(new LeftSliderDoubleView.OnDelViewStatusChangeLister() {
            @Override
            public void onStatusChange(boolean show) {
                if (show) {
                    // 如果编辑菜单在显示
                    mLeftSlideView = leftSlideView;
                }
            }
        });

        ManagerCenterVH item = new ManagerCenterVH(leftSlideView);
        return item;



    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ManagerCenterVH item = (ManagerCenterVH )holder;
        System.out.println("ItemAdpater 45");
        System.out.println(position);
        ArtitleBean artitleBean =artittleReponses.get(position);
        System.out.println("ItemAdapter 48");
        System.out.println(artitleBean.getTittle());
        item.title.setText(artitleBean.getTittle());
        String issue=artitleBean.getIssue();

        item.dates.setText(artitleBean.getTime());

        if(artitleBean.getText().length()>=20){
            item.text.setText(artitleBean.getText().substring(0,20).replaceAll(" +",""));
        }else{
            item.text.setText(artitleBean.getText().replaceAll(" +",""));
        }
        if (issue.equals("1")){
            item.mdisplay.setText("下架");
            item.mdisplay.setBackgroundColor(Color.parseColor("#FEAF0B"));
        }



    }

    @Override
    public int getItemCount() {
        int count=0;
        if (artittleReponses != null && artittleReponses.size() > 0) {
            return artittleReponses.size();
        } else {
            return 0;
        }
    }


    /**
     * 还原itemView
     * @param point
     */
    public void restoreItemView(Point point) {
        if (mLeftSlideView != null) {

            int[] pos = new int[2];


            mLeftSlideView.getLocationInWindow(pos);

            int width = mLeftSlideView.getWidth();
            int height = mLeftSlideView.getHeight();

            // 触摸点在view的区域内，那么直接返回
            if (point.x >= pos[0] && point.y >= pos[1]
                    && point.x <= pos[0] + width && point.y <= pos[1] + height) {

                return;
            }

            mLeftSlideView.resetDelStatus();
        }
    }
    public static class ManagerCenterVH extends RecyclerView.ViewHolder {
        private TextView title,dates,text,mdisplay;
        public  ManagerCenterVH(@NonNull View view) {
            super(view);
            title=view.findViewById(R.id.title);
            dates=view.findViewById(R.id.dates);
            text=view.findViewById(R.id.text);
            mdisplay=view.findViewById(R.id.display);



            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }
    }
}
