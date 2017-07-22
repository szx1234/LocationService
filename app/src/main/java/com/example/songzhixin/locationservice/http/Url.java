package com.example.songzhixin.locationservice.http;

/**
 * Created by songzhixin on 2017/7/22.
 */

public class Url {

    public static final String URL_BASE = "http://www.bananachocolatemilk.com";
    public static final String URL_LOGIN = "/sign/signin";
    public static String getUrlLogin () {
//        return "http://114.215.96.138";
        return URL_BASE + URL_LOGIN;
    }

    public static final String URL_INFO = "/activity/info";
    public static String getUrlInfo () {
        return URL_BASE + URL_INFO;
    }
    public static String getToken () {
        return "795f25f80e4e38ccb2d2c55295e0e5f9";
    }
}
