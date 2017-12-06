package com.my.project.service.container;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.my.project.service.container.client.User;
import com.my.project.service.container.client.UserException_Exception;
import com.my.project.service.container.client.UserService;
import com.my.project.service.container.client.UserService_Service;

/**
 * 使用Servlet容器发布WebService后测试接口服务
 * @author yang
 */
public class TestContainerClient {

	private UserService userService;

	@Before
	public void before() {
		try {
			URL wsdlLocation = new URL("http://localhost:8080/webservice-sample/userService?wsdl");
			QName serviceName = new QName("http://service.project.my.com/user", "userService");
			UserService_Service service = new UserService_Service(wsdlLocation, serviceName);
			userService = service.getUserServicePort();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
		User u = new User();
		u.setUsername("Bob");
		u.setPassword("111222");
		u.setNickname("鲍勃");
		try {
			userService.add(u);
			System.out.println("add user success!");
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void login() {
		// input wrong password!
		try {
			userService.login("admin", "111111");
			System.out.println("login success!");
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		}
		// user does not exist!
		try {
			userService.login("xxx", "yyyyyy");
			System.out.println("login success!");
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		}
		// login success!
		try {
			userService.login("Bob", "111222");
			System.out.println("login success!");
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void list() {
		List<User> userList = userService.list();
		for(User u : userList) {
			System.out.println(u.getNickname());
		}
	}

}
