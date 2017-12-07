package com.my.project.service.container;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.ws.WebServiceContext;

import com.my.project.exception.UserException;
import com.my.project.model.User;
import com.my.project.repository.UserRepository;
import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;

/**
 * 将WebService部署在Servlet容器中
 * @author yang
 */
@WebService(
	targetNamespace = "http://service.project.my.com/user",
	endpointInterface = "com.my.project.service.container.UserService",
	wsdlLocation = "WEB-INF/wsdl/user.wsdl",
	serviceName = "userService",
	portName = "userServicePort")
public class UserServiceImpl implements UserService {

	private UserRepository repository = UserRepository.getInstance();

	/** 注入WebServiceContext */
	@Resource
	private WebServiceContext context;

	/**
	 * 验证调用者权限
	 * @throws UserException 权限验证不通过时抛出异常
	 */
	private void authorize() throws UserException {
		try {
			// SOAPHeader中的权限验证信息
			String authorizeHeaderName = "authorize";
			// 是否存在SOAPHeader
			HeaderList headers = (HeaderList) context.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
			if(headers == null || headers.isEmpty()) {
				throw new UserException(String.format("\"%s\" header can not be empty!", authorizeHeaderName));
			}
			// SOAPHeader是否为空结点
			QName name = new QName("http://service.project.my.com/user", authorizeHeaderName);
			Header header = headers.get(name, true);
			if(header == null) {
				throw new UserException(String.format("\"%s\" header can not be empty!", authorizeHeaderName));
			}
			// 验证SOAPHeader里的授权用户信息
			XMLStreamReader reader = header.readHeader();
			User authorizedUser = getAuthorizedUser(reader);
			User user = repository.get(authorizedUser.getUsername());
			if(user == null) {
				throw new UserException("user permission denied!");
			}
			if(!user.getPassword().equals(authorizedUser.getPassword())) {
				throw new UserException("authorized user password error!");
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从Header中解析用户信息
	 * @param reader XMLStreamReader
	 * @return Header中的用户信息
	 */
	private User getAuthorizedUser(XMLStreamReader reader) {
		User user = new User();
		try {
			while(reader.hasNext()) {
				int event = reader.next();
				if(event == XMLEvent.START_ELEMENT) {
					String name = reader.getName().getLocalPart();
					if("username".equals(name)) {
						user.setUsername(reader.getElementText());
					} else if("password".equals(name)) {
						user.setPassword(reader.getElementText());
					} else if("nickname".equals(name)) {
						user.setNickname(reader.getElementText());
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void add(User user) throws UserException {
		authorize(); // 权限检查
		if(repository.exist(user.getUsername())) {
			throw new UserException(String.format("user %s already exists!", user.getUsername()));
		}
		repository.add(user);
	}

	@Override
	public void delete(String username) throws UserException {
		authorize(); // 权限检查
		if(!repository.exist(username)) {
			throw new UserException("user does not exist!");
		}
		repository.delete(username);
	}

	@Override
	public List<User> list() {
		return repository.list();
	}

	@Override
	public User login(String username, String password) throws UserException {
		User user = repository.get(username);
		if(user == null) {
			throw new UserException("user does not exist!");
		}
		if(!user.getPassword().equals(password)) {
			throw new UserException("input wrong password!");
		}
		return user;
	}

}
