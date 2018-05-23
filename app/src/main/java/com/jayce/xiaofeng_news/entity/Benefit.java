package com.jayce.xiaofeng_news.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Benefit {

    private Boolean error;

    private List<String> results;

    public void setError(Boolean error) {
        this.error = error;
    }

    public void setResults(List<String> girls) {
        this.results = girls;
    }

    public Boolean getError() {
        return error;
    }

    public List<String> getResults() {
        return results;
    }

}
