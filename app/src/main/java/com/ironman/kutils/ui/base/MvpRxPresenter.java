package com.ironman.kutils.ui.base;

import android.support.annotation.NonNull;

import com.ironman.kutils.data.network.ApiFactory;
import com.ironman.kutils.data.network.ApiHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: mvp rxjava Presenter
 */
public abstract class MvpRxPresenter<V extends MvpView> implements MvpPresenter<V> {


	public static final ApiHelper mApi = ApiFactory.getApiHelper();

	private CompositeSubscription mCompositeSubscription;

	private V view;

	@Override
	public void attachView(V view) {
		this.view = view;
	}

	public void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}
		this.mCompositeSubscription.add(s);
	}

	@NonNull
	public V getView() {
		return view;
	}

	@Override
	public void detachView() {
		if (this.mCompositeSubscription != null) {
			this.mCompositeSubscription.unsubscribe();
		}
	}



}
