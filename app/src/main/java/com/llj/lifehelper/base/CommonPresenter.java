package com.llj.lifehelper.base;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/12 14:06
 *    描述：
 * </pre>
 */
public class CommonPresenter<V extends BaseView> implements BasePresenter<V> {
	protected V mView;

	@Override
	public void attachView(V view) {
		mView = view;
	}

	@Override
	public void detachView() {
		mView = null;
	}
}
