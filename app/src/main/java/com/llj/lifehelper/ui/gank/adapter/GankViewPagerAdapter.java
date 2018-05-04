package com.llj.lifehelper.ui.gank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.llj.lifehelper.ui.gank.fragment.GankFragmentFactory;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 14:17
 *    描述：
 * </pre>
 */
public class GankViewPagerAdapter extends FragmentStatePagerAdapter {
	public GankViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return GankFragmentFactory.createFragment(position);
	}

	@Override
	public int getCount() {
		return 9;
	}
}
