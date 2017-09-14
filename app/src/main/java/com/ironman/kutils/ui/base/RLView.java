package com.ironman.kutils.ui.base;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: 下拉刷新和加载更多
 */
public interface RLView<T> extends MvpView{

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载中
     */
    void hideLoading();

    /**
     * 显示内容
     */
    void showContent(T data);

    /**
     * 显示错误
     */
    void showError(int code, String msg);

    /**
     * 显示内容空的情况
     */
    void showEmpty();

    /**
     * 是否显示footView
     */
    void showFootView(boolean b);

    /**
     * 加载更多数据
     *
     * @param data
     */
    void loadMoreData(T data);


}
