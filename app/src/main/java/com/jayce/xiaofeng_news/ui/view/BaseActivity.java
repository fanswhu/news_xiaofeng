package com.jayce.xiaofeng_news.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by jayce on 2018/5/23 jflgdslmgg;l;f;.
 */

public abstract class BaseActivity  extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();


    }

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void initEvents();


    protected abstract void initDatas();






}
