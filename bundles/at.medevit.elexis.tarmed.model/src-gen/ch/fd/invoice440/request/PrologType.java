//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.18 at 03:48:09 PM CET 
//


package ch.fd.invoice440.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prologType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prologType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package" type="{http://www.forum-datenaustausch.ch/invoice}softwareType" minOccurs="0"/>
 *         &lt;element name="generator" type="{http://www.forum-datenaustausch.ch/invoice}softwareType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prologType", propOrder = {
    "_package",
    "generator"
})
public class PrologType {

    @XmlElement(name = "package")
    protected SoftwareType _package;
    @XmlElement(required = true)
    protected SoftwareType generator;

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareType }
     *     
     */
    public SoftwareType getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareType }
     *     
     */
    public void setPackage(SoftwareType value) {
        this._package = value;
    }

    /**
     * Gets the value of the generator property.
     * 
     * @return
     *     possible object is
     *     {@link SoftwareType }
     *     
     */
    public SoftwareType getGenerator() {
        return generator;
    }

    /**
     * Sets the value of the generator property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoftwareType }
     *     
     */
    public void setGenerator(SoftwareType value) {
        this.generator = value;
    }

}
