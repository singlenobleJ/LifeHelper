package com.llj.lifehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 14:32
 *    描述：
 * </pre>
 */
public abstract class BaseFragment extends Fragment {
	protected View contentView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		contentView = inflater.inflate(provideLayoutId(), container, false);
		initView();
		return contentView;
	}

	protected abstract int provideLayoutId();

	protected abstract void initView();
}
