package com.llj.baselibrary.util;

import android.util.Log;


/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/04/10 13:29
 *    描述：日志工具类
 * </pre>
 */
public class LogUtils {

	/**
	 * 控制log日志的输出
	 */
	public static final boolean IS_LOG = true;

	public static void v(String tag, String msg) {
		if (IS_LOG) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (IS_LOG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (IS_LOG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (IS_LOG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (IS_LOG) {
			Log.e(tag, msg);
		}
	}
}