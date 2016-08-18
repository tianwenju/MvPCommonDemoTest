package com.delta.ams.mvpcommondemo;

import android.content.Context;

public interface LoginDao {
    public void login(String username, String password, Context context);

    public void logout();
}
