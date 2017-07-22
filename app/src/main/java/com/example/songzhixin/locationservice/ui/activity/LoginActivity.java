package com.example.songzhixin.locationservice.ui.activity;

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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.songzhixin.locationservice.R;
import com.example.songzhixin.locationservice.http.Url;
import com.example.songzhixin.locationservice.weight.App;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
    RequestQueue mQueue;

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
        mQueue = Volley.newRequestQueue(this);
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
        if (!checkInput()) {
            showToast("账号或密码不能为空！");
            return;
        }
        final Map<String, String> params = new HashMap<>();
        params.put("user_name", "szx");
        params.put("password", "szx");

        StringRequest request = new StringRequest(Request.Method.POST, Url.getUrlLogin(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onSucess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
        mQueue.add(request);
    }

    private void onSucess(String response) {
        JSONObject object = null;
        String desc = "";
        String token = "";
        int status_code = -1;
        try {
            object = new JSONObject(response);
            status_code = Integer.valueOf(object.getString("status_code"));
            /**
             * 登陆成功
             * TODO 增加跳转逻辑
             */
            if (status_code == 1) {
                JSONObject object_body = object.getJSONObject("body");
                token = object_body.getString("token");
                Log.w("token", "onSucess: " + token);
                App.setToken(token);
                showToast("登陆成功");
                return;
            }
            /**
             * 登陆失败
             */
            desc = new String(object.getString("desc"));
            if (desc != null){
                switch (desc) {
                    case "用户名不存在":
//                        mInput_UserName.setText("");
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

    private boolean checkInput() {
        username = mInput_UserName.getText().toString();
        password = mInput_Password.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
