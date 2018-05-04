package com.llj.lifehelper.ui.gank.contract;

import com.llj.lifehelper.base.BasePresenter;
import com.llj.lifehelper.base.BaseView;
import com.llj.lifehelper.net.model.gank.GankBaseModel;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 13:19
 *    描述：
 * </pre>
 */
public class GankContract {
	public interface IGankView extends BaseView {
		void setGankResource(GankBaseModel data);

		void setRefreshResource(GankBaseModel data);

		void setLoadMoreResource(GankBaseModel data);

	}

	public interface IGankPresenter extends BasePresenter<IGankView> {

		void requestGankResource(String category, int count, int page);

		void requestRefreshResource(String category, int count, int page);

		void requestLoadMore(String category, int count, int page);

	}
}
