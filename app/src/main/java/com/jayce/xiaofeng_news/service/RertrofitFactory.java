package com.jayce.xiaofeng_news.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jayce on 2018/5/23.
 */

public class RertrofitFactory {
    private static final String GANK_URL = "http://gank.io/";

    private static GankService gankService;

    public static synchronized GankService getGankService() {
        if (gankService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GANK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankService = retrofit.create(GankService.class);
        }
        return gankService;
    }

}
