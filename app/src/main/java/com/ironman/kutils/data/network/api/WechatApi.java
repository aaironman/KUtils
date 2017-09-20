package com.ironman.kutils.data.network.api;

import com.ironman.kutils.model.wechatModel.WXHttpResponse;
import com.ironman.kutils.model.wechatModel.WXItemBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述:  微信精选api
 */
public interface WechatApi {

    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
}
