//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.0 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2025.01.23 um 10:57:07 AM CET 
//


package gs1.ecom.ecom_common.xsd;

import java.util.ArrayList;
import java.util.List;

import gs1.shared.shared_common.xsd.AdditionalReturnableAssetIdentificationType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java-Klasse für Ecom_ReturnableAssetIdentificationType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Ecom_ReturnableAssetIdentificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="grai" type="{urn:gs1:shared:shared_common:xsd:3}GRAIType" minOccurs="0"/&gt;
 *         &lt;element name="additionalReturnableAssetIdentification" type="{urn:gs1:shared:shared_common:xsd:3}AdditionalReturnableAssetIdentificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ecom_ReturnableAssetIdentificationType", propOrder = {
    "grai",
    "additionalReturnableAssetIdentification"
})
public class EcomReturnableAssetIdentificationType {

    protected String grai;
    protected List<AdditionalReturnableAssetIdentificationType> additionalReturnableAssetIdentification;

    /**
     * Ruft den Wert der grai-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrai() {
        return grai;
    }

    /**
     * Legt den Wert der grai-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrai(String value) {
        this.grai = value;
    }

    /**
     * Gets the value of the additionalReturnableAssetIdentification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the additionalReturnableAssetIdentification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalReturnableAssetIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalReturnableAssetIdentificationType }
     * 
     * 
     */
    public List<AdditionalReturnableAssetIdentificationType> getAdditionalReturnableAssetIdentification() {
        if (additionalReturnableAssetIdentification == null) {
            additionalReturnableAssetIdentification = new ArrayList<AdditionalReturnableAssetIdentificationType>();
        }
        return this.additionalReturnableAssetIdentification;
    }

}
