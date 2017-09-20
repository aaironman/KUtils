package com.ironman.kutils.ui.base.delegate;


import android.support.annotation.NonNull;

import com.ironman.kutils.ui.base.MvpPresenter;
import com.ironman.kutils.ui.base.MvpView;


/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: 
 */
public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * 创建 Presenter
     */
    @NonNull
    public P createPresenter();

    /**
     * 返回MVP Presenter
     */
    public P getPresenter();

    /**
     * 设置 Presenter
     */
    public void setPresenter(P presenter);

    /**
     * 返回MVP 中的View
     */
    public V getMvpView();


}
