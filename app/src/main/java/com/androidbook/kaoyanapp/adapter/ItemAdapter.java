package com.androidbook.kaoyanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidbook.kaoyanapp.R;

import com.androidbook.kaoyanapp.activity.DispayArt;
import com.androidbook.kaoyanapp.entity.ArtitleBean;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<ArtitleBean> artittleReponses;

    public ItemAdapter(Context context, List<ArtitleBean> artittleReponse) {
        System.out.println("ItemAdapter 27");
        this.mContext = context;
        this.artittleReponses=artittleReponse;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_artitles_layout, parent, false);
        ItmeHolder itemAdapter = new ItmeHolder(view);
        System.out.println("ItemAdapter 38");
        return itemAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItmeHolder item = (ItmeHolder) holder;
        System.out.println("ItemAdpater 45");
        System.out.println(position);
        ArtitleBean artitleBean =artittleReponses.get(position);
            System.out.println("ItemAdapter 48");
            System.out.println(artitleBean.getTittle());
            item.title.setText(artitleBean.getTittle());
            item.author.setText(artitleBean.getUser());
            item.dz.setText("70");
            item.comment.setText("74");
            item.collect.setText("75");
            item.text.setText("73");
            if(artitleBean.getText().length()>=50){
                item.text.setText(artitleBean.getText().substring(0,50).replaceAll(" +",""));
            }else{
                item.text.setText(artitleBean.getText().replaceAll(" +",""));
            }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, position+"我被点击了", Toast.LENGTH_SHORT).show();
                System.out.println(position);
            ArtitleBean artitleBean1 = artittleReponses.get(position);
                System.out.println(artittleReponses.get(position).getTittle());


                Intent in = new Intent(mContext, DispayArt.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("art", artitleBean1);
                in.putExtras(bundle);
                in.putExtra("1","3");

                mContext.startActivity(in);


            }

        });

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

    public class ItmeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title,author,comment,collect,text,dz;


        public ItmeHolder(@NonNull View view) {
            super(view);
        title=view.findViewById(R.id.title);
        author=view.findViewById(R.id.author);
        comment=view.findViewById(R.id.comment);
        collect = view.findViewById(R.id.collect);
        text=view.findViewById(R.id.text);
        dz=view.findViewById(R.id.dz);



            //通过tag将ViewHolder和itemView绑定
            view.setTag(this);
        }


        @Override
        public void onClick(View v) {
            System.out.println("VideoAdapter-click");
        }
    }




}
