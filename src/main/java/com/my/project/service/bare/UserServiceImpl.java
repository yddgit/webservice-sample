package com.my.project.service.bare;

import javax.jws.WebService;

import com.my.project.model.User;
import com.my.project.repository.UserRepository;

@WebService(
	targetNamespace = "http://service.project.my.com/user",
	endpointInterface = "com.my.project.service.bare.UserService",
	wsdlLocation = "META-INF/wsdl/user-bare.wsdl",
	serviceName = "userService",
	portName = "userServicePort"
)
public class UserServiceImpl implements UserService {

	@Override
	public void add(User user) {
		UserRepository.getInstance().add(user);
	}

	@Override
	public void delete(String username) {
		UserRepository.getInstance().delete(username);
	}

	@Override
	public UserList list() {
		UserList userList = new UserList();
		userList.user = UserRepository.getInstance().list();
		return userList;
	}

	@Override
	public User login(String username, String password) {
		return UserRepository.getInstance().login(username, password);
	}

}
