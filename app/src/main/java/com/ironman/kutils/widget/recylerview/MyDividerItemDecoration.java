package com.ironman.kutils.widget.recylerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ironman.kutils.R;
import com.ironman.kutils.utils.DensityUtil;


/**
 * Created by 冯浩 on 2017/5/24.
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

	private Drawable mDivider;     //分割线Drawable
	private int mDividerHeight;  //分割线高度

	/**
	 * 使用line_divider中定义好的颜色
	 */
	public MyDividerItemDecoration(Context context) {
		mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
		mDividerHeight = DensityUtil.dip2px(context, 1);
	}

	/**
	 * @param context
	 * @param divider 分割线Drawable
	 * @param dividerHeight 分割线高度
	 */
	public MyDividerItemDecoration(Context context, int divider, float dividerHeight) {
		mDivider = ContextCompat.getDrawable(context, divider);
		mDividerHeight = DensityUtil.dip2px(context, dividerHeight);
	}

	//获取分割线尺寸
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.set(0, 0, 0, mDividerHeight);
	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();

		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);

			RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + mDividerHeight;
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}
}