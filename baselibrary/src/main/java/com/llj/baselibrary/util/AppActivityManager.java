package com.llj.baselibrary.util;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class AppActivityManager {

  private static AppActivityManager sInstance = new AppActivityManager();
  private WeakReference<Activity> sCurrentActivityWeakRef;


  private AppActivityManager() {

  }

  public static AppActivityManager getInstance() {
    return sInstance;
  }

  public Activity getCurrentActivity() {
    Activity currentActivity = null;
    if (sCurrentActivityWeakRef != null) {
      currentActivity = sCurrentActivityWeakRef.get();
    }
    return currentActivity;
  }

  public void setCurrentActivity(Activity activity) {
    sCurrentActivityWeakRef = new WeakReference<>(activity);
  }
}