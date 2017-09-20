package com.ironman.kutils.ui.base;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
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

