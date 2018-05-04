package com.llj.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:40
 *    描述：
 * </pre>
 */
public class ShowAllGridView extends GridView {
	public ShowAllGridView(Context context) {
		super(context);
	}

	public ShowAllGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ShowAllGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
