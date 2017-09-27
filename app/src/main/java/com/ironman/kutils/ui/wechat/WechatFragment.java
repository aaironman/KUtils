package com.ironman.kutils.ui.wechat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ironman.kutils.R;
import com.ironman.kutils.model.wechatModel.WXItemBean;
import com.ironman.kutils.ui.base.MvpLazyFragment;
import com.ironman.kutils.utils.UIUtils;
import com.ironman.kutils.widget.MultiStateView;
import com.ironman.kutils.widget.recylerview.MyDividerItemDecoration;
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
 * 时间: 2017/9/19
 * 描述:
 */
public class WechatFragment extends MvpLazyFragment<WechatView,WechatPresenter> implements WechatView {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_wechat)
    RecyclerView rvWechat;
    @BindView(R.id.mv_state)
    MultiStateView mvState;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    private WechatAdapter adapter;
    private SmartRecyclerAdapter smartRecyclerAdapter;
    private RecyclerFooterView recyclerFooterView;

    public static WechatFragment newInstance() {
        WechatFragment fragment = new WechatFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, null);
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

        adapter = new WechatAdapter(getActivity());
        smartRecyclerAdapter = new SmartRecyclerAdapter(adapter);
        recyclerFooterView = new RecyclerFooterView(getActivity());
        smartRecyclerAdapter.setFooterView(recyclerFooterView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvWechat.addItemDecoration(new MyDividerItemDecoration(getActivity(),R.color.background_color,8));
        rvWechat.setLayoutManager(layoutManager);


        UIUtils.ptrFrameAddHeader(getActivity(), ptrFrame);
        ptrFrame.disableWhenHorizontalMove(true);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, rvWechat, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.initData(false);
            }
        });

        rvWechat.setAdapter(adapter);
        rvWechat.addOnScrollListener(mOnScrollListener);
    }

    private RecyclerOnScrollListener mOnScrollListener = new RecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            presenter.loadForMore();
        }
    };

    @Override
    public void loadData() {
        presenter.initData(true);
    }

    @Override
    public WechatPresenter createPresenter() {
        return new WechatPresenter();
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
    public void showContent(List<WXItemBean> data) {
        mvState.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        adapter.setData(data);
    }

    @Override
    public void showError(int code, String msg) {
        UIUtils.handleErrorLayout(mvState, code, msg);
    }

    @Override
    public void showEmpty() {
        mvState.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        TextView tv = (TextView) mvState.getView(MultiStateView.VIEW_STATE_EMPTY).findViewById(R.id.tv_empty);
        tv.setVisibility(View.VISIBLE);
        tv.setText("暂无数据");
    }

    @Override
    public void showFootView(boolean b) {
        recyclerFooterView.setShowLoadView(b);
    }

    @Override
    public void loadMoreData(List<WXItemBean> data) {
        adapter.addList(data);
    }

}
