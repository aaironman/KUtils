package com.ironman.kutils.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironman.kutils.R;
import com.ironman.kutils.model.zhihuModel.ThemeListBean;
import com.ironman.kutils.ui.base.MvpLazyFragment;
import com.ironman.kutils.utils.UIUtils;
import com.ironman.kutils.widget.MultiStateView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述:主题
 */
public class ThemeFragment extends MvpLazyFragment<ThemeView, ThemePresenter> implements ThemeView {


    @BindView(R.id.rv_theme)
    SwipeMenuRecyclerView rvTheme;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    private ThemeAdapter adapter;
//    private SmartRecyclerAdapter smartRecyclerAdapter;

    public static ThemeFragment newInstance() {
        ThemeFragment fragment = new ThemeFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        return view;
    }

    private void initView(){
        adapter = new ThemeAdapter(getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rvTheme.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getContext(), R.color.background_color)));
        rvTheme.setLayoutManager(layoutManager);
        rvTheme.setAdapter(adapter);

        UIUtils.ptrFrameAddHeader(getActivity(), ptrFrame);
        ptrFrame.disableWhenHorizontalMove(true);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, rvTheme, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.initData(false);
            }
        });
    }


    @Override
    public void loadData() {
        presenter.initData(true);
    }

    @Override
    public ThemePresenter createPresenter() {
        return new ThemePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        mvState.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void hideLoading() {
        ptrFrame.refreshComplete();
    }

    @Override
    public void setData(ThemeListBean data) {
        mvState.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        dealData(data.getOthers());
    }

    @Override
    public void showError(int code, String msg) {
        UIUtils.handleErrorLayout(mvState,code,msg);
    }

    @Override
    public void showEmpty() {
        mvState.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    private void dealData(List<ThemeListBean.OthersBean> data){
        adapter.setData(data);
    }

}
