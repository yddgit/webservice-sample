
package com.my.project.service.wrapped.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "userService", targetNamespace = "http://service.project.my.com/user")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param user
     */
    @WebMethod(action = "http://service.project.my.com/user/add")
    @RequestWrapper(localName = "add", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.AddResponse")
    public void add(
        @WebParam(name = "user", targetNamespace = "")
        User user);

    /**
     * 
     * @param username
     */
    @WebMethod(action = "http://service.project.my.com/user/delete")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.DeleteResponse")
    public void delete(
        @WebParam(name = "username", targetNamespace = "")
        String username);

    /**
     * 
     * @return
     *     returns java.util.List<com.my.project.service.wrapped.client.User>
     */
    @WebMethod(action = "http://service.project.my.com/user/list")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "list", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.List")
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.ListResponse")
    public List<User> list();

    /**
     * 
     * @param username
     * @param password
     * @return
     *     returns com.my.project.service.wrapped.client.User
     */
    @WebMethod(action = "http://service.project.my.com/user/login")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.wrapped.client.LoginResponse")
    public User login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password);

}
