package com.androidbook.kaoyanapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.api.Api;
import com.androidbook.kaoyanapp.api.ApiConfig;
import com.androidbook.kaoyanapp.api.TtitCallback;
import com.androidbook.kaoyanapp.entity.ArtitleBean;
import com.androidbook.kaoyanapp.entity.ArtittleReponse;
import com.androidbook.kaoyanapp.entity.BaseResponse;
import com.androidbook.kaoyanapp.entity.VideoEntity;
import com.androidbook.kaoyanapp.listener.OnItemChildClickListener;
import com.androidbook.kaoyanapp.listener.OnItemClickListener;
import com.androidbook.kaoyanapp.view.CircleTransform;
import com.dueeeke.videocontroller.component.PrepareView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datas;

    private List<ArtitleBean> artittleReponses;

    public void setDatas(List<VideoEntity> datas) {
        this.datas = datas;
    }


    public VideoAdapter(Context context, List<VideoEntity> datas, List<ArtitleBean> artittleReponse) {
        this.mContext = context;
        this.datas = datas;
        this.artittleReponses=artittleReponse;
        System.out.println("_60");
    }
    public VideoAdapter(Context context, List<VideoEntity> datas) {
        this.mContext = context;
        this.datas = datas;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        System.out.println("76");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datas.get(position);
       // List<ArtitleBean> artitleBeans=artittleReponses.getArtitleBeans();
        ArtitleBean artitleBean =artittleReponses.get(position);
        vh.tvTitle.setText(artitleBean.getTittle());
        vh.tvAuthor.setText(artitleBean.getUser());
        vh.tvDz.setText("70");
        vh.tvComment.setText("74");
        vh.tvCollect.setText("75");
        vh.text.setText("73");
        if(artitleBean.getText().length()>=50){
            vh.text.setText(artitleBean.getText().substring(0,50).replaceAll(" +",""));
        }else{
            vh.text.setText(artitleBean.getText().replaceAll(" +",""));
        }

    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.size() > 0) {
            return datas.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvDz;
        private TextView tvComment;
        private TextView tvCollect;
        private TextView text;
        private ImageView imgHeader;
        private ImageView imgCollect;
        private ImageView imgDizan;
        public ImageView mThumb;
        public PrepareView mPrepareView;
        public FrameLayout mPlayerContainer;
        public int mPosition;
        private boolean flagCollect;
        private boolean flagLike;

        public ViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.title);
            tvAuthor = view.findViewById(R.id.author);
            tvDz = view.findViewById(R.id.dz);
            tvComment = view.findViewById(R.id.comment);
            tvCollect = view.findViewById(R.id.collect);
            imgHeader = view.findViewById(R.id.img_header);
            imgCollect = view.findViewById(R.id.img_collect);
            imgDizan = view.findViewById(R.id.img_like);
            mPlayerContainer = view.findViewById(R.id.player_container);
            mPrepareView = view.findViewById(R.id.prepare_view);
            text=view.findViewById(R.id.text);




            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }


        @Override
        public void onClick(View v) {
            System.out.println("VideoAdapter-click");
        }
    }
}