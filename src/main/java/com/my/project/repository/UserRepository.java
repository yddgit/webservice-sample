package com.my.project.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.my.project.model.User;

/**
 * 用户数据操作类
 * @author yang
 */
public class UserRepository {

	/** 用户列表: username -> User */
	private static Map<String, User> users = new HashMap<String, User>();

	private static UserRepository instance = null;
	private UserRepository() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("123456");
		admin.setNickname("管理员");
		users.put(admin.getUsername(), admin);
	}
	/**
	 * 获取用户数据操作类实例
	 * @return 用户数据操作类实例
	 */
	public static UserRepository getInstance() {
		if(instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}

	/**
	 * 添加用户
	 * @param user 用户
	 */
	public void add(User user) {
		if(!users.containsKey(user.getUsername())) {
			users.put(user.getUsername(), user);
		}
	}

	/**
	 * 删除用户
	 * @param username 用户名
	 */
	public void delete(String username) {
		if(users.containsKey(username)) {
			users.remove(username);
		}
	}

	/**
	 * 获取所有用户
	 * @return 用户列表
	 */
	public List<User> list() {
		return Arrays.asList(users.values().toArray(new User[]{}));
	}

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录成功返回用户信息，否则返回<code>null</code>
	 */
	public User login(String username, String password) {
		User user = users.get(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
