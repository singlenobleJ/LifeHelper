package com.llj.baselibrary.util;

import com.google.gson.Gson;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class GsonUtils {

	private static class GsonHolder {
		private static final Gson INSTANCE = new Gson();
	}

	public static Gson getInstance() {
		return GsonHolder.INSTANCE;
	}
}
