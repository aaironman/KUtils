package com.ironman.kutils.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ironman.kutils.R;
import com.ironman.kutils.model.zhihuModel.ThemeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.ironman.kutils.R.id.iv_photo;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private Context mContext;
    private List<ThemeListBean.OthersBean> mList;

    public ThemeAdapter(Context mContext) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
    }

    public void setData(List<ThemeListBean.OthersBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<ThemeListBean.OthersBean> list) {
        if (list == null)
            return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_theme_item, null);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThemeViewHolder holder, int position) {
        ThemeListBean.OthersBean othersBean = mList.get(position);
        holder.tvTitle.setText(othersBean.getName());
        Glide.with(mContext).load(othersBean.getThumbnail())
                .apply(bitmapTransform(new RoundedCornersTransformation(16,0)))
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ThemeViewHolder extends RecyclerView.ViewHolder{
        @BindView(iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ThemeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
