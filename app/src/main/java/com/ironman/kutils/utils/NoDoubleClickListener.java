package com.ironman.kutils.utils;

import android.view.View;

/**
 * 作者: miaocong
 * 时间: 2017/9/13
 * 描述:
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        if (!ClickUtils.isFastDoubleClick()){
            doClick(v);
        }
    }

    public abstract void doClick(View v);
}
