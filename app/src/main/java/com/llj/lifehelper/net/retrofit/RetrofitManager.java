package com.llj.lifehelper.net.retrofit;


import com.llj.lifehelper.constant.Constants;
import com.llj.lifehelper.net.gsonconverter.CustomGsonConverterFactory;
import com.llj.lifehelper.net.service.GankApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 11:53
 *    描述：
 * </pre>
 */
public class RetrofitManager {
	private static volatile RetrofitManager sRetrofitManager;
	private GankApiService gankApiService;

	private RetrofitManager() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.connectTimeout(Constants.Net.CONNECT_TIME_OUT, TimeUnit.SECONDS)
				.readTimeout(Constants.Net.READ_TIME_OUT, TimeUnit.SECONDS)
				.writeTimeout(Constants.Net.WRITE_TIME_OUT, TimeUnit.SECONDS);
		//设置默认SSLSocketFactory信任所有证书
		//SSLSocketManager.setSSLSocketFactory(builder);
		//设置SSLSocketFactory信任指定证书，传入本地证书的输入流
		//SSLSocketManager.setCertificates(builder,null);
		//设置HostnameVerifier
		//SSLSocketManager.setHostNameVerifier(builder);
		//设置Cookie持久化处理
		//builder.cookieJar(new CookieManager(new PersistentHttpCookieStore(App.getApp().getApplicationContext())));
		OkHttpClient okHttpClient = builder.build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(Constants.Net.GANK_BASE_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(CustomGsonConverterFactory.create())
				.client(okHttpClient)
				.build();
		//动态代理
		//gankApiService = ApiServiceProxy.getProxy(retrofit, GankApiService.class);
		gankApiService = retrofit.create(GankApiService.class);
	}

	public static RetrofitManager getInstance() {
		if (sRetrofitManager == null) {
			synchronized (RetrofitManager.class) {
				if (sRetrofitManager == null) {
					sRetrofitManager = new RetrofitManager();
				}
			}
		}
		return sRetrofitManager;
	}

	public GankApiService getGankApiService() {
		return gankApiService;
	}
}
