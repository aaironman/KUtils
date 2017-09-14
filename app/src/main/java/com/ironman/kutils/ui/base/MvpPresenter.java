package com.ironman.kutils.ui.base;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: mvp Presenter
 */
public interface MvpPresenter<V extends MvpView> {

	/**
	 * 绑定View到Presenter
	 */
	void attachView(V view);

	/**
	 * 解绑View
	 */
	void detachView();

}

