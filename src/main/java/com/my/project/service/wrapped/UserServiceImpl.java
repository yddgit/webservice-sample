package com.my.project.service.wrapped;

import java.util.List;

import javax.jws.WebService;

import com.my.project.model.User;
import com.my.project.repository.UserRepository;

@WebService(
	targetNamespace = "http://service.project.my.com/user",
	endpointInterface = "com.my.project.service.wrapped.UserService",
	wsdlLocation = "META-INF/wsdl/user-wrapped.wsdl",
	serviceName = "userService",
	portName = "userServicePort")
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
	public List<User> list() {
		return UserRepository.getInstance().list();
	}

	@Override
	public User login(String username, String password) {
		return UserRepository.getInstance().login(username, password);
	}

}
