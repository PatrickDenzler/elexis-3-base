package org.hl7.v3;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for adxp.direction complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="adxp.direction">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:hl7-org:v3}ADXP">
 *       &lt;attribute name="partType" type="{urn:hl7-org:v3}AddressPartType" fixed="DIR" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adxp.direction")
public class AdxpDirection extends ADXP {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
