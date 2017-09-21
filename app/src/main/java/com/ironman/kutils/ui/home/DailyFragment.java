package com.ironman.kutils.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ironman.kutils.R;
import com.ironman.kutils.common.DailyBannerImageLoader;
import com.ironman.kutils.model.zhihuModel.DailyListBean;
import com.ironman.kutils.ui.base.MvpLazyFragment;
import com.ironman.kutils.utils.DensityUtil;
import com.ironman.kutils.utils.UIUtils;
import com.ironman.kutils.widget.MultiStateView;
import com.ironman.kutils.widget.recylerview.MyDividerItemDecoration;
import com.ironman.kutils.widget.recylerview.SmartRecyclerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

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
 * 描述:日报
 */
public class DailyFragment extends MvpLazyFragment<DailyView,DailyPresenter> implements DailyView, OnBannerListener {

    @BindView(R.id.rv_daily)
    RecyclerView rvDaily;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    private DailyAdapter adapter;
    private SmartRecyclerAdapter smartRecyclerAdapter;

    private View headerView;
    private Banner banner;

    public static DailyFragment newInstance() {
        DailyFragment fragment = new DailyFragment();
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
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        setUnBinder(ButterKnife.bind(this, view));
        return view;
    }

    private void initView(){
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.header_daily,null);
        banner = ButterKnife.findById(headerView,R.id.banner_daily);
        int w = DensityUtil.getScreenWidth(getContext());

        //设置banner宽高比
        banner.setLayoutParams(new FrameLayout.LayoutParams(w, w * 28 / 75));

        adapter = new DailyAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        smartRecyclerAdapter = new SmartRecyclerAdapter(adapter);
        smartRecyclerAdapter.setHeaderView(headerView);
        rvDaily.addItemDecoration(new MyDividerItemDecoration(getActivity(), R.drawable.line_divider, 8));
        rvDaily.setLayoutManager(layoutManager);
        rvDaily.setAdapter(smartRecyclerAdapter);

        UIUtils.ptrFrameAddHeader(getActivity(), ptrFrame);
        ptrFrame.disableWhenHorizontalMove(true);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, rvDaily, header);
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
    public DailyPresenter createPresenter() {
        return new DailyPresenter();
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
    public void setData(DailyListBean data) {
        mvState.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        dealContentData(data.getStories());
        dealHeaderData(data.getTop_stories());
    }

    @Override
    public void showError(int code, String msg) {
        UIUtils.handleErrorLayout(mvState,code,msg);
    }

    @Override
    public void showEmpty() {
        mvState.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    private void dealContentData(List<DailyListBean.StoriesBean> list){
        adapter.setData(list);
    }

    private void dealHeaderData(List<DailyListBean.TopStoriesBean> list){
        //简单使用
        banner.setImages(list)
                .setImageLoader(new DailyBannerImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
