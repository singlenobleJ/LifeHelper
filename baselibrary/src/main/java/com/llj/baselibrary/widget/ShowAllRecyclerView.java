package com.llj.baselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:39
 *    描述：
 * </pre>
 */
public class ShowAllRecyclerView extends RecyclerView {


	public ShowAllRecyclerView(Context context) {
		super(context);
	}

	public ShowAllRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public ShowAllRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
