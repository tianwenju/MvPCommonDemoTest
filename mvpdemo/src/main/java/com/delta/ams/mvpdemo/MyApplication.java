package com.delta.ams.mvpdemo;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    public static Context mContext;

    public static RequestQueue mQueue;

    public static Context getContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        MyApplication.mContext = mContext;
    }

    public void onCreate() {
        super.onCreate();
        // HTTPSTrustManager.allowAllSSL ();FIXME 確定合適允許HTTPS請求
        mQueue = Volley.newRequestQueue(getApplicationContext());

    };

    public static RequestQueue getQueue() {
        return mQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getQueue().add(req);
    }
    public static void stopOne(Object object) {
        getQueue().cancelAll(object);
    }

}
