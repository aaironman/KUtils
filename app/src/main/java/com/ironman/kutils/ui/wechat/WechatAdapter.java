package com.ironman.kutils.ui.wechat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ironman.kutils.R;
import com.ironman.kutils.model.wechatModel.WXItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/25
 * 描述:
 */
public class WechatAdapter extends RecyclerView.Adapter<WechatAdapter.WechatViewHolder> {

    private Context mContext;
    private List<WXItemBean> mList;

    public WechatAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setData(List<WXItemBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<WXItemBean> list) {
        if (list == null)
            return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public WechatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_wechat_item, parent, false);
        return new WechatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WechatViewHolder holder, int position) {
        WXItemBean wxItemBean = mList.get(position);
        Glide.with(mContext).load(wxItemBean.getPicUrl()).into(holder.ivPhoto);
        holder.tvTitle.setText(wxItemBean.getTitle());
        holder.tvTime.setText(wxItemBean.getCtime());
        holder.tvDec.setText(wxItemBean.getDescription());
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class WechatViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_dec)
        TextView tvDec;
        @BindView(R.id.tv_time)
        TextView tvTime;

        WechatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
