package com.example.songzhixin.locationservice.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.songzhixin.locationservice.R;
import com.example.songzhixin.locationservice.common.Const;
import com.example.songzhixin.locationservice.http.Url;
import com.example.songzhixin.locationservice.model.MyActivity;
import com.example.songzhixin.locationservice.weight.App;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by songzhixin on 2017/7/23.
 */

public class MainActivity extends BaseActivity {
    private Timer mTimer;
    private TimerTask mTask;
    private RequestQueue mQueue;
    private StringRequest request;
    private Gson mGson;
    @Override
    public void initParms(Bundle bundle) {
    }

    @Override
    protected void initProp() {
        setSetStatusBar(false);
        setmAllowFullScreen(false);
        setAllowScreenRoate(false);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void intiViewAndObject() {
        mGson = new Gson();
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                mQueue.add(request);
            }
        };
        mQueue = Volley.newRequestQueue(this);
        request = new StringRequest(Request.Method.POST, Url.getUrlInfo(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyActivity activity = mGson.fromJson(response, MyActivity.class);
                        if (activity.status_code == 1) {
                            // TODO 去处理发来的数据， 发送更新广播

                        } else {
                            /**
                             * TODO 发送广播，通知活动结束
                             */
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("网络错误");
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                return ("{\"token\":\"" + App.getToken() + "\"}").getBytes();
            }
        };

    }

    @Override
    public void setListener() {

    }

    @Override
    public void weightClick(View view) {

    }

    @Override
    public void doBusiness() {
        StringRequest request = new StringRequest(Request.Method.POST, Url.getUrlInfo(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w(TAG, "onResponse: " + response);
                        MyActivity myActivity = mGson.fromJson(response, MyActivity.class);
                        if (myActivity.status_code == 1) {
                            App.isInTheActivity = true;
                            mTimer.schedule(mTask, 0, Const.REFRESH_PERIOD);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.w(TAG, "onErrorResponse: " + error.toString() );
                    }
                }
                ){
            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                return ("{\"token\":\"" + App.getToken() + "\"}").getBytes();
            }
        };
        mQueue.add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
