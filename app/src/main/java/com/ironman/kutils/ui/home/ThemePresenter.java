package com.ironman.kutils.ui.home;

import com.ironman.kutils.data.network.ExceptionHandle;
import com.ironman.kutils.model.zhihuModel.ThemeListBean;
import com.ironman.kutils.ui.base.MvpRxPresenter;
import com.ironman.kutils.utils.RxUtils;

import rx.Subscriber;
import rx.Subscription;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public class ThemePresenter extends MvpRxPresenter<ThemeView> {


    public void initData(boolean isShowLoading){
        if (isShowLoading){
            getView().showLoading();
        }

        Subscription subscription = RxUtils.toObservableNoRetry(zhihuApi.getThemeList()).subscribe(new Subscriber<ThemeListBean>() {
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
            public void onNext(ThemeListBean themeListBean) {
                getView().setData(themeListBean);
            }
        });
        addSubscription(subscription);
    }

}
