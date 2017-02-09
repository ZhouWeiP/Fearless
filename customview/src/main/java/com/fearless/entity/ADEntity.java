package com.fearless.entity;

/**
 * Created by zhouwei on 16/9/30.
 */
public class ADEntity {

    public String getFront() {
        return mFront;
    }

    public void setFront(String front) {
        mFront = front;
    }

    public String getBack() {
        return mBack;
    }

    public void setBack(String back) {
        mBack = back;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public ADEntity(String front) {
        mFront = front;
    }

    public ADEntity(String front,String back,String url) {
        mFront = front;
        mBack=back;
        mUrl=url;
    }


    private  String mFront; //
    private String mBack;
    private String mUrl;
}
