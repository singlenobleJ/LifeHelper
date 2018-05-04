package com.llj.lifehelper.ui.gank.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llj.baselibrary.imageloader.ImageLoader;
import com.llj.baselibrary.util.Utils;
import com.llj.lifehelper.R;
import com.llj.lifehelper.net.model.gank.GankModel;

import java.util.List;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 14:29
 *    描述：
 * </pre>
 */
public class WelfareAdapter extends BaseQuickAdapter<GankModel, BaseViewHolder> {
	public WelfareAdapter(@Nullable List<GankModel> data) {
		super(R.layout.item_fragment_gank_welfare, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, GankModel item) {
		helper.setText(R.id.tv_desc, item.getDesc());
		ImageLoader.loadImage(Utils.getContext(), item.getUrl(), helper.getView(R.id.iv_image));
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position) {
		super.onBindViewHolder(holder, position);
	}
}
