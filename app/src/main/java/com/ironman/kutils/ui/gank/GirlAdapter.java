package com.ironman.kutils.ui.gank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ironman.kutils.R;
import com.ironman.kutils.model.gankModel.GankItemBean;

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
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_girl_item, parent, false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GirlViewHolder holder, int position) {
        GankItemBean gankItemBean = mList.get(position);
        Glide.with(mContext).load(gankItemBean.getUrl()).into(holder.ivPhoto);
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
