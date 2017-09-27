package com.ironman.kutils.ui.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ironman.kutils.R;
import com.ironman.kutils.model.gankModel.GankItemBean;
import com.ironman.kutils.ui.base.MvpLazyFragment;
import com.ironman.kutils.utils.UIUtils;
import com.ironman.kutils.widget.MultiStateView;
import com.ironman.kutils.widget.recylerview.DividerGridItemDecoration;
import com.ironman.kutils.widget.recylerview.RecyclerFooterView;
import com.ironman.kutils.widget.recylerview.RecyclerOnScrollListener;
import com.ironman.kutils.widget.recylerview.SmartRecyclerAdapter;

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
public class WelfareFragment extends MvpLazyFragment<WelfareView,WelfarePresenter> implements WelfareView
{

    @BindView(R.id.rv_daily)
    RecyclerView rvDaily;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    private GirlAdapter adapter;


    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerFooterView recyclerFooterView;

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
        smartRecyclerAdapter = new SmartRecyclerAdapter(adapter);
        recyclerFooterView = new RecyclerFooterView(getActivity());
        smartRecyclerAdapter.setFooterView(recyclerFooterView);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvDaily.addItemDecoration(new DividerGridItemDecoration(getActivity(),R.drawable.line_divider,8));
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

        rvDaily.setAdapter(smartRecyclerAdapter);
        rvDaily.addOnScrollListener(mOnScrollListener);
    }

    private RecyclerOnScrollListener mOnScrollListener = new RecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            presenter.loadForMore();
        }
    };

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
        recyclerFooterView.setShowLoadView(b);
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

}
