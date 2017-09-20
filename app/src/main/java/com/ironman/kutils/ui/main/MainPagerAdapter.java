package com.ironman.kutils.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ironman.kutils.ui.gank.GankFragment;
import com.ironman.kutils.ui.gold.GoldFragment;
import com.ironman.kutils.ui.home.HomeFragment;
import com.ironman.kutils.ui.wechat.WechatFragment;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: MainPagerAdapter
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

	public MainPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return HomeFragment.newInstance();
			case 1:
				return WechatFragment.newInstance();
			case 2:
				return GankFragment.newInstance();
			case 3:
				return GoldFragment.newInstance();
			default:
				return null;
		}
	}

}