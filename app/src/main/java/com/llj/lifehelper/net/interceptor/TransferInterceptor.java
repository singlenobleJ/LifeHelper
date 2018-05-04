package com.llj.lifehelper.net.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2017/12/20 14:50
 *    描述：请求头拦截器
 * </pre>
 */

public class TransferInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Request newRequest;
		Request.Builder requestBuilder = request.newBuilder().addHeader("Accept-Encoding", "gzip,deflate")
				.addHeader("Charset", "UTF-8");

		newRequest = requestBuilder.build();

		return chain.proceed(newRequest);
	}
}
