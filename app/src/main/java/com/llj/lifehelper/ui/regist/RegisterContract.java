package com.llj.lifehelper.ui.regist;

import com.llj.lifehelper.base.BasePresenter;
import com.llj.lifehelper.base.BaseView;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/12 14:30
 *    描述：
 * </pre>
 */
public interface RegisterContract {
	interface IRegisterView extends BaseView {

		void registerSuccess();

		void registerFailure();
	}

	interface IRegisterPresenter extends BasePresenter<IRegisterView> {
		void register();
	}
}
