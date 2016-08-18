package com.delta.ams.mvpdemo.ui.login.login_contact;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.delta.ams.mvpdemo.model.login.LoginDaoImpl;

/**
 * Created by V.wenju.tian on 2016/8/18.
 */
public class LoginPersenter implements LoginContract.Persenter {


    private LoginContract.View mView;
    private Context mContext;
    private LoginDaoImpl mLoginDao;

    @Override
    public void attachView(LoginContract.View view) {
        this.mView = view;
        mLoginDao = new LoginDaoImpl();
        if (view instanceof Context) {
            mContext = (Context) view;
        } else {
            throw new ClassCastException("view is not a context");
        }
    }

    @Override
    public void detachView() {

        mView = null;
    }


    @Override
    public void login(String name, String passWord, Context mcontext) {

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(passWord)) {

            Toast.makeText(mContext, "sdsdd", Toast.LENGTH_SHORT).show();
        } else {
            mLoginDao.login(name, passWord, mcontext);
            mView.showProgress("登陆中。。");
            mLoginDao.setOnLoginListener(new LoginDaoImpl.OnLoginListener() {
                @Override
                public void onSuccess(String cookies) {

                    mView.LoginSucess(cookies);
                    mView.hideProgress();
                }

                @Override
                public void onFailed(String errorMsg) {

                    mView.LoginError(errorMsg);
                    mView.hideProgress();
                }
            });

        }

    }
}
