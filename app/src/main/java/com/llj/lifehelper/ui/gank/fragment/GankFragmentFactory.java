package com.llj.lifehelper.ui.gank.fragment;

import com.llj.lifehelper.base.BaseFragment;
import com.llj.lifehelper.ui.gank.fragment.AllFragment;
import com.llj.lifehelper.ui.gank.fragment.AndroidFragment;
import com.llj.lifehelper.ui.gank.fragment.ExpandFragment;
import com.llj.lifehelper.ui.gank.fragment.IOSFragment;
import com.llj.lifehelper.ui.gank.fragment.VideoFragment;
import com.llj.lifehelper.ui.gank.fragment.WebFragment;
import com.llj.lifehelper.ui.gank.fragment.WelfareFragment;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 14:18
 *    描述：
 * </pre>
 */
public class GankFragmentFactory {
	public static BaseFragment createFragment(int position) {
		BaseFragment fragment;
		switch (position) {
			case 0:
				fragment = new AndroidFragment();
				break;
			case 1:
				fragment = new WelfareFragment();
				break;
			case 2:
				fragment = new IOSFragment();
				break;
			case 3:
				fragment = new ExpandFragment();
				break;
			case 4:
				fragment = new WebFragment();
				break;
			case 5:
				fragment = new VideoFragment();
				break;
			case 6:
				fragment = new RecommendFragment();
				break;
			case 7:
				fragment = new AppFragment();
				break;
			case 8:
				fragment = new AllFragment();
				break;
			default:
				throw new IllegalArgumentException("position is incorrect ...");
		}
		return fragment;
	}
}
