package com.llj.lifehelper.global;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.llj.baselibrary.global.BaseApp;
import com.llj.lifehelper.db.DaoMaster;
import com.llj.lifehelper.db.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 14:33
 *    描述：
 * </pre>
 */
public class App extends BaseApp {

	public static final boolean ENCRYPTED = false;
	public static int SCREEN_W;
	public static int SCREEN_H;
	private static App sApp;
	private DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();
		sApp = this;
		setupScreenInfo();
		setupDatabase();
	}

	public static App getApp() {
		return sApp;
	}

	public void setupDatabase() {
		DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "user-db-encrypted" : "user-db", null);
		Database db = ENCRYPTED ? devOpenHelper.getEncryptedWritableDb("super-secret") : devOpenHelper.getWritableDb();
		daoSession = new DaoMaster(db).newSession();
	}

	public void setupScreenInfo() {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		SCREEN_W = dm.widthPixels;
		SCREEN_H = dm.heightPixels;
	}


	public DaoSession getDaoSession() {
		return daoSession;
	}

}
