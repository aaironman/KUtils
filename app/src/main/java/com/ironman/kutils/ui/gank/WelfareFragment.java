package com.ironman.kutils.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironman.kutils.R;
import com.ironman.kutils.model.gankModel.GankItemBean;
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
 * 时间: 2017/9/26
 * 描述:
 */
public class WelfareFragment extends MvpLazyFragment<WelfareView,WelfarePresenter> implements WelfareView, SwipeMenuRecyclerView.LoadMoreListener
{

    @BindView(R.id.rv_daily)
    SwipeMenuRecyclerView rvDaily;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    private GirlAdapter adapter;

    public static WelfareFragment newInstance() {
        WelfareFragment fragment = new WelfareFragment();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView(){
        adapter = new GirlAdapter(getActivity());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvDaily.addItemDecoration(new DefaultItemDecoration(ContextCompat.getColor(getContext(), R.color.background_color),8, 8));
        rvDaily.setLayoutManager(layoutManager);


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

        UIUtils.recycleViewLoadForMore(getActivity(),rvDaily);
        rvDaily.setLoadMoreListener(this);
        rvDaily.setAdapter(adapter);
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
    public void showContent(List<GankItemBean> data) {
        mvState.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        adapter.setData(data);
    }


    @Override
    public void showError(int code, String msg) {
        UIUtils.handleErrorLayout(mvState,code,msg);
    }

    @Override
    public void showEmpty() {
        mvState.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void showFootView(boolean b) {
        rvDaily.loadMoreFinish(false, b);
    }

    @Override
    public void loadMoreData(List<GankItemBean> data) {
        adapter.addList(data);
    }


    @Override
    public void loadData() {
        presenter.initData(true);
    }

    @Override
    public WelfarePresenter createPresenter() {
        return new WelfarePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLoadMore() {
        presenter.loadForMore();
    }
}
