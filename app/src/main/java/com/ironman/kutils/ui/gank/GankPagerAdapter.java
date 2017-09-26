package com.ironman.kutils.ui.gank;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 作者: miaocong
 * 时间: 2017/9/26
 * 描述:
 */
public class GankPagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"ANDROID", "IOS", "前端","福利"};

    public GankPagerAdapter(FragmentManager fm) {
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
                return AndroidFragment.newInstance();
            case 1:
                return IosFragment.newInstance();
            case 2:
                return JsFragment.newInstance();
            case 3:
                return WelfareFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
