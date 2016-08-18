package com.delta.ams.mvpdemo.Base;

/**
 * Created by V.wenju.tian on 2016/8/18.
 */
public interface BaseView {

    void close();

    void showProgress(String msg);

    void showProgress(String msg, int progress);

    void hideProgress();

    void showErrorMessage(String msg, String content);
}
