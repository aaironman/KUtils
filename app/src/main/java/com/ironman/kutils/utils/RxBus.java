package com.ironman.kutils.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: RxBus
 */
public final class RxBus {
	
	private static final String TAG = RxBus.class.getSimpleName();
	
	private static volatile RxBus defaultInstance;
	
	private SerializedSubject<Object, Object> rxBus;
	
	private RxBus() {
		rxBus = new SerializedSubject(PublishSubject.create());
	}
	
	public static synchronized RxBus getInstance() {
		if (defaultInstance == null) {
			synchronized (RxBus.class) {
				if (defaultInstance == null) {
					defaultInstance = new RxBus();
				}
			}
		}
		return defaultInstance;
	}
	
	public void postEvent(Object event) {
		if (this.hasObservers()) rxBus.onNext(event);
	}
	
	public <T> Observable<T> toObservable(Class<T> type) {
		return rxBus.asObservable().ofType(type).onBackpressureBuffer();
	}
	
	private boolean hasObservers() {
		return rxBus.hasObservers();
	}
	
}
