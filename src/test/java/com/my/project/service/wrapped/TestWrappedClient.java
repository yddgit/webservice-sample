package com.my.project.service.wrapped;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.my.project.service.wrapped.client.User;
import com.my.project.service.wrapped.client.UserService;
import com.my.project.service.wrapped.client.UserService_Service;

public class TestWrappedClient {

	private UserService userService;

	@Before
	public void before() {
		try {
			URL wsdlLocation = new URL("http://localhost:9000/userService?wsdl");
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
		userService.add(u);
		System.out.println("add user success!");
	}

	@Test
	public void list() {
		List<User> userList = userService.list();
		for(User u : userList) {
			System.out.println(u.getNickname());
		}
	}

}
