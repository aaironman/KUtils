package com.ironman.kutils.widget.recylerview;

import android.view.View;

/**
 * Created by 冯浩 on 2017/5/24.
 */

public interface OnListLoadNextPageListener {
	/**
	 * 开始加载下一页
	 *
	 * @param view 当前RecyclerView/ListView/GridView
	 */
	public void onLoadNextPage(View view);
}
