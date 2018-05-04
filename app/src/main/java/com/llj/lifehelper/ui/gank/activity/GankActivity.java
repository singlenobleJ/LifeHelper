package com.llj.lifehelper.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.llj.lifehelper.R;
import com.llj.lifehelper.base.BaseActivity;
import com.llj.lifehelper.ui.gank.adapter.GankViewPagerAdapter;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 12:59
 *    描述：
 * </pre>
 */
public class GankActivity extends BaseActivity {


	private TabLayout tabLayout;
	private ViewPager vpFragment;

	public static void start(Context context) {
		Intent starter = new Intent(context, GankActivity.class);
		context.startActivity(starter);
	}

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_gank;
	}

	@Override
	protected void initView() {
		tabLayout = findViewById(R.id.tab_layout);
		vpFragment = findViewById(R.id.vp_fragment);
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayout.addTab(tabLayout.newTab().setText("Android"));
		tabLayout.addTab(tabLayout.newTab().setText("福利"));
		tabLayout.addTab(tabLayout.newTab().setText("iOS"));
		tabLayout.addTab(tabLayout.newTab().setText("拓展资源"));
		tabLayout.addTab(tabLayout.newTab().setText("前端"));
		tabLayout.addTab(tabLayout.newTab().setText("休息视频"));
		tabLayout.addTab(tabLayout.newTab().setText("瞎推荐"));
		tabLayout.addTab(tabLayout.newTab().setText("App"));
		tabLayout.addTab(tabLayout.newTab().setText("全部"));
		vpFragment.setAdapter(new GankViewPagerAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(vpFragment);
		tabLayout.getTabAt(0).setText("Android");
		tabLayout.getTabAt(1).setText("福利");
		tabLayout.getTabAt(2).setText("iOS");
		tabLayout.getTabAt(3).setText("拓展资源");
		tabLayout.getTabAt(4).setText("前端");
		tabLayout.getTabAt(5).setText("休息视频");
		tabLayout.getTabAt(6).setText("瞎推荐");
		tabLayout.getTabAt(7).setText("App");
		tabLayout.getTabAt(8).setText("全部");
	}

}
