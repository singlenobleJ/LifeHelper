package com.llj.baselibrary.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.llj.baselibrary.R;
import com.llj.baselibrary.widget.CustomDividerItemDecoration;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/3/12 10:59
 *    描述：
 * </pre>
 */
public class RvDividerUtils {


	/**
	 * 设置RecyclerView分割线
	 *
	 * @param context  上下文
	 * @param drawable 分割线Drawable样式
	 */
	public static void setRvItemDivider(Context context, RecyclerView recyclerView,
	                                    Drawable drawable) {
		CustomDividerItemDecoration itemDecoration = new CustomDividerItemDecoration(context,
				DividerItemDecoration.VERTICAL);
		itemDecoration.setDrawable(drawable);
		recyclerView.addItemDecoration(itemDecoration);

	}

	/**
	 * 设置RecyclerView分割线(默认)
	 *
	 * @param context 上下文
	 */
	public static void setRvItemDivider(Context context, RecyclerView recyclerView) {
		CustomDividerItemDecoration itemDecoration = new CustomDividerItemDecoration(context,
				DividerItemDecoration.VERTICAL);
		itemDecoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_rv));
		recyclerView.addItemDecoration(itemDecoration);

	}
}
