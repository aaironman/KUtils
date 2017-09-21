package com.ironman.kutils.widget.recylerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.ironman.kutils.R;


/**
 * Created by 冯浩 on 2017/5/24.
 */

public class RecyclerFooterView extends RelativeLayout {

	RelativeLayout rlLoadingForMore;
	RelativeLayout rlNoData;

	public RecyclerFooterView(Context context) {
		super(context);
		init();
	}

	public RecyclerFooterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RecyclerFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void init() {
		View view = inflate(getContext(), R.layout.loading_for_more, this);
		rlLoadingForMore = (RelativeLayout) view.findViewById(R.id.rl_loading_for_more);
		rlNoData = (RelativeLayout) view.findViewById(R.id.rl_no_data);
		hideLoadView();
	}

	public void setShowLoadView(boolean isShow) {

		if (isShow) {
			rlLoadingForMore.setVisibility(View.VISIBLE);
			rlNoData.setVisibility(View.GONE);
		} else {
			rlLoadingForMore.setVisibility(View.GONE);
			rlNoData.setVisibility(View.VISIBLE);
		}

	}

	public void hideLoadView(){
		rlLoadingForMore.setVisibility(View.GONE);
		rlNoData.setVisibility(View.GONE);
	}

}
