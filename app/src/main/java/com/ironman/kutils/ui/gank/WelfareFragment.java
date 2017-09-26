package com.ironman.kutils.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironman.kutils.R;
import com.ironman.kutils.ui.base.MvpLazyFragment;

import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/26
 * 描述:
 */
public class WelfareFragment extends MvpLazyFragment<WelfareView,WelfarePresenter> implements WelfareView{

    public static AndroidFragment newInstance() {
        AndroidFragment fragment = new AndroidFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        return view;
    }


    @Override
    public WelfarePresenter createPresenter() {
        return new WelfarePresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    @Override
    public void loadData() {

    }

    private void initView(){

    }
}
