package com.my.project.service.container;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.soap.MTOMFeature;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.my.project.service.container.client.Authorize;
import com.my.project.service.container.client.ObjectFactory;
import com.my.project.service.container.client.User;
import com.my.project.service.container.client.UserException_Exception;
import com.my.project.service.container.client.UserService;
import com.my.project.service.container.client.UserService_Service;
import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

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
			userService = service.getUserServicePort(new MTOMFeature());
			// 添加SOAPHeader信息
			User authorizedUser = new User();
			authorizedUser.setUsername("admin");
			authorizedUser.setPassword("123456");
			authorizedUser.setNickname("超级管理员");
			addAuthorizeHeader(userService, authorizedUser);
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
	public void delete() {
		try {
			userService.delete("Bob");
			System.out.println("delete user success!");
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

	/**
	 * 测试二进制数据上传, 需要提前在客户端准备upload-client.jpg文件
	 */
	@Test
	public void upload() {
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			inputStream = TestContainerClient.class.getClassLoader().getResourceAsStream("upload-client.jpg");
			byte[] buffer = new byte[1024];
			int length;
			while((length = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
			userService.upload(outputStream.toByteArray());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
				if(outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试二进制数据下载, 需要提前在服务端准备download-server.jpg文件
	 */
	@Test
	public void download() {
		String userHome = System.getProperty("user.home") + File.separator;
		String fileName = userHome + "download-client.jpg";
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
			outputStream.write(userService.download());
			outputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 添加SOAPHeader信息
	 * @param userService 服务接口
	 * @param authorizedUser authorizedUser
	 */
	private void addAuthorizeHeader(UserService userService, User authorizedUser) {
		try {
			// 创建SOAPHeader对象
			ObjectFactory factory = new ObjectFactory();
			Authorize authorize = factory.createAuthorize();
			authorize.setAuthorizedUser(authorizedUser);
			// 将Header对象转换为Document
			JAXBElement<Authorize> authorizeElement = factory.createAuthorize(authorize);
			JAXBContext ctx = JAXBContext.newInstance(Authorize.class);
			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
			marshaller.marshal(authorizeElement, doc);
			// 通过Headers.create()添加Header信息
			WSBindingProvider provider = (WSBindingProvider)userService;
			provider.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
