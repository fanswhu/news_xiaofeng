package com.jayce.xiaofeng_news.service;

import rx.Observer;

/**
 * Created by jayce on 2018/5/23.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(Object o) {

            onSuccess((T) o);



    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());

    }

    public abstract  void onSuccess(T t);
    public abstract  void onError(String e);
}
