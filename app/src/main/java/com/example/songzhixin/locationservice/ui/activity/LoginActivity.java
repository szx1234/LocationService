package com.example.songzhixin.locationservice.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.songzhixin.locationservice.R;
import com.example.songzhixin.locationservice.model.MyActivity;
import com.google.gson.Gson;

/**
 * Created by songzhixin on 2017/7/19.
 */

public class LoginActivity extends BaseActivity {
    private Button mBtn_login;
    private TextView mBtn_register;
    @Override
    public void initParms(Bundle bundle) {

    }

    @Override
    protected void initProp() {
        setAllowScreenRoate(false);
        setmAllowFullScreen(true);
        setSetStatusBar(true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void intiViewAndObject() {
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_register = (TextView) findViewById(R.id.register);
    }

    @Override
    public void setListener() {
        mBtn_login.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
    }

    @Override
    public void weightClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
//                showToast("点击登陆");
                login();
                break;
            case R.id.register:
                showToast("点击注册");
                register();
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness() {
    }

    private void register() {
    }

    private void login() {

    }
}
