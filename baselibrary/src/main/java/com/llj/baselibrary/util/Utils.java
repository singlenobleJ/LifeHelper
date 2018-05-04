package com.llj.baselibrary.util;

import android.content.Context;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class Utils {

  private static Context context;

  private Utils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 初始化工具类
   *
   * @param context 上下文
   */
  public static void init(Context context) {
    Utils.context = context.getApplicationContext();
  }

  /**
   * 获取ApplicationContext
   *
   * @return ApplicationContext
   */
  public static Context getContext() {
    if (context != null) {
      return context;
    }
    throw new NullPointerException("u should init first");
  }
}
