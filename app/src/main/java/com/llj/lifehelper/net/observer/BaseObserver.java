package com.llj.lifehelper.net.observer;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.llj.lifehelper.net.exception.ApiException;
import com.llj.lifehelper.net.model.NetWorkMessage;
import com.llj.lifehelper.net.model.gank.GankBaseModel;

import org.json.JSONException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 12:57
 *    描述：
 * </pre>
 */
public abstract class BaseObserver implements Observer<GankBaseModel> {

	/**
	 * 订阅关系
	 *
	 * @param disposable
	 */
	@Override
	public void onSubscribe(Disposable disposable) {
		subscribe(disposable);
	}

	/**
	 * 请求成功
	 *
	 * @param data
	 */
	@Override
	public void onNext(GankBaseModel data) {
		onSuccess(data);
	}

	/**
	 * 请求出错
	 *
	 * @param e
	 */
	@Override
	public void onError(Throwable e) {
		ApiException apiException = new ApiException();
		//异常信息分类
		if (e instanceof HttpException) {
			HttpException httpException = (HttpException) e;
			int errorCode = httpException.code();
			String errorMessage;
			switch (errorCode) {
				case 401:
					errorMessage = "未授权";
					break;
				case 403:
					errorMessage = "禁止访问";
					break;
				case 404:
					errorMessage = "资源不见了";
					break;
				case 408:
					errorMessage = "请求超时，检查当前网络状态是否良好";
					break;
				case 500:
				case 502:
				case 503:
				case 504:
					errorMessage = "服务器开小差了，请稍后再试吧";
					break;
				default:
					errorMessage = httpException.getMessage();
			}
			//处理异常
			apiException.setErrorCode(errorCode);
			apiException.setDisplayMessage(errorMessage);
			onFailure(apiException);
		} else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
			apiException.setErrorCode(1000);
			apiException.setDisplayMessage("Json数据解析出错");
			onFailure(apiException);
		} else if (e instanceof SocketTimeoutException) {
			apiException.setErrorCode(1000);
			apiException.setDisplayMessage("连接超时，请检查网络状态");
			onFailure(apiException);
		} else {
			apiException.setErrorCode(1000);
			apiException.setDisplayMessage(e.getMessage());
			onFailure(apiException);

		}
	}

	/**
	 * 请求完成
	 */
	@Override
	public void onComplete() {
		Log.d("complete", "onComplete");
	}

	/**
	 * 异常
	 *
	 * @param apiException
	 */
	protected void onFailure(ApiException apiException) {
		Log.d("failure", "请求失败(errorCode=" + apiException.getErrorCode() + ")" + apiException.getDisplayMessage());
	}

	protected abstract void onSuccess(GankBaseModel data);

	protected abstract void subscribe(Disposable d);

}
