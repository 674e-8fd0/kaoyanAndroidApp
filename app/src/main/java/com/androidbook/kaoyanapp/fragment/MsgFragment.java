package com.androidbook.kaoyanapp.fragment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.androidbook.kaoyanapp.R;
import com.androidbook.kaoyanapp.listener.OnDelViewStatusChangeLister;
import com.androidbook.kaoyanapp.listviewitems.CustomLinearLayout;
import com.androidbook.kaoyanapp.listviewitems.LeftSlideView;
import com.androidbook.kaoyanapp.util.DPIUtil;

public class MsgFragment  extends Fragment {

    private String tittle;
    private RecyclerView mRecyclerView;
    private MsgAdapter mMsgAdapter;



    public MsgFragment() {
        // Required empty public constructor
    }


    public static  MsgFragment newInstance(String tittle) {
        MsgFragment fragment =new MsgFragment();
        fragment.tittle=tittle;
        return fragment;
    }

    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 初始化转换工具
        DPIUtil.setDensity(getResources().getDisplayMetrics().density);
        View v= inflater.inflate(R.layout.fragment_msg,container,false);
        mRecyclerView = v.findViewById(R.id.recyclerView);
        View root = v.findViewById(R.id.root);
        if (root instanceof CustomLinearLayout) {
            CustomLinearLayout cll = (CustomLinearLayout) root;
            cll.setOnTouchListener(new CustomLinearLayout.OnTouchListener() {
                @Override
                public void doTouch(Point point) {
                    if (mMsgAdapter != null) {
                        mMsgAdapter.restoreItemView(point);
                    }
                }
            });
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mMsgAdapter = new MsgAdapter(getContext(), mRecyclerView);
        mRecyclerView.setAdapter(mMsgAdapter);


        return v;
    }

    public static class MsgAdapter extends RecyclerView.Adapter {

        private Context mContext;

        private RecyclerView mRecyclerView;

        private LeftSlideView mLeftSlideView;

        public MsgAdapter(Context context, RecyclerView recyclerView) {
            this.mContext = context;
            this.mRecyclerView = recyclerView;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            final LeftSlideView leftSlideView = new LeftSlideView(mContext);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                    , DPIUtil.dip2px(100.f));// width,  height
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT
                    ,ViewGroup.LayoutParams.WRAP_CONTENT );// width,  height
            leftSlideView.setLayoutParams(params);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.layout_content, null);
            View menuView = inflater.inflate(R.layout.layout_menu, null);

            menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "点击删除按钮", Toast.LENGTH_SHORT).show();
                }
            });

            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "点击进入查看对话", Toast.LENGTH_SHORT).show();
                }
            });

            leftSlideView.addContentView(contentView);
            leftSlideView.addMenuView(menuView);


            leftSlideView.setRecyclerView(mRecyclerView);
            leftSlideView.setStatusChangeLister(new LeftSlideView.OnDelViewStatusChangeLister() {
                @Override
                public void onStatusChange(boolean show) {
                    if (show) {
                        // 如果编辑菜单在显示
                        mLeftSlideView = leftSlideView;
                    }
                }
            });


            return new MyVH(leftSlideView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
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
    }

    public static class MyVH extends RecyclerView.ViewHolder {

        public MyVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}