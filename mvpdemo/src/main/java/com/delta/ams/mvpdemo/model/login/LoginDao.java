package com.delta.ams.mvpdemo.model.login;

import android.content.Context;

public interface LoginDao {
    public void login(String username, String password, Context context);

    public void logout();
}
