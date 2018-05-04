package com.llj.lifehelper.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 14:32
 *    描述：
 * </pre>
 */
public class BaseDialog extends Dialog{
	public BaseDialog(@NonNull Context context) {
		super(context);
	}

	public BaseDialog(@NonNull Context context, int themeResId) {
		super(context, themeResId);
	}
}
