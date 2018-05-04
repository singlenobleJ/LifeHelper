package com.llj.lifehelper.ui.gank.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.llj.baselibrary.imageloader.ImageLoader;
import com.llj.lifehelper.R;
import com.llj.lifehelper.base.BaseActivity;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 16:28
 *    描述：
 * </pre>
 */
public class PlayVideoActivity extends BaseActivity {
	public static final String VIDEO_URL = "video_url";
	public static final String VIDEO_TITLE = "video_title";
	private String videoUrl;
	private String videoTitle;
	private Toolbar toolBar;

	public static void start(Context context, String videoUrl, String videoTitle) {
		Intent starter = new Intent(context, PlayVideoActivity.class);
		starter.putExtra(VIDEO_URL, videoUrl);
		starter.putExtra(VIDEO_TITLE, videoTitle);
		context.startActivity(starter);
	}

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_play_video;
	}

	@Override
	protected void initView() {
		toolBar = findViewById(R.id.toolbar);
		setSupportActionBar(toolBar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		videoUrl = getIntent().getStringExtra(VIDEO_URL);
		videoTitle = getIntent().getStringExtra(VIDEO_TITLE);
		JZVideoPlayerStandard jzVideoPlayerStandard = findViewById(R.id.video_player);
		jzVideoPlayerStandard.setUp(videoUrl
				, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, videoTitle);
		ImageLoader.loadImage(this, "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640", jzVideoPlayerStandard.thumbImageView);
	}

	@Override
	public void onBackPressed() {
		if (JZVideoPlayer.backPress()) {
			return;
		}
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		JZVideoPlayer.releaseAllVideos();
	}
}
