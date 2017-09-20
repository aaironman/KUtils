package com.ironman.kutils.ui.base;

import android.os.Bundle;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: 懒加载Fragment
 */
public abstract class LazyFragment extends BaseFragment {

	private static final String TAG = "LazyFragment";

	protected boolean isViewInitiated; //控件是否初始化完成
	protected boolean isVisibleToUser; //页面是否可见
	protected boolean isDataInitiated; //数据是否加载


	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		this.isVisibleToUser = isVisibleToUser;
		prepareFetchData(false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isViewInitiated = true;
		prepareFetchData(false);
	}

	public abstract void loadData();


	protected void prepareFetchData(boolean forceUpdate) {

		if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
			loadData();
			isDataInitiated = true;
		}
	}
}