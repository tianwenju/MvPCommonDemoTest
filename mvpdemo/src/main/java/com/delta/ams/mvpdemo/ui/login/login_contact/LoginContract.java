package com.delta.ams.mvpdemo.ui.login.login_contact;

import android.content.Context;

import com.delta.ams.mvpdemo.Base.BasePresenter;
import com.delta.ams.mvpdemo.Base.BaseView;

/**
 * Created by V.wenju.tian on 2016/8/18.
 */
public interface LoginContract {

    interface Persenter extends BasePresenter<View> {

        void login(String name, String passWord, Context mContext);
    }

    interface View extends BaseView {

        void LoginSucess(String response);

        void LoginError(String errorMessage);
    }

}
