package com.my.project.service.rpc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.my.project.service.rpc.client.User;
import com.my.project.service.rpc.client.UserList;
import com.my.project.service.rpc.client.UserService;
import com.my.project.service.rpc.client.UserService_Service;

public class TestRpcClient {

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
		UserList userList = userService.list();
		for(User u : userList.getUser()) {
			System.out.println(u.getNickname());
		}
	}

}
