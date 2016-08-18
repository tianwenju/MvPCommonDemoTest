package com.delta.ams.mvpdemo.model.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.delta.ams.mvpdemo.MyApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginDaoImpl implements LoginDao {
    private OnLoginListener mOnLoginListener;
    private final static String LOG_TAG = LoginDaoImpl.class.getSimpleName();

    public void setOnLoginListener(OnLoginListener mOnLoginListener) {
        this.mOnLoginListener = mOnLoginListener;
    }

    public void login(String username, String password, final Context context) {
        Map<String, String> param = new HashMap<>();
        param.put("name", username);
        param.put("pwd", password);
        JSONObject object = new JSONObject(param);
        JsonObjectRequest request = new JsonObjectRequest(Method.POST,
                com.delta.ams.mvpdemo.Common.URL.LOGIN_URL, object, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (mOnLoginListener != null) {
                    if (response != null) {
                        mOnLoginListener.onSuccess(response
                                .toString());
                    }
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {

                    mOnLoginListener.onFailed(error.getMessage());
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        Log.i(LOG_TAG, "Body" + new String(request.getBody()));
        try {
            Log.i(LOG_TAG, request.getHeaders().toString());
        } catch (AuthFailureError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MyApplication.getQueue().add(request);

    }

    @Override
    public void logout() {

    }

    public interface OnLoginListener {
        void onSuccess(String cookies);

        void onFailed(String errorMsg);
    }

}
