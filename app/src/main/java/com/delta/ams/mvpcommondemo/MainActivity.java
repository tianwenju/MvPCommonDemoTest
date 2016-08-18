package com.delta.ams.mvpcommondemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginDaoImpl.OnLoginListener {

    private EditText et_name;
    private EditText et_password;
    private Button bt_login;
    private LoginDaoImpl mLoginDao;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        mLoginDao = new LoginDaoImpl();
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {

        String userName = et_name.getText().toString().trim();
        String userPassword = et_password.getText().toString().trim();
        mDialog = new ProgressDialog(this);
        mDialog.show();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)) {

            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mLoginDao.login(userName, userPassword, this);
            mLoginDao.setOnLoginListener(this);

        }
    }

    @Override
    public void onSuccess(String cookies) {

        mDialog.dismiss();
        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailed(String errorMsg) {
        mDialog.dismiss();
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
    }
}
