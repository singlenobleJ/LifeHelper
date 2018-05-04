package com.llj.lifehelper.web;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2017/12/1 10:38
 *    描述：
 * </pre>
 */
public class CustomWebViewClient extends WebViewClient {

  private WebView mWebView;

  public CustomWebViewClient(WebView webView) {
    this.mWebView = webView;
  }

  @Override
  public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
    imgReset();
  }

  @Override
  public boolean shouldOverrideUrlLoading(WebView view, String url) {
    view.loadUrl(url);
    return true;
  }

  //此方法获取里面的img，设置img的高度100%,固定图片不能左右滑动
  private void imgReset() {
    mWebView.loadUrl("javascript:(function(){" +
        "var images = document.getElementsByTagName('img'); " +
        "for(var i=0;i<images.length;i++)  " +
        "{"
        + "var img = images[i];   " +
        " img.style.maxWidth = '100%';img.style.height='auto';" +
        "}" +
        "})()");
  }
}
