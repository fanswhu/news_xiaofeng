package com.jayce.xiaofeng_news.entity;


/**
 * Created by 钉某人
 * github: https://github.com/DingMouRen
 * email: naildingmouren@gmail.com
 */


public class SlideBean {
    private String mItemBg;
    private String mTitle;
    private int mUserIcon;
    private String mUserSay;

    public SlideBean(String mItemBg, String mTitle, int mUserIcon, String mUserSay) {
        this.mItemBg = mItemBg;
        this.mTitle = mTitle;
        this.mUserIcon = mUserIcon;
        this.mUserSay = mUserSay;
    }

    public String getItemBg() {
        return mItemBg;
    }

    public void setItemBg(String mItemBg) {
        this.mItemBg = mItemBg;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getUserIcon() {
        return mUserIcon;
    }

    public void setUserIcon(int mUserIcon) {
        this.mUserIcon = mUserIcon;
    }

    public String getUserSay() {
        return mUserSay;
    }

    public void setUserSay(String mUserSay) {
        this.mUserSay = mUserSay;
    }
}
