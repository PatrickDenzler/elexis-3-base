package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for IVL_TS complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="IVL_TS">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:v3}SXCM_TS">
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element name="low" type="{urn:hl7-org:v3}IVXB_TS"/>
 *           &lt;choice minOccurs="0">
 *             &lt;element name="width" type="{urn:hl7-org:v3}PQ" minOccurs="0"/>
 *             &lt;element name="high" type="{urn:hl7-org:v3}IVXB_TS" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;element name="high" type="{urn:hl7-org:v3}IVXB_TS"/>
 *         &lt;sequence>
 *           &lt;element name="width" type="{urn:hl7-org:v3}PQ"/>
 *           &lt;element name="high" type="{urn:hl7-org:v3}IVXB_TS" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="center" type="{urn:hl7-org:v3}TS"/>
 *           &lt;element name="width" type="{urn:hl7-org:v3}PQ" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVL_TS", propOrder = { "rest" })
public class IVLTS extends SXCMTS {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@XmlElementRefs({ @XmlElementRef(name = "width", namespace = "urn:hl7-org:v3", type = JAXBElement.class),
			@XmlElementRef(name = "center", namespace = "urn:hl7-org:v3", type = JAXBElement.class),
			@XmlElementRef(name = "high", namespace = "urn:hl7-org:v3", type = JAXBElement.class),
			@XmlElementRef(name = "low", namespace = "urn:hl7-org:v3", type = JAXBElement.class) })
	protected List<JAXBElement<? extends QTY>> rest;

	/**
	 * Gets the rest of the content model.
	 *
	 * <p>
	 * You are getting this "catch-all" property because of the following reason:
	 * The field name "High" is used by two different parts of a schema. See: line
	 * 1774 of file:/Users/andrzejburyan/Documents/workspace/cdasample2/wsdl/CDA.xsd
	 * line 1765 of
	 * file:/Users/andrzejburyan/Documents/workspace/cdasample2/wsdl/CDA.xsd
	 * <p>
	 * To get rid of this property, apply a property customization to one of both of
	 * the following declarations to change their names: Gets the value of the rest
	 * property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the rest property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getRest().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link JAXBElement
	 * }{@code <} {@link PQ }{@code >} {@link JAXBElement }{@code <}{@link TS
	 * }{@code >} {@link JAXBElement } {@code <}{@link IVXBTS }{@code >}
	 * {@link JAXBElement }{@code <}{@link IVXBTS }{@code >}
	 *
	 *
	 */
	public List<JAXBElement<? extends QTY>> getRest() {
		if (rest == null) {
			rest = new ArrayList<JAXBElement<? extends QTY>>();
		}
		return this.rest;
	}

}
