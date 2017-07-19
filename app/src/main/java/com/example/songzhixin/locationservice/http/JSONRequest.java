package com.example.songzhixin.locationservice.http;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by songzhixin on 2017/7/19.
 */

public class JSONRequest<T> extends Request<T> {

    private Gson mGson;
    private Class<T> mClass;
    private final Response.Listener<T> mListener;
    public JSONRequest(int method, String url, Class<T> clazz, Response.Listener listener, Response.ErrorListener elistener) {
        super(method, url, elistener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
