package com.ironman.kutils.data.network;

import com.ironman.kutils.data.network.api.GankApi;
import com.ironman.kutils.data.network.api.GoldApi;
import com.ironman.kutils.data.network.api.WechatApi;
import com.ironman.kutils.data.network.api.ZhihuApi;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: ApiFactory
 */
public class ApiFactory {

    private static final String TAG = "ApiFactory";

    protected static final Object o = new Object();
    public static ZhihuApi zhihuApi = null;
    public static WechatApi wechatApi = null;
    public static GankApi gankApi = null;
    public static GoldApi goldApi = null;


    public static ZhihuApi getZhihuApi() {
        synchronized (o) {
            if (zhihuApi == null) {
                zhihuApi = new ApiRetrofit().getZhihuApi();
            }
            return zhihuApi;
        }
    }

    public static WechatApi getWechatApi() {
        synchronized (o) {
            if (wechatApi == null) {
                wechatApi = new ApiRetrofit().getWechatApi();
            }
            return wechatApi;
        }
    }

    public static GankApi getGankApi() {
        synchronized (o) {
            if (gankApi == null) {
                gankApi = new ApiRetrofit().getGankApi();
            }
            return gankApi;
        }
    }

    public static GoldApi getGoldApi(){
        synchronized (o) {
            if (goldApi == null) {
                goldApi = new ApiRetrofit().getGoldApi();
            }
            return goldApi;
        }
    }
}
