package com.llj.lifehelper.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/11 16:55
 *    描述：
 * </pre>
 */
@Entity
public class User {
	@Id(autoincrement = true)
	private long id;
	@Unique
	private String name;
	private String password;

	@Generated(hash = 179890708)
	public User(long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Generated(hash = 586692638)
	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
