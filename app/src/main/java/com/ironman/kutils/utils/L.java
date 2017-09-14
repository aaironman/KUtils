package com.ironman.kutils.utils;

import android.util.Log;

import com.ironman.kutils.BuildConfig;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: Log统一管理类
 */
public class L {

    public static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "KUtils";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }


    public static void d(Class<?> _class, String msg) {
        if (isDebug)
            Log.i(_class.getName(), msg);
    }

    public static void e(Class<?> _class, String msg) {
        if (isDebug)
            Log.i(_class.getName(), msg);
    }

    public static void v(Class<?> _class, String msg) {
        if (isDebug)
            Log.i(_class.getName(), msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
