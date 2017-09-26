package com.ironman.kutils.ui.gank;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ironman.kutils.R;
import com.ironman.kutils.model.gankModel.GankItemBean;
import com.ironman.kutils.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/26
 * 描述:
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder> {
    private Context mContext;
    private List<GankItemBean> mList;

    public GirlAdapter(Context mContext) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
    }

    public void setData(List<GankItemBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<GankItemBean> list) {
        if (list == null)
            return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) DensityUtil.getScreenWidth(mContext) / (float) mList.get(position).getHeight() * 10f);
    }


    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_girl_item, parent, false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        GankItemBean gankItemBean = mList.get(position);
        if (mList.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.ivPhoto.getLayoutParams();
            layoutParams.height = mList.get(holder.getAdapterPosition()).getHeight();
        }

        Glide.with(mContext).asBitmap().load(gankItemBean.getUrl()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if(holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    if (mList.get(holder.getAdapterPosition()).getHeight() <= 0) {
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        int realHeight = (DensityUtil.getScreenWidth(mContext)/ 2) * height / width;
                        mList.get(holder.getAdapterPosition()).setHeight(realHeight);
                        ViewGroup.LayoutParams lp = holder.ivPhoto.getLayoutParams();
                        lp.height = realHeight;
                    }
                    holder.ivPhoto.setImageBitmap(resource);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class GirlViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;

        GirlViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
