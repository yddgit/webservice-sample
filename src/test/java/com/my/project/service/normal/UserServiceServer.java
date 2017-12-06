package com.my.project.service.normal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;

/**
 * 发布UserService服务
 * @author yang
 */
public class UserServiceServer {

	public static void main(String[] args) {
		try {
			// 创建服务发布结点
			Endpoint endpoint = Endpoint.create(new UserServiceImpl());

			// 添加user.xsd
			List<Source> metadata = new ArrayList<>();
			String xsdLocation = "META-INF/wsdl/user.xsd";
			URL xsdURL = UserServiceServer.class.getClassLoader().getResource(xsdLocation);
			if (xsdURL == null) {
				System.out.println(xsdLocation + " does not exist");
				return;
			}
			Source xsdSource = new StreamSource(xsdURL.openStream());
			xsdSource.setSystemId(xsdURL.toExternalForm());
			metadata.add(xsdSource);
			endpoint.setMetadata(metadata);

			// 发布服务
			endpoint.publish("http://localhost:9000/userService");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
