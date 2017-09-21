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
import com.ironman.kutils.model.zhihuModel.DailyListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/21
 * 描述:
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder> {

    private Context mContext;
    private List<DailyListBean.StoriesBean> mList;

    public DailyAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setData(List<DailyListBean.StoriesBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<DailyListBean.StoriesBean> list) {
        if (list == null)
            return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_daily_item, parent, false);
        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyViewHolder holder, int position) {
        DailyListBean.StoriesBean storiesBean = mList.get(position);
        String imgUrl = "";
        if (storiesBean.getImages() != null  && storiesBean.getImages().size() != 0){
            imgUrl = storiesBean.getImages().get(0);
        }
        Glide.with(mContext).load(imgUrl).into(holder.ivPhoto);
        holder.tvTitle.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class DailyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        DailyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
