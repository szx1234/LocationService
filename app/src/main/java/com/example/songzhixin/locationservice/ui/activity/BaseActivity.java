package com.example.songzhixin.locationservice.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by songzhixin on 2017/7/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    Handler handler = new Handler();
    /**
     * 是否透明状态栏
     **/
    private boolean isSetStatusBar = false;
    /**
     * 是否允许全屏
     **/
    private boolean isAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);

        initProp();

        if (isSetStatusBar) {
            steepStatusBar();
        }
        if(isAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(mContextView);
        if (isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        initParms(getIntent().getExtras());
        intiViewAndObject();
        setListener();
        doBusiness();
    }

    /**
     * 初始化参数（由其他活动传过来的参数）
     */
    public abstract void initParms(Bundle bundle);

    /**
     * 配置初始化
     */
    protected abstract void initProp();

    /**
     * 绑定布局
     */
    public abstract int bindLayout();

    /**
     * 初始化View以及相关类变量
     */
    public abstract void intiViewAndObject();

    /**
     * 设置监听器
     */
    public abstract void setListener();

    /**
     * 处理点击事件
     */
    public abstract void weightClick(View view);

    @Override
    public void onClick(View v) {
        weightClick(v);
    }

    /**
     * 处理业务逻辑
     */
    public abstract void doBusiness();

    /**
     * 透明系统状态栏
     */
    private void steepStatusBar() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /**
     * 页面跳转
     */
    protected void startActivity(Class<?> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    /**
     * 显示Toast
     *
     * @param str
     */
    protected void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置是否透明系统状态栏
     */
    protected void setSetStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 设置是否支持全屏
     */
    protected void setmAllowFullScreen(boolean isAllowFullScreen) {
        this.isAllowFullScreen = isAllowFullScreen;
    }

    /**
     * 设置是否支持屏幕旋转
     */
    protected void setAllowScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    protected void goToActivity (Class clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}
