package com.ironman.kutils.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.ironman.kutils.R;
import com.ironman.kutils.data.network.ExceptionHandle;
import com.ironman.kutils.widget.MultiStateView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public class UIUtils {


    /**
     * 下拉刷新头样式
     *
     * @param context
     * @param ptrFrame
     */
    public static void ptrFrameAddHeader(Context context, PtrClassicFrameLayout ptrFrame) {

        final MaterialHeader header = new MaterialHeader(context);
        int[] colors = context.getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtil.dip2px(context,15), 0, DensityUtil.dip2px(context,10));

        ptrFrame.setResistance(2.7f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.0f);
        ptrFrame.setDurationToClose(150);
        ptrFrame.setDurationToCloseHeader(500);
        ptrFrame.setPullToRefresh(false);
        ptrFrame.setKeepHeaderWhenRefresh(true);
        ptrFrame.setHeaderView(header);
        ptrFrame.addPtrUIHandler(header);
        ptrFrame.disableWhenHorizontalMove(true);

    }

    /**
     * 处理网络情况错误时布局显示情况
     * @param mvState
     * @param code NETWORK_ERROR UNEXPECTED HTTP_ERROR
     * @param msg 显示文字
     */
    public static void handleErrorLayout(MultiStateView mvState, int code, String msg){
        mvState.setViewState(MultiStateView.VIEW_STATE_ERROR);
        TextView tv = (TextView) mvState.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.tv_error);
        ImageView iv = (ImageView) mvState.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.iv_error);
        if (code== ExceptionHandle.NETWORK_ERROR){//网络情况
            tv.setText(R.string.net_work_error);
            iv.setImageResource(R.drawable.empty_net_work_error);
        }else if (code== ExceptionHandle.HTTP_ERROR){//服务器请求成功，ret 为0  显示msg
            tv.setText(msg);
            iv.setImageResource(R.drawable.empty_error);
        }else {//其他错误情况 比如json解析失败...
            iv.setImageResource(R.drawable.empty_error);
            tv.setText(R.string.service_error);
        }
    }
}
