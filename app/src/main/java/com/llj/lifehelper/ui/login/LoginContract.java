package com.llj.lifehelper.ui.login;

import com.llj.lifehelper.base.BasePresenter;
import com.llj.lifehelper.base.BaseView;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/12 14:11
 *    描述：
 * </pre>
 */
public interface LoginContract {

	interface ILoginView extends BaseView {

		void loginSuccess();

		void loginFailure();
	}

	interface ILoginPresenter extends BasePresenter<ILoginView> {
		void login();
	}
}
