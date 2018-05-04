package com.llj.lifehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 14:32
 *    描述：
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(provideLayoutId());
		initView();
	}

	protected abstract int provideLayoutId();

	protected abstract void initView();
}
