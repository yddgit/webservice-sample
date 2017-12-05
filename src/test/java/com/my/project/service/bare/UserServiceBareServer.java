package com.my.project.service.bare;

import javax.xml.ws.Endpoint;

/**
 * 发布UserService服务
 * @author yang
 */
public class UserServiceBareServer {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9000/userService", new UserServiceImpl());
	}

}
