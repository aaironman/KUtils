package com.ironman.kutils.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: rxJava 工具类
 */
public class RxUtils {

	/**
	 * 封装Observable 1.observable错误处理  2.线程调度
	 * @param observable observable
	 * @param <T> HttpResult
	 * @return 返回 Observable
	 */
	public static <T> Observable<T> toObservable(Observable<T> observable) {
		return observable.onErrorResumeNext(RefreshSessionKeyAndRetryUtils.refreshSsidAndRetry(observable))
				.compose(applySchedulers());
	}

	/**
	 * 封装Observable 线程调度
	 * @param observable observable
	 * @param <T> HttpResult
	 * @return 返回 Observable
	 */
	public static <T> Observable<T> toObservableNoRetry(Observable<T> observable) {
		return observable.compose(applySchedulers());
	}

	/**
	 * 线程调度
	 * @param <T>
	 * @return
	 */
	public static  <T> Observable.Transformer<T, T> applySchedulers() {
		return observable -> observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

}
