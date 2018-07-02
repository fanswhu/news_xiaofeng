package com.jayce.xiaofeng_news.ui.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingmouren.layoutmanagergroup.slide.ItemConfig;
import com.dingmouren.layoutmanagergroup.slide.ItemTouchHelperCallback;
import com.dingmouren.layoutmanagergroup.slide.OnSlideListener;
import com.dingmouren.layoutmanagergroup.slide.SlideLayoutManager;
import com.jayce.xiaofeng_news.R;
import com.jayce.xiaofeng_news.entity.GankBean;
import com.jayce.xiaofeng_news.entity.SlideBean;
import com.jayce.xiaofeng_news.service.BaseObserver;
import com.jayce.xiaofeng_news.service.RertrofitFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @BindView(R.id.main_rv)
    RecyclerView rv;
    private List<SlideBean> list = new ArrayList<>();
    MyAdapter adapter = new MyAdapter();

    ItemTouchHelperCallback mItemTouchHelperCallback;

    private  int page = new Random().nextInt(31);
    @Override
    protected void initDatas() {
        request(page);
    }

    private void request(int page) {
        RertrofitFactory.getGankService().getBenefit(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GankBean>() {
                    @Override
                    public void onSuccess(GankBean gankBean) {
                        Log.e("---",gankBean.getResults().get(0).getUrl());
                        for(int i = 0; i<gankBean.getResults().size();i++){
                            GankBean.ResultsBean  resultsBean = gankBean.getResults().get(i);
                            SlideBean bean =new SlideBean(resultsBean.getUrl(),
                                    resultsBean.getWho(),1,resultsBean.getType());
                            list.add(bean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onError(String e) {

                    }
                });
    }

    @Override
    protected void initEvents() {
        rv.setAdapter(adapter);
        mItemTouchHelperCallback = new ItemTouchHelperCallback(rv.getAdapter(), list);
       ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        SlideLayoutManager mSlideLayoutManager = new SlideLayoutManager(rv, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(rv);

        rv.setLayoutManager(mSlideLayoutManager);

        initListener();

    }

    @Override
    protected void initViews() {


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }



    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {

                } else if (direction == ItemConfig.SLIDED_RIGHT) {

                }
                int position = viewHolder.getAdapterPosition();
// list.remove(0);

            }

            @Override
            public void onClear() {
                page = new Random().nextInt(31);
                //addData();
                request(page);
            }
        });
    }

    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_slide, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SlideBean bean = list.get(position);
            Glide.with(MainActivity.this).load(bean.getItemBg()).into(holder.imgBg);

            holder.tvTitle.setText(bean.getTitle());
            //holder.userIcon.setImageResource(bean.getUserIcon());
            holder.userSay.setText(bean.getUserSay());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgBg;
            ImageView userIcon;
            TextView tvTitle;
            TextView userSay;

            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                userIcon = itemView.findViewById(R.id.img_user);
                tvTitle = itemView.findViewById(R.id.tv_title);
                userSay = itemView.findViewById(R.id.tv_user_say);
            }
        }
    }
}
