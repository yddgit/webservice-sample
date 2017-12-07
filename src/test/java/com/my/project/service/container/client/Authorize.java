
package com.my.project.service.container.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for authorize complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authorize">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorizedUser" type="{http://service.project.my.com/user}user"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authorize", propOrder = {
    "authorizedUser"
})
public class Authorize {

    @XmlElement(required = true)
    protected User authorizedUser;

    /**
     * Gets the value of the authorizedUser property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getAuthorizedUser() {
        return authorizedUser;
    }

    /**
     * Sets the value of the authorizedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setAuthorizedUser(User value) {
        this.authorizedUser = value;
    }

}
