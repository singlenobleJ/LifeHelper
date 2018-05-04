package com.llj.lifehelper.net.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/16 14:10
 *    描述：
 * </pre>
 */
public class SSLSocketManager {
	/**
	 * OkHttp设置支持Https默认配置
	 *
	 * @param builder
	 */
	public static void setSSLSocketFactory(OkHttpClient.Builder builder) {
		try {
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
					TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init((KeyStore) null);
			TrustManager[] trustManagers = trustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).getTrustManagers();
			if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
				throw new IllegalStateException("Unexpected default trust managers:"
						+ Arrays.toString(trustManagers));
			}
			X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[]{trustManager}, null);
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			builder.sslSocketFactory(sslSocketFactory, trustManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取TrustManager[]
	 *
	 * @return
	 */
	private static TrustManager[] getTrustManager() {
		TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		}};
		return trustManagers;
	}

	/**
	 * 获取HostnameVerifier
	 *
	 * @return
	 */
	public static void setHostNameVerifier(OkHttpClient.Builder builder) {
		HostnameVerifier hostnameVerifier = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		builder.hostnameVerifier(hostnameVerifier);
	}

	/**
	 * 设置指定的证书访问自签名的网站
	 * 首先把我们下载的服务端证书文件放到assets文件夹下，其实你可以随便放哪，反正能读取到就行。
	 *
	 * @param certificates
	 */
	public static void setCertificates(OkHttpClient.Builder builder, InputStream... certificates) {
		try {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(null);
			int index = 0;
			for (InputStream certificate : certificates) {
				String certificateAlias = Integer.toString(index++);
				keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
				try {
					if (certificate != null) {
						certificate.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
					TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(keyStore);
			TrustManager[] trustManagers = trustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).getTrustManagers();
			if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
				throw new IllegalStateException("Unexpected default trust managers:"
						+ Arrays.toString(trustManagers));
			}
			X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			builder.sslSocketFactory(sslSocketFactory, trustManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
