package com.llj.lifehelper.net.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/11 10:48
 *    描述：
 * </pre>
 */
public class ApiServiceHandler implements InvocationHandler {
	private Object mObject;

	public ApiServiceHandler(Object object) {
		mObject = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		method.invoke(mObject, args);
		return null;
	}
}
