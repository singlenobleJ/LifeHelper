package com.llj.baselibrary.global;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.llj.baselibrary.util.AppActivityManager;
import com.llj.baselibrary.util.Utils;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 17:00
 *    描述：
 * </pre>
 */
public class BaseApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Utils.init(this);
		registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
			@Override
			public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
				AppActivityManager.getInstance().setCurrentActivity(activity);
			}

			@Override
			public void onActivityStarted(Activity activity) {

			}

			@Override
			public void onActivityResumed(Activity activity) {

			}

			@Override
			public void onActivityPaused(Activity activity) {

			}

			@Override
			public void onActivityStopped(Activity activity) {

			}

			@Override
			public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

			}

			@Override
			public void onActivityDestroyed(Activity activity) {

			}
		});
	}

}
