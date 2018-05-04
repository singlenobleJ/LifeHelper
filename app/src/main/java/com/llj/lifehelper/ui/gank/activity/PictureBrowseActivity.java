package com.llj.lifehelper.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;
import com.llj.baselibrary.imageloader.ImageLoader;
import com.llj.lifehelper.R;
import com.llj.lifehelper.base.BaseActivity;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 15:48
 *    描述：
 * </pre>
 */
public class PictureBrowseActivity extends BaseActivity {
	public static final String IMAGE_URL = "image_url";
	private PhotoView photoView;
	private String imageUrl;
	private Toolbar toolbar;

	public static void start(Context context, String imageUrl) {
		Intent starter = new Intent(context, PictureBrowseActivity.class);
		starter.putExtra(IMAGE_URL, imageUrl);
		context.startActivity(starter);
	}

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_picture_browse;
	}


	@Override
	protected void initView() {
		photoView = findViewById(R.id.photo_view);
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		imageUrl = getIntent().getStringExtra(IMAGE_URL);
		ImageLoader.loadImage(this, imageUrl, photoView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
