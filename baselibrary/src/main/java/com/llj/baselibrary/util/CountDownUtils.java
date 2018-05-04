package com.llj.baselibrary.util;


import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 16:41
 *    描述：
 * </pre>
 */
public class CountDownUtils {

  /**
   * 按钮上启动一个定时器
   *
   * @param tvVerifyCode 发送验证码控件
   * @param defaultString 控件默认显示的字符串
   * @param max 失效时间
   * @param interval 间隔时间
   */
  public static CustomCountDownTimer startTimer(final WeakReference<TextView> tvVerifyCode,
                                                final String defaultString, int max,
                                                int interval) {
    tvVerifyCode.get().setEnabled(false);
    return new CustomCountDownTimer(max * 1000 + 500, interval * 1000) {

      @Override
      public void onTick(long time) {
        if (null == tvVerifyCode.get()) {
          this.cancel();
          return;
        } else {
          tvVerifyCode.get().setText("剩余" + (time / 1000) + "秒");
        }
      }

      @Override
      public void onFinish() {
        if (null == tvVerifyCode.get()) {
          this.cancel();
          return;
        }
        tvVerifyCode.get().setEnabled(true);
        tvVerifyCode.get().setText(defaultString);

      }
    }.start();

  }
}