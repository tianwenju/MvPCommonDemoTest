package com.delta.ams.mvpdemo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.delta.ams.mvpdemo.Base.AbsBaseActivity;
import com.delta.ams.mvpdemo.ui.login.login_contact.LoginContract;
import com.delta.ams.mvpdemo.ui.login.login_contact.LoginPersenter;

public class MainActivity extends AbsBaseActivity implements LoginContract.View, View.OnClickListener {


    private EditText et_name;
    private EditText et_password;
    private Button bt_login;
    private LoginPersenter mLoginPersenter;
    private String mUserName;
    private String mUserPassword;

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        mLoginPersenter
                = new LoginPersenter();
        mLoginPersenter.attachView(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

    }


    @Override
    protected void initToolBar() {

    }

    @Override
    public void LoginSucess(String response) {

        Log.d("dsd", "LoginSucess() called with: " + "response = [" + response + "]");
        Toast.makeText(this, "正常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoginError(String errorMessage) {
        Log.d("dsd", "LoginError: ");
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        mUserName = et_name.getText().toString().trim();
        mUserPassword = et_password.getText().toString().trim();
        mLoginPersenter.login(mUserName, mUserPassword,this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPersenter.detachView();
    }
}
