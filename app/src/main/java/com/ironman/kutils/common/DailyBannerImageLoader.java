package com.ironman.kutils.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ironman.kutils.model.zhihuModel.DailyListBean;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述: 日报banner图片下载器
 */
public class DailyBannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        DailyListBean.TopStoriesBean topStoriesBean = (DailyListBean.TopStoriesBean) path;
        Glide.with(context)
                .load(topStoriesBean.getImage())
                .into(imageView);
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
