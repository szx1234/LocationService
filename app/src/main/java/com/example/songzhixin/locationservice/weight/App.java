package com.example.songzhixin.locationservice.weight;

import android.app.Application;
import android.content.Context;

/**
 * Created by songzhixin on 2017/7/17.
 */

public class App extends Application {
    public static Context mAppContext;
    public static String token;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();

    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
