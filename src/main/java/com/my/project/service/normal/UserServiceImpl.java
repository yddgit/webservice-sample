package com.my.project.service.normal;

import java.util.List;

import javax.jws.WebService;

import com.my.project.exception.UserException;
import com.my.project.model.User;
import com.my.project.repository.UserRepository;

@WebService(
	targetNamespace = "http://service.project.my.com/user",
	endpointInterface = "com.my.project.service.normal.UserService",
	wsdlLocation = "META-INF/wsdl/user.wsdl",
	serviceName = "userService",
	portName = "userServicePort")
public class UserServiceImpl implements UserService {

	@Override
	public void add(User user) throws UserException {
		User u = UserRepository.getInstance().get(user.getUsername());
		if(u != null) {
			throw new UserException(String.format("user %s already exists!", user.getUsername()));
		}
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
	public User login(String username, String password) throws UserException {
		User user = UserRepository.getInstance().get(username);
		if(user == null) {
			throw new UserException("user does not exist!");
		}
		if(!user.getPassword().equals(password)) {
			throw new UserException("input wrong password!");
		}
		return user;
	}

}
