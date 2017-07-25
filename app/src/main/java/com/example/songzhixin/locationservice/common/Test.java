package com.example.songzhixin.locationservice.common;

import com.example.songzhixin.locationservice.ui.activity.MainActivity;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by songzhixin on 2017/7/21.
 */

public class Test {
    public static void main(String args[]) {
        try {
            Const m = new Const();
            Class c = Class.forName("com.example.songzhixin.locationservice.ui.activity.MainActivity");
//            Class c = m.getClass();
            for (Method method : c.getMethods()) {
                System.out.println(method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
