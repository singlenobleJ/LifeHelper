package com.llj.baselibrary.util;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class ButtonUtils {
	private static long lastClickTime = 0;
	private static long DIFF = 1000;
	private static int lastButtonId = -1;

	/**
	 * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
	 *
	 * @return
	 */
	public static boolean isFastDoubleClick(int buttonId) {
		return isFastDoubleClick(buttonId, DIFF);
	}

	/**
	 * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
	 *
	 * @param diff
	 * @return
	 */
	public static boolean isFastDoubleClick(int buttonId, long diff) {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
			return true;
		}
		lastClickTime = time;
		lastButtonId = buttonId;
		return false;
	}

}