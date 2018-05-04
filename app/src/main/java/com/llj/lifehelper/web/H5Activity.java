package com.llj.lifehelper.web;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.llj.lifehelper.R;
import com.llj.lifehelper.base.BaseActivity;


/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2017/12/4 13:04
 *    描述：H5页面用于展示完整的h5页面
 * </pre>
 */
public class H5Activity extends BaseActivity {

	private WebView mWebView;
	private ProgressBar mProgressbar;
	private Toolbar mToolBar;
	public static final String WEB_URL = "web_url";
	private String url;

	public static void openActivity(Context context, String url) {
		Intent intent = new Intent(context, H5Activity.class);
		intent.putExtra(WEB_URL, url);
		context.startActivity(intent);
	}

	@Override
	protected int provideLayoutId() {
		return R.layout.activity_h5;
	}

	@Override
	protected void initView() {
		url = getIntent().getStringExtra(WEB_URL);
		initToolBar();
		initWebView();//初始化WebView
		initWebSettings();//初始化WebSettings
		initWebViewClient();//初始化WebViewClient
		initWebChromeClient();//初始化WebChromeClient
	}

	private void initToolBar() {
		mToolBar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolBar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	private void initWebView() {
		mWebView = findViewById(R.id.web_view);
		mProgressbar =  findViewById(R.id.progress_bar);
		mWebView.loadUrl(url);
	}

	private void initWebSettings() {
		WebSettings settings = mWebView.getSettings();
		//支持获取手势焦点
		mWebView.requestFocusFromTouch();
		//支持JS
		settings.setJavaScriptEnabled(true);
		//支持插件
		settings.setPluginState(WebSettings.PluginState.ON);
		//设置适应屏幕
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		//支持缩放
		settings.setSupportZoom(false);
		//隐藏原生的缩放控件
		settings.setDisplayZoomControls(false);
		//支持内容重新布局
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		settings.supportMultipleWindows();
		settings.setSupportMultipleWindows(true);
		//设置缓存模式
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		settings.setAppCacheEnabled(true);
		settings.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath());

		//设置可访问文件
		settings.setAllowFileAccess(true);
		//当webview调用requestFocus时为webview设置节点
		settings.setNeedInitialFocus(true);
		//支持自动加载图片
		if (Build.VERSION.SDK_INT >= 19) {
			settings.setLoadsImagesAutomatically(true);
		} else {
			settings.setLoadsImagesAutomatically(false);
		}
		settings.setNeedInitialFocus(true);
		//设置编码格式
		settings.setDefaultTextEncodingName("UTF-8");
	}

	private void initWebViewClient() {
		mWebView.setWebViewClient(new WebViewClient() {

			//页面开始加载时
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				mProgressbar.setVisibility(View.VISIBLE);
			}


			//页面完成加载时
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				mProgressbar.setVisibility(View.GONE);
			}

			//是否在WebView内加载新页面
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
				view.loadUrl(request.toString());
				return true;
			}

			//网络错误时回调的方法
			@Override
			public void onReceivedError(WebView view, WebResourceRequest request,
			                            WebResourceError error) {
				super.onReceivedError(view, request, error);
			}

			@TargetApi(Build.VERSION_CODES.M)
			@Override
			public void onReceivedHttpError(WebView view, WebResourceRequest request,
			                                WebResourceResponse errorResponse) {
				super.onReceivedHttpError(view, request, errorResponse);
			}
		});
	}

	private void initWebChromeClient() {

		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				setTitle(title);
			}

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
			}
		});
	}

	/**
	 * 设置Toolbar标题
	 */
	private void setTitle(final String title) {
		if (mToolBar != null) {
			mToolBar.post(new Runnable() {
				@Override
				public void run() {
					mToolBar.setTitle(TextUtils.isEmpty(title) ? "加载中...." : title);
				}
			});
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是回退键且历史记录里还有页面
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		if (mWebView != null) {
			mWebView.clearHistory();
			ViewGroup viewGroup = (ViewGroup) mWebView.getParent();
			viewGroup.removeView(mWebView);
			mWebView.destroy();
			mWebView = null;
		}
		super.onDestroy();
	}
}