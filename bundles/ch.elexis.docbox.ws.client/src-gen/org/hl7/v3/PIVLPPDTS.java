
package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             Note: because this type is defined as an extension of SXCM_T,
 *             all of the attributes and elements accepted for T are also
 *             accepted by this definition.  However, they are NOT allowed
 *             by the normative description of this type.  Unfortunately,
 *             we cannot write a general purpose schematron contraints to
 *             provide that extra validation, thus applications must be
 *             aware that instance (fragments) that pass validation with
 *             this might might still not be legal.
 *          
 * 
 * <p>Java class for PIVL_PPD_TS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PIVL_PPD_TS">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:v3}SXCM_PPD_TS">
 *       &lt;sequence>
 *         &lt;element name="phase" type="{urn:hl7-org:v3}IVL_PPD_TS" minOccurs="0"/>
 *         &lt;element name="period" type="{urn:hl7-org:v3}PPD_PQ" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="alignment" type="{urn:hl7-org:v3}CalendarCycle" />
 *       &lt;attribute name="institutionSpecified" type="{urn:hl7-org:v3}bl" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PIVL_PPD_TS", propOrder = {
    "phase",
    "period"
})
public class PIVLPPDTS
    extends SXCMPPDTS
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected IVLPPDTS phase;
    protected PPDPQ period;
    @XmlAttribute
    protected List<String> alignment;
    @XmlAttribute
    protected Boolean institutionSpecified;

    /**
     * Gets the value of the phase property.
     * 
     * @return
     *     possible object is
     *     {@link IVLPPDTS }
     *     
     */
    public IVLPPDTS getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLPPDTS }
     *     
     */
    public void setPhase(IVLPPDTS value) {
        this.phase = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link PPDPQ }
     *     
     */
    public PPDPQ getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link PPDPQ }
     *     
     */
    public void setPeriod(PPDPQ value) {
        this.period = value;
    }

    /**
     * Gets the value of the alignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAlignment() {
        if (alignment == null) {
            alignment = new ArrayList<String>();
        }
        return this.alignment;
    }

    /**
     * Gets the value of the institutionSpecified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isInstitutionSpecified() {
        if (institutionSpecified == null) {
            return false;
        } else {
            return institutionSpecified;
        }
    }

    /**
     * Sets the value of the institutionSpecified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInstitutionSpecified(Boolean value) {
        this.institutionSpecified = value;
    }

}
