package com.llj.lifehelper.ui.gank.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llj.baselibrary.util.ToastUtils;
import com.llj.lifehelper.R;
import com.llj.lifehelper.base.BaseFragment;
import com.llj.lifehelper.net.model.gank.GankBaseModel;
import com.llj.lifehelper.net.model.gank.GankModel;
import com.llj.lifehelper.ui.gank.activity.PictureBrowseActivity;
import com.llj.lifehelper.ui.gank.adapter.WelfareAdapter;
import com.llj.lifehelper.ui.gank.contract.GankContract;
import com.llj.lifehelper.ui.gank.presenter.GankPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 13:01
 *    描述：
 * </pre>
 */
public class WelfareFragment extends BaseFragment implements GankContract.IGankView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
	public static final int PAGE_SIZE = 20;
	private RecyclerView rvAndroid;
	private SwipeRefreshLayout refreshLayout;
	private GankContract.IGankPresenter gankPresenter;
	private WelfareAdapter adapter;
	private int page = 1;
	private List<GankModel> gankList = new ArrayList<>();

	@Override
	protected int provideLayoutId() {
		return R.layout.fragment_gank_common;
	}

	@Override
	protected void initView() {
		rvAndroid = contentView.findViewById(R.id.rv_gank);
		refreshLayout = contentView.findViewById(R.id.refresh_layout);
		refreshLayout.setOnRefreshListener(this);
		rvAndroid.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		gankPresenter = new GankPresenter();
		gankPresenter.attachView(this);
		gankPresenter.requestGankResource("福利", PAGE_SIZE, 1);
	}


	@Override
	public void showLoading(boolean isShow) {

	}

	@Override
	public void showMessage(String message) {
		ToastUtils.showLongToastSafe(message);
	}

	@Override
	public void setGankResource(GankBaseModel data) {
		if (data.getResults() != null && !data.getResults().isEmpty()) {
			gankList.clear();
			gankList.addAll(data.getResults());
		}
		initAdapter();

	}

	@Override
	public void setRefreshResource(GankBaseModel data) {
		//刷新成功关闭刷新进度条，启用上拉加载
		refreshLayout.setRefreshing(false);
		if (adapter != null) {
			adapter.setEnableLoadMore(true);
		}

		gankList.clear();
		gankList.addAll(data.getResults());
		if (adapter != null) {
			adapter.setNewData(gankList);
		} else {
			initAdapter();
		}
	}

	private void initAdapter() {
		adapter = new WelfareAdapter(gankList);
		adapter.setOnLoadMoreListener(this, rvAndroid);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				PictureBrowseActivity.start(getActivity(), gankList.get(position).getUrl());
			}
		});
		rvAndroid.setAdapter(adapter);
	}

	@Override
	public void setLoadMoreResource(GankBaseModel data) {
		//加载更多完成启用下拉刷新
		refreshLayout.setEnabled(true);
		adapter.addData(data.getResults());
		adapter.loadMoreComplete();
	}

	@Override
	public void onRefresh() {
		if (adapter != null) {
			adapter.setEnableLoadMore(false);
		}
		gankPresenter.requestRefreshResource("福利", PAGE_SIZE, 1);
	}

	@Override
	public void onLoadMoreRequested() {
		//禁用下拉刷新
		refreshLayout.setRefreshing(false);
		refreshLayout.setEnabled(false);
		if (adapter.getData().size() < PAGE_SIZE) {
			//小于一页则加载结束
			adapter.loadMoreEnd(true);
			refreshLayout.setEnabled(true);
		} else {
			page++;
			gankPresenter.requestLoadMore("福利", PAGE_SIZE, page);
		}
	}
}
