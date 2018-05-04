package com.llj.lifehelper.net.proxy;

import java.lang.reflect.Proxy;

import retrofit2.Retrofit;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/11 11:00
 *    描述：
 * </pre>
 */
public class ApiServiceProxy {

	public static <T> T getProxy(Retrofit retrofit, Class<T> tClass) {
		T t = retrofit.create(tClass);
		return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new ApiServiceHandler(t));
	}
}
