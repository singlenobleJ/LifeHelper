package com.llj.baselibrary.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/04/10 13:29
 *    描述：
 * </pre>
 */
public abstract class CustomCountDownTimer {

  /**
   * Millis since epoch when alarm should stop.
   */
  private final long mMillisInFuture;

  /**
   * The interval in millis that the user receives callbacks
   */
  private final long mCountdownInterval;

  private long mStopTimeInFuture;

  /**
   * boolean representing if the timer was cancelled
   */
  private boolean mCancelled = false;

  /**
   * @param millisInFuture The number of millis in the future from the call to {@link #start()}
   * until the countdown is done and {@link #onFinish()} is called.
   * @param countDownInterval The interval along the way to receive {@link #onTick(long)}
   * callbacks.
   */
  public CustomCountDownTimer(long millisInFuture, long countDownInterval) {
    mMillisInFuture = millisInFuture;
    mCountdownInterval = countDownInterval;
  }

  /**
   * Cancel the countdown.
   */
  public synchronized final void cancel() {
    mCancelled = true;
    mHandler.removeMessages(MSG);
  }

  /**
   * Start the countdown.
   */
  public synchronized final CustomCountDownTimer start() {
    mCancelled = false;
    if (mMillisInFuture <= 0) {
      onFinish();
      return this;
    }
    mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
    mHandler.sendMessage(mHandler.obtainMessage(MSG));
    return this;
  }


  /**
   * Callback fired on regular interval.
   *
   * @param millisUntilFinished The amount of time until finished.
   */
  public abstract void onTick(long millisUntilFinished);

  /**
   * Callback fired when the time is up.
   */
  public abstract void onFinish();


  private static final int MSG = 1;


  // handles counting down
  @SuppressLint("HandlerLeak")
  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {

      synchronized (CustomCountDownTimer.this) {
        if (mCancelled) {
          return;
        }

        final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

        if (millisLeft <= 0) {
          onFinish();
        } else {
          // 修改了最后一次提醒时会不执行onTick()的问题
          long lastTickStart = SystemClock.elapsedRealtime();
          onTick(millisLeft);
          if (millisLeft < mCountdownInterval) {
            sendMessageDelayed(obtainMessage(MSG), millisLeft);
          } else {
            // take into account user's onTick taking time to execute
            long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
            // special case: user's onTick took more than interval to
            // complete, skip to next interval
            while (delay < 0) {
              delay += mCountdownInterval;
            }
            sendMessageDelayed(obtainMessage(MSG), delay);
          }
        }
      }
    }
  };
}