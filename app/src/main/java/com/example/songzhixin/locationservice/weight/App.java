package com.example.songzhixin.locationservice.weight;

import android.app.Application;
import android.content.Context;

/**
 * Created by songzhixin on 2017/7/17.
 */

public class App extends Application {
    private static Context mAppContext;


    private static String token;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();

    }

    public static Context getAppContext() {
        return mAppContext;
    }
    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        App.token = token;
    }
}
