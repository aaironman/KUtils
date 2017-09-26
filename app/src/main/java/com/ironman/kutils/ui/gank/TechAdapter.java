package com.ironman.kutils.ui.gank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class TechAdapter extends RecyclerView.Adapter<TechAdapter.TechViewHolder> {

    private Context mContext;
    private List<GankItemBean> mList;

    public TechAdapter(Context mContext) {
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
    public TechViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_tech_item, parent, false);
        return new TechViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TechViewHolder holder, int position) {
        GankItemBean gankItemBean = mList.get(position);
        holder.tvTitle.setText(gankItemBean.getDesc());
        holder.tvAuthor.setText(gankItemBean.getWho());
        holder.tvDate.setText(gankItemBean.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class TechViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_date)
        TextView tvDate;

        TechViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
