package com.llj.baselibrary.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class AssetsUtil {

	/**
	 * 从asset路径下读取对应文件转String输出
	 *
	 * @param mContext
	 * @param fileName
	 * @return
	 */
	public static String getJson(Context mContext, String fileName) {
		StringBuilder sb = new StringBuilder();
		AssetManager am = mContext.getAssets();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(am.open(fileName)));
			String line;
			while (null != (line = br.readLine())) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			sb.delete(0, sb.length());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString().trim();
	}
}
