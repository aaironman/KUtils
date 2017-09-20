package com.ironman.kutils.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.ironman.kutils.R;
import com.ironman.kutils.ui.base.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/19
 * 描述:
 */
public class HomeFragment extends LazyFragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pst_tab)
    PagerSlidingTabStrip pstTab;
    @BindView(R.id.pager)
    ViewPager pager;
    ZhihuPagerAdapter pagerAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, null);
        setUnBinder(ButterKnife.bind(this, view));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(toolbar, false);
        initView();
    }

    private void initView(){
        pagerAdapter = new ZhihuPagerAdapter(getChildFragmentManager());
        pager.setAdapter(pagerAdapter);

        pstTab.setViewPager(pager);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
