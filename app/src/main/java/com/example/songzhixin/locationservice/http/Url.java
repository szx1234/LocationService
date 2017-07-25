package com.example.songzhixin.locationservice.http;

/**
 * Created by songzhixin on 2017/7/22.
 */

public class Url {

    public static final String URL_BASE = "http://www.bananachocolatemilk.com";
    public static final String URL_LOGIN = "/sign/signin";
    public static final String URL_GET_PROFILE = "/profile/get";
    public static final String URL_INFO = "/activity/info";
    public static String getUrlInfo () {
        return URL_BASE + URL_INFO;
    }
    public static String getUrlLogin () {
        return URL_BASE + URL_LOGIN;
    }
    public static String getUserProfile() {
        return URL_BASE + URL_GET_PROFILE;
    }
}
