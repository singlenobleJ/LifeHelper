package com.llj.lifehelper.db;

import com.llj.lifehelper.global.App;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/11 17:06
 *    描述：
 * </pre>
 */
public class UserDaoManager {
	/**
	 * 插入数据
	 *
	 * @param user
	 * @return id
	 */
	public static long insert(User user) {
		UserDao userDao = App.getApp().getDaoSession().getUserDao();
		return userDao.insert(user);
	}

	/**
	 * 删除数据
	 *
	 * @param id
	 */
	public void delete(long id) {
		UserDao userDao = App.getApp().getDaoSession().getUserDao();
		userDao.deleteByKey(id);
	}

	/**
	 * 更新数据
	 *
	 * @param user
	 */
	public void update(User user) {
		UserDao userDao = App.getApp().getDaoSession().getUserDao();
		userDao.update(user);
	}

	/**
	 * 查询数据
	 *
	 * @return
	 */
	public List<User> query() {
		UserDao userDao = App.getApp().getDaoSession().getUserDao();
		Query<User> userQuery = userDao.queryBuilder().build();
		return userQuery.list();
	}
}
