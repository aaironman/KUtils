package com.ironman.kutils.data.network;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: ApiFactory
 */
public class ApiFactory {

    private static final String TAG = "ApiFactory";

    protected static final Object o = new Object();
    public static ApiHelper mApiHelper = null;


    public static ApiHelper getApiHelper() {
        synchronized (o) {
            if (mApiHelper == null) {
                mApiHelper = new ApiRetrofit().getApiHelper();
            }
            return mApiHelper;
        }
    }

}
