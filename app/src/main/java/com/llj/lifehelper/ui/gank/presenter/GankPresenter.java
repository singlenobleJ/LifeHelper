package com.llj.lifehelper.ui.gank.presenter;

import com.llj.baselibrary.util.RxUtils;
import com.llj.lifehelper.net.model.gank.GankBaseModel;
import com.llj.lifehelper.net.observer.BaseObserver;
import com.llj.lifehelper.net.retrofit.RetrofitManager;
import com.llj.lifehelper.ui.gank.contract.GankContract;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 13:21
 *    描述：
 * </pre>
 */
public class GankPresenter implements GankContract.IGankPresenter {
	private GankContract.IGankView view;
	private Disposable disposable;

	@Override
	public void attachView(GankContract.IGankView view) {
		this.view = view;
	}

	@Override
	public void detachView() {
		view = null;
		disposable.dispose();
	}

	@Override
	public void requestGankResource(String category, int count, int page) {
		RetrofitManager.getInstance().getGankApiService()
				.requestGank(category, count, page)
				.compose(RxUtils.rxSchedulerHelper())
				.subscribe(new BaseObserver() {
					@Override
					protected void onSuccess(GankBaseModel data) {
						view.setGankResource(data);
					}

					@Override
					protected void subscribe(Disposable d) {
						disposable = d;
					}
				});
	}

	@Override
	public void requestRefreshResource(String category, int count, int page) {
		RetrofitManager.getInstance().getGankApiService()
				.requestGank(category, count, page)
				.compose(RxUtils.rxSchedulerHelper())
				.subscribe(new BaseObserver() {
					@Override
					protected void onSuccess(GankBaseModel data) {
						view.setRefreshResource(data);
					}

					@Override
					protected void subscribe(Disposable d) {
						disposable = d;
					}
				});
	}

	@Override
	public void requestLoadMore(String category, int count, int page) {
		RetrofitManager.getInstance().getGankApiService()
				.requestGank(category, count, page)
				.compose(RxUtils.rxSchedulerHelper())
				.subscribe(new BaseObserver() {
					@Override
					protected void onSuccess(GankBaseModel data) {
						view.setLoadMoreResource(data);
					}

					@Override
					protected void subscribe(Disposable d) {
						disposable = d;
					}
				});
	}
}
