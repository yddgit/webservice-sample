
package com.my.project.service.container;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.my.project.exception.UserException;
import com.my.project.model.User;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "userService", targetNamespace = "http://service.project.my.com/user")
public interface UserService {


    /**
     * 
     * @param user
     * @throws UserException_Exception
     */
    @WebMethod(action = "http://service.project.my.com/user/add")
    @RequestWrapper(localName = "add", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.AddResponse")
    public void add(
        @WebParam(name = "user", targetNamespace = "")
        User user)
        throws UserException
    ;

    /**
     * 
     * @param username
     * @throws UserException_Exception
     */
    @WebMethod(action = "http://service.project.my.com/user/delete")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.DeleteResponse")
    public void delete(
        @WebParam(name = "username", targetNamespace = "")
        String username)
        throws UserException
    ;

    /**
     * 
     * @return
     *     returns java.util.List<com.my.project.service.container.User>
     */
    @WebMethod(action = "http://service.project.my.com/user/list")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "list", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.List")
    @ResponseWrapper(localName = "listResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.ListResponse")
    public List<User> list();

    /**
     * 
     * @param username
     * @param password
     * @return
     *     returns com.my.project.service.container.User
     * @throws UserException_Exception
     */
    @WebMethod(action = "http://service.project.my.com/user/login")
    @WebResult(name = "user", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.project.my.com/user", className = "com.my.project.service.container.LoginResponse")
    public User login(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password)
        throws UserException
    ;

}
