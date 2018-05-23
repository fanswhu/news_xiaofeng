package com.jayce.xiaofeng_news.service;





import com.jayce.xiaofeng_news.entity.GankBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jayce on 2018/5/23.
 */

public interface GankService {
    @GET("api/data/福利/20/{page}")
    Observable<GankBean> getBenefit(@Path("page") int page);

}
