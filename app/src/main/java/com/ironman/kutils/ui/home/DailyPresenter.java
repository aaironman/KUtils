package com.ironman.kutils.ui.home;

import com.ironman.kutils.data.network.ExceptionHandle;
import com.ironman.kutils.model.zhihuModel.DailyListBean;
import com.ironman.kutils.ui.base.MvpRxPresenter;
import com.ironman.kutils.utils.RxUtils;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public class DailyPresenter extends MvpRxPresenter<DailyView> {

    public void initData(boolean isShowLoading){
        if (isShowLoading){
            getView().showLoading();
        }

        Subscription subscription = RxUtils.toObservableNoRetry(zhihuApi.getDailyList()).subscribe(new Subscriber<DailyListBean>() {
            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                ExceptionHandle.ResponseThrowable responseThrowable = ExceptionHandle.handleException(e);
                getView().showError(responseThrowable.code, e.toString());
            }

            @Override
            public void onNext(DailyListBean dailyListBean) {
                getView().setData(dailyListBean);
            }
        });
        addSubscription(subscription);
    }
}
