package com.my.project.service.rpc;

import javax.xml.ws.Endpoint;

/**
 * 发布UserService服务
 * @author yang
 */
public class UserServiceRpcServer {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9000/userService", new UserServiceImpl());
	}

}
