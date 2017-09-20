package com.ironman.kutils.ui.gold;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ironman.kutils.R;
import com.ironman.kutils.ui.base.LazyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/19
 * 描述:
 */
public class GoldFragment extends LazyFragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static GoldFragment newInstance() {
        GoldFragment fragment = new GoldFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gold, null);
        setUnBinder(ButterKnife.bind(view));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initToolbar(toolbar,false);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
