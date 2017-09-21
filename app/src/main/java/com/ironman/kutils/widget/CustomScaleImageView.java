package com.ironman.kutils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ironman.kutils.R;


/**
 * Created by 冯浩 on 2016/12/12.
 */

public class CustomScaleImageView extends ImageView {

	private int widthScale = 1;

	private int heightScale = 1;

	public CustomScaleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public CustomScaleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public CustomScaleImageView(Context context) {
		super(context);
	}

	private void init(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomScaleImageView);

		widthScale = a.getInteger(R.styleable.CustomScaleImageView_widthScale, 1);

		heightScale = a.getInteger(R.styleable.CustomScaleImageView_heightScale, 1);

		a.recycle();
	}


	@SuppressWarnings("unused")
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

		int childWidthSize = getMeasuredWidth();
		int childHeightSize = getMeasuredHeight();

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize * heightScale / widthScale, MeasureSpec
				.EXACTLY);

		widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
