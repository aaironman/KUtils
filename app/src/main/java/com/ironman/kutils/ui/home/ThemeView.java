package com.ironman.kutils.ui.home;

import com.ironman.kutils.model.zhihuModel.ThemeListBean;
import com.ironman.kutils.ui.base.MvpView;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public interface ThemeView extends MvpView {

    void showLoading();

    void hideLoading();

    void setData(ThemeListBean data);

    void showError(int code, String msg);

    void showEmpty();

}
