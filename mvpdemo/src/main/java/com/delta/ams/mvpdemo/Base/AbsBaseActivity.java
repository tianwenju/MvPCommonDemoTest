package com.delta.ams.mvpdemo.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.delta.ams.mvpdemo.widget.DialogManager;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * @author V.wenju.tian
 *         Class Note:
 *         1 所有的activity继承于这个类，
 *         2 实现BaseView中的方法（处理进度条，显示对话框）
 */
public abstract class AbsBaseActivity extends AppCompatActivity implements BaseView {
    protected static String TAG_LOG = null;// Log tag

    protected Context mContext = null;//context


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mContext = this;

        TAG_LOG = this.getClass().getSimpleName();


        if (getContentViewID() != 0)
            setContentView(getContentViewID());

        initToolBar();
        initViewsAndEvents();
    }


    /**
     * init views and events here
     */
    protected abstract void initViewsAndEvents();

    /**
     * bind layout resource file
     */
    protected abstract int getContentViewID();

    /**
     * todo init tool bar
     */
    protected abstract void initToolBar();


    /**
     * implements methods in BaseView
     */

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showProgress(String message) {
        DialogManager.showProgressDialog(mContext, message);
    }

    @Override
    public void showProgress(String message, int progress) {
        DialogManager.showProgressDialog(mContext, message, progress);
    }

    @Override
    public void hideProgress() {
        DialogManager.hideProgressDialog();
    }

    @Override
    public void showErrorMessage(String msg, String content) {
        DialogManager.showErrorDialog(mContext, msg, content, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
    }
}

