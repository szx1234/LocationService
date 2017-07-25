package com.example.songzhixin.locationservice.weight;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.songzhixin.locationservice.http.Url;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by songzhixin on 2017/7/17.
 */

public class App extends Application {
    private static Context mAppContext;
    private static String token;
    private static RequestQueue mQueue;
    private static final String TAG = "App";
    public static boolean isInTheActivity = false;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        init();
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getAppContext()).edit();
        editor.putString("token", token);
        editor.commit();
        App.token = token;
    }

    public void init() {
        mQueue = Volley.newRequestQueue(mAppContext);
        token = PreferenceManager.getDefaultSharedPreferences(getAppContext()).getString("token", "");
    }

    public static RequestQueue getQueue() {
        return mQueue;
    }
}
