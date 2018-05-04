package com.llj.baselibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/3/12 10:59
 *    描述：
 * </pre>
 */
public class NetUtils {


	/**
	 * 判断是否有网络连接。
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isAvailable();
		}
		return false;
	}

	/**
	 * 判断是否处于WiFi状态 getActiveNetworkInfo 是可用的网络，不一定是连接的，getNetworkInfo 是连接的。
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		//处于WiFi连接状态
		return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
	}

	/**
	 * 判断是否处于WiFi状态 getActiveNetworkInfo 是可用的网络，不一定是连接的，getNetworkInfo 是连接的。
	 *
	 * @param context
	 * @return
	 */
	public static boolean isMobile(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		//处于WiFi连接状态
		return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
	}

}
