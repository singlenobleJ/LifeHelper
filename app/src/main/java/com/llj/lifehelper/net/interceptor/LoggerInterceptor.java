package com.llj.lifehelper.net.interceptor;


import com.llj.baselibrary.util.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2017/12/20 14:50
 *    描述：日志拦截器
 * </pre>
 */
public class LoggerInterceptor implements Interceptor {

	private static final Charset UTF8 = Charset.forName("UTF-8");
	public static final String TAG_NET_REQUEST = "net_request_tag";

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		LogUtils.d(TAG_NET_REQUEST,
				"requestURL:-------" + request.url() + "\n" + "requestHeaders:-------{" + request.headers()
						.toString() + "}" + "\n" + "\n" + "requestBody:-------" + getRequestBody(
						request.body()));
		Response response = chain.proceed(request);
		LogUtils.d(TAG_NET_REQUEST,
				"responseMessage:-------" + response.message() + "\n" + "responseHeaders:--------{"
						+ response.headers().toString() + "}" + "\n" + "\n" + "responseBody:------- "
						+ getResponseBody(response.body()));
		return response;
	}


	public String getRequestBody(RequestBody body) {
		Buffer buffer = new Buffer();
		try {
			body.writeTo(buffer);
			Charset charset = UTF8;
			MediaType contentType = body.contentType();
			if (contentType != null) {
				charset = contentType.charset(UTF8);
			}
			return buffer.readString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getResponseBody(ResponseBody responseBody) {
		BufferedSource source = responseBody.source();
		try {
			source.request(Long.MAX_VALUE); // Buffer the entire body.
			Buffer rspButter = source.buffer();
			Charset charset = UTF8;
			String body = "response:" + rspButter.clone().readString(charset);
			return body;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
