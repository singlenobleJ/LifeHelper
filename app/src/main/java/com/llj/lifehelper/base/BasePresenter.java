package com.llj.lifehelper.base;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/12 13:54
 *    描述：
 * </pre>
 */
public interface BasePresenter<V extends BaseView> {

	void attachView(V view);

	void detachView();
}
