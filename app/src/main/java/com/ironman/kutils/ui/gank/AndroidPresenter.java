package com.ironman.kutils.ui.gank;

import com.ironman.kutils.data.network.ExceptionHandle;
import com.ironman.kutils.model.gankModel.GankHttpResponse;
import com.ironman.kutils.model.gankModel.GankItemBean;
import com.ironman.kutils.ui.base.MvpRxPresenter;
import com.ironman.kutils.utils.Constants;
import com.ironman.kutils.utils.RxUtils;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者: miaocong
 * 时间: 2017/9/26
 * 描述:
 */
public class AndroidPresenter extends MvpRxPresenter<AndroidView> {

    private int mPage = 1;
    private int mPageTotal = 1;
    private boolean isNoMoreDatas;
    private String tech;

    public void initData(String tech,boolean pullToRefresh) {
        if (pullToRefresh)
            getView().showLoading();

        isNoMoreDatas = false;
        mPage = 1;
        mPageTotal = 1;
        this.tech = tech;
        requestData(tech,mPage, false);
    }

    public void loadForMore() {
        if (isNoMoreDatas) {
            return;
        }
        mPage += 1;
        requestData(tech,mPage, true);
    }

    private void requestData(String tech,int page, final boolean isLoadForMore) {

        if (isNoMoreDatas){
            return;
        }

        Subscription subscribe = RxUtils.toObservableNoRetry(gankApi.getTechList(tech,Constants.PAGE_SIZE,page)).subscribe(new Subscriber<GankHttpResponse<List<GankItemBean>>>() {
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
            public void onNext(GankHttpResponse<List<GankItemBean>> data) {
                if (!data.getError()) {

                    List<GankItemBean> list = data.getResults();

                    if (list.size() == 0) {

                        if (isLoadForMore) {
                            getView().showFootView(false);
                            isNoMoreDatas = true;
                        } else {
                            getView().showEmpty();
                        }

                    } else {

                        if (list.size() == 0) {
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

                    getView().showError(ExceptionHandle.HTTP_ERROR, "服务器错误");
                }
            }
        });
        addSubscription(subscribe);

    }


}
