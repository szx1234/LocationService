package com.example.songzhixin.locationservice.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.songzhixin.locationservice.R;
import com.example.songzhixin.locationservice.http.Url;
import com.example.songzhixin.locationservice.weight.App;


import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by songzhixin on 2017/7/19.
 */

public class LoginActivity extends BaseActivity {
    private AutoCompleteTextView mInput_UserName;
    private EditText mInput_Password;
    private String username;
    private String password;
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
        mInput_UserName = (AutoCompleteTextView) findViewById(R.id.username);
        mInput_Password = (EditText) findViewById(R.id.password);
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
        if (!TextUtils.isEmpty(App.getToken())) {
            StringRequest request = new StringRequest(Request.Method.POST, Url.getUserProfile(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                JSONObject body = object.getJSONObject("body");
                                SharedPreferences.Editor editor = getSharedPreferences("USER_DETAIL", 0).edit();
                                editor.putString("AVATAR_URL", body.getString("avatar_url"));
                                editor.putString("USER_NAME", body.getString("user_name"));
                                editor.putString("NICKNAME", body.getString("nickname"));
                                editor.commit();
                                gotoMain();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.w(TAG, "onErrorResponse: " + error.toString() + "无效的token");
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
            App.getQueue().add(request);
        }
    }

    private void register() {
    }

    private void login() {
        if (!checkInput()) {
            showToast("账号或密码不能为空！");
            return;
        }

        StringRequest request = new StringRequest(Request.Method.POST, Url.getUrlLogin(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showToast("网络错误！");
                        error.printStackTrace();
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            public byte[] getBody() throws AuthFailureError {
                return ("{\"user_name\":\"" + username + "\",\"password\":\"" + password + "\"}").getBytes();
            }
        };
        App.getQueue().add(request);
    }

    private void onSuccess(String response) {
        JSONObject object = null;
        String desc = "";
        String token = "";
        int status_code = -1;
        try {
            object = new JSONObject(response);
            status_code = Integer.valueOf(object.getString("status_code"));
            if (status_code == 1) {
                JSONObject object_body = object.getJSONObject("body");
                token = object_body.getString("token");
                App.setToken(token);
                showToast("登陆成功");
                gotoMain();
                return;
            }
            /**
             * 登陆失败
             */
            desc = new String(object.getString("desc"));
            if (desc != null) {
                switch (desc) {
                    case "用户名不存在":
                        break;
                    case "密码错误":
                        mInput_Password.setText("");
                        break;
                    default:
                        break;
                }
            }
            showToast(desc);
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("解析错误");
        }
    }

    private void gotoMain() {
        finish();
//        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        goToActivity(MainActivity.class);
    }

    private boolean checkInput() {
        username = mInput_UserName.getText().toString();
        password = mInput_Password.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
