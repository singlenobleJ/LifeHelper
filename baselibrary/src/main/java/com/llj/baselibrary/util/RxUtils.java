package com.llj.baselibrary.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/04/10 13:29
 *    描述：
 * </pre>
 */
public class RxUtils {

	/**
	 * 利用Compose简化线程的操作
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
		return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
	}

}
