package com.ironman.kutils.data.network.api;

import com.ironman.kutils.model.goldModel.GoldHttpResponse;
import com.ironman.kutils.model.goldModel.GoldListBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述:稀土掘金api
 */
public interface GoldApi {

    String HOST = "https://api.leancloud.cn/";

    /**
     * 文章列表
     */
    @GET("1.1/classes/Entry")
    Observable<GoldHttpResponse<List<GoldListBean>>> getGoldList(@Header("X-LC-Id") String id,
                                                                 @Header("X-LC-Sign") String sign,
                                                                 @Query("where") String where,
                                                                 @Query("order") String order,
                                                                 @Query("include") String include,
                                                                 @Query("limit") int limit,
                                                                 @Query("skip") int skip);

    /**
     * 热门推荐
     */
    @GET("1.1/classes/Entry")
    Observable<GoldHttpResponse<List<GoldListBean>>> getGoldHot(@Header("X-LC-Id") String id,
                                                              @Header("X-LC-Sign") String sign,
                                                              @Query("where") String where,
                                                              @Query("order") String order,
                                                              @Query("include") String include,
                                                              @Query("limit") int limit);
}
