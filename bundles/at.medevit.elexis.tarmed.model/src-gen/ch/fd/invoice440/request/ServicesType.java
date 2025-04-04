//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.18 at 03:48:09 PM CET 
//


package ch.fd.invoice440.request;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for servicesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="servicesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="record_tarmed" type="{http://www.forum-datenaustausch.ch/invoice}recordTarmedType"/>
 *         &lt;element name="record_drg" type="{http://www.forum-datenaustausch.ch/invoice}recordDRGType"/>
 *         &lt;element name="record_lab" type="{http://www.forum-datenaustausch.ch/invoice}recordLabType"/>
 *         &lt;element name="record_migel" type="{http://www.forum-datenaustausch.ch/invoice}recordMigelType"/>
 *         &lt;element name="record_paramed" type="{http://www.forum-datenaustausch.ch/invoice}recordParamedType"/>
 *         &lt;element name="record_drug" type="{http://www.forum-datenaustausch.ch/invoice}recordDrugType"/>
 *         &lt;element name="record_other" type="{http://www.forum-datenaustausch.ch/invoice}recordOtherType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicesType", propOrder = {
    "recordTarmedOrRecordDrgOrRecordLab"
})
public class ServicesType {

    @XmlElements({
        @XmlElement(name = "record_tarmed", type = RecordTarmedType.class),
        @XmlElement(name = "record_drg", type = RecordDRGType.class),
        @XmlElement(name = "record_lab", type = RecordLabType.class),
        @XmlElement(name = "record_migel", type = RecordMigelType.class),
        @XmlElement(name = "record_paramed", type = RecordParamedType.class),
        @XmlElement(name = "record_drug", type = RecordDrugType.class),
        @XmlElement(name = "record_other", type = RecordOtherType.class)
    })
    protected List<Object> recordTarmedOrRecordDrgOrRecordLab;

    /**
     * Gets the value of the recordTarmedOrRecordDrgOrRecordLab property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordTarmedOrRecordDrgOrRecordLab property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordTarmedOrRecordDrgOrRecordLab().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecordTarmedType }
     * {@link RecordDRGType }
     * {@link RecordLabType }
     * {@link RecordMigelType }
     * {@link RecordParamedType }
     * {@link RecordDrugType }
     * {@link RecordOtherType }
     * 
     * 
     */
    public List<Object> getRecordTarmedOrRecordDrgOrRecordLab() {
        if (recordTarmedOrRecordDrgOrRecordLab == null) {
            recordTarmedOrRecordDrgOrRecordLab = new ArrayList<Object>();
        }
        return this.recordTarmedOrRecordDrgOrRecordLab;
    }

}
