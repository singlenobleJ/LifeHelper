package com.llj.baselibrary.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.PermissionChecker;


/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 10:59
 *    描述：
 * </pre>
 */
public class PermissionUtil {
	public static boolean isHasPermission(Context context, String permission) {
		int targetSdkVersion;
		try {
			final PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			targetSdkVersion = info.applicationInfo.targetSdkVersion;
		} catch (PackageManager.NameNotFoundException e) {
			targetSdkVersion = 22;
			e.printStackTrace();
		}
		boolean result = true;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			if (targetSdkVersion >= Build.VERSION_CODES.M) {
				result = context.checkSelfPermission(permission)
						== PackageManager.PERMISSION_GRANTED;
			} else {
				result = PermissionChecker.checkSelfPermission(context, permission)
						== PermissionChecker.PERMISSION_GRANTED;
			}
		}
		return result;
	}
}
