package com.ironman.kutils.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironman.kutils.R;
import com.ironman.kutils.ui.base.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述:主题
 */
public class ThemeFragment extends LazyFragment {


    @BindView(R.id.rv_theme)
    RecyclerView rvTheme;

    public static ThemeFragment newInstance() {
        ThemeFragment fragment = new ThemeFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        return view;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
