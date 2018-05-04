package com.llj.baselibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/04/10 13:29
 *    描述：
 * </pre>
 */
public class SpUtils {

	private SharedPreferences sp;
	private static volatile SpUtils instance;
	private static final String SP_FILE_NAME = "life_helper_sp";

	private SpUtils(Context context) {
		sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	public static SpUtils getInstance(Context context) {
		if (instance == null) {
			synchronized (SpUtils.class) {
				if (instance == null) {
					instance = new SpUtils(context.getApplicationContext());
				}
			}
		}
		return instance;
	}

	public SpUtils putInt(String key, int value) {
		sp.edit().putInt(key, value).apply();
		return this;
	}

	public int getInt(String key, int deValue) {
		return sp.getInt(key, deValue);
	}

	public SpUtils putLong(String key, long value) {
		sp.edit().putLong(key, value).apply();
		return this;
	}

	public long getLong(String key, long deValue) {
		return sp.getLong(key, deValue);
	}

	public SpUtils putFloat(String key, float value) {
		sp.edit().putFloat(key, value).apply();
		return this;
	}

	public Float getFloat(String key, float dValue) {
		return sp.getFloat(key, dValue);
	}

	public SpUtils putBoolean(String key, boolean value) {
		sp.edit().putBoolean(key, value).apply();
		return this;
	}

	public Boolean getBoolean(String key, boolean deValue) {
		return sp.getBoolean(key, deValue);
	}

	public SpUtils putString(String key, String value) {
		sp.edit().putString(key, value).apply();
		return this;
	}

	public String getString(String key, String deValue) {
		return sp.getString(key, deValue);
	}

	/**
	 * 移除指定的key
	 *
	 * @param key
	 * @return
	 */
	public boolean remove(String key) {
		if (isExist(key)) {
			SharedPreferences.Editor editor = sp.edit();
			editor.remove(key);
			return editor.commit();
		}
		return false;
	}

	/**
	 * 判断某个key是否存在
	 *
	 * @param key
	 * @return
	 */
	public boolean isExist(String key) {
		return sp.contains(key);
	}

	/**
	 * 清除所有的数据
	 *
	 * @return
	 */
	public boolean clearAll() {
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		return editor.commit();
	}

	/**
	 * 获取所有的键值对
	 *
	 * @return
	 */
	public Map<String, ?> getAll() {
		return sp.getAll();

	}


}