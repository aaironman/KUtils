package com.ironman.kutils.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述:
 */
public class ZhihuPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"日报", "主题", "专栏","热门"};

    public ZhihuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return DailyFragment.newInstance();
            case 1:
                return ThemeFragment.newInstance();
            case 2:
                return ColumnFragment.newInstance();
            case 3:
                return HotFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
