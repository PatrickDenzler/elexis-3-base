//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2020.05.20 um 02:13:04 PM CEST 
//


package ch.fd.invoice450.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für employeeType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="employeeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="familyname" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35"/>
 *         &lt;element name="givenname" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" minOccurs="0"/>
 *         &lt;element name="telecom" type="{http://www.forum-datenaustausch.ch/invoice}telecomAddressType" minOccurs="0"/>
 *         &lt;element name="online" type="{http://www.forum-datenaustausch.ch/invoice}onlineAddressType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="salutation" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" />
 *       &lt;attribute name="title" type="{http://www.forum-datenaustausch.ch/invoice}stringType1_35" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeType", propOrder = {
    "familyname",
    "givenname",
    "telecom",
    "online"
})
public class EmployeeType {

    @XmlElement(required = true)
    protected String familyname;
    protected String givenname;
    protected TelecomAddressType telecom;
    protected OnlineAddressType online;
    @XmlAttribute(name = "salutation")
    protected String salutation;
    @XmlAttribute(name = "title")
    protected String title;

    /**
     * Ruft den Wert der familyname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyname() {
        return familyname;
    }

    /**
     * Legt den Wert der familyname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyname(String value) {
        this.familyname = value;
    }

    /**
     * Ruft den Wert der givenname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenname() {
        return givenname;
    }

    /**
     * Legt den Wert der givenname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenname(String value) {
        this.givenname = value;
    }

    /**
     * Ruft den Wert der telecom-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TelecomAddressType }
     *     
     */
    public TelecomAddressType getTelecom() {
        return telecom;
    }

    /**
     * Legt den Wert der telecom-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomAddressType }
     *     
     */
    public void setTelecom(TelecomAddressType value) {
        this.telecom = value;
    }

    /**
     * Ruft den Wert der online-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link OnlineAddressType }
     *     
     */
    public OnlineAddressType getOnline() {
        return online;
    }

    /**
     * Legt den Wert der online-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineAddressType }
     *     
     */
    public void setOnline(OnlineAddressType value) {
        this.online = value;
    }

    /**
     * Ruft den Wert der salutation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * Legt den Wert der salutation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalutation(String value) {
        this.salutation = value;
    }

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
