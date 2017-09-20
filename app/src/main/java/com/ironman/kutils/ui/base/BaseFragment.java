package com.ironman.kutils.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ironman.kutils.utils.SystemBarHelper;

import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: 
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

	public void initToolbar(Toolbar toolbar, boolean isShowBackIcon) {
		AppCompatActivity mAppCompatActivity = (AppCompatActivity) getActivity();
		mAppCompatActivity.setSupportActionBar(toolbar);
		ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setDisplayHomeAsUpEnabled(isShowBackIcon);
			SystemBarHelper.immersiveStatusBar(getActivity(), 0);
			SystemBarHelper.setHeightAndPadding(getActivity(), toolbar);
		}
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
