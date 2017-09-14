package com.ironman.kutils.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: BaseFragment
 */
public class BaseFragment extends Fragment {
	
	private CompositeSubscription mCompositeSubscription;
	protected ProgressDialog pg = null;
	private Unbinder mUnBinder;
	
	
	public void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}
		
		this.mCompositeSubscription.add(s);
	}

	public ProgressDialog getProgressDialog() {
		if (pg == null) {
			pg = new ProgressDialog(getContext());
		}
		return pg;
	}
	
	public void setUnBinder(Unbinder unBinder) {
		mUnBinder = unBinder;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (this.mCompositeSubscription != null) {
			this.mCompositeSubscription.unsubscribe();
		}
	}
	
	@Override
	public void onDestroyView() {
		if (mUnBinder != null) {
			mUnBinder.unbind();
		}
		super.onDestroyView();
	}
}
