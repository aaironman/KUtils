package com.ironman.kutils.ui.wechat;

import com.ironman.kutils.data.network.ExceptionHandle;
import com.ironman.kutils.model.wechatModel.WXHttpResponse;
import com.ironman.kutils.model.wechatModel.WXItemBean;
import com.ironman.kutils.ui.base.MvpRxPresenter;
import com.ironman.kutils.utils.Constants;
import com.ironman.kutils.utils.RxUtils;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者: miaocong
 * 时间: 2017/9/25
 * 描述:
 */
public class WechatPresenter extends MvpRxPresenter<WechatView> {

    private int mPage = 1;
    private int mPageTotal = 1;
    private boolean isNoMoreDatas;

    public void initData(boolean pullToRefresh) {
        if (pullToRefresh)
            getView().showLoading();

        isNoMoreDatas = false;
        mPage = 1;
        mPageTotal = 1;
        requestData(mPage, false);
    }

    public void loadForMore() {
        if (isNoMoreDatas) {
            return;
        }
        mPage += 1;
        requestData(mPage, true);
    }

    private void requestData(int page, final boolean isLoadForMore) {

//        if (page > mPageTotal) {
//            getView().showFootView(false);
//            isNoMoreDatas = true;
//            return;
//        }
        if (isNoMoreDatas){
            return;
        }

        Subscription subscribe = RxUtils.toObservableNoRetry(wechatApi.getWXHot(Constants.KEY_API,Constants.PAGE_SIZE,page)).subscribe(new Subscriber<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable throwable) {
                ExceptionHandle.ResponseThrowable responseThrowable = ExceptionHandle.handleException(throwable);
                getView().hideLoading();
                getView().showError(responseThrowable.code, throwable.toString());
            }

            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> data) {
                if (data.getCode() == Constants.SUCCESS) {

                    List<WXItemBean> list = data.getNewslist();

                    if (list.size() == 0) {

                        if (isLoadForMore) {
                            getView().showFootView(false);
                            isNoMoreDatas = true;
                        } else {
                            getView().showEmpty();
                        }

                    } else {

                        if (data.getNewslist().size() == 0) {
                            getView().showFootView(false);
                            isNoMoreDatas = true;
                        } else {
                            getView().showFootView(true);
                        }

                        if (isLoadForMore) {
                            getView().loadMoreData(list);
                        } else {
                            getView().showContent(list);
                        }

                    }
                } else {

                    if (!isLoadForMore) {
                        getView().showFootView(false);
                    }

                    getView().showError(ExceptionHandle.HTTP_ERROR, data.getMsg());
                }
            }
        });
        addSubscription(subscribe);

    }

}
