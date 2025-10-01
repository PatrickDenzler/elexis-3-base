//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v3.0.2 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2025.10.01 um 10:56:51 AM CEST 
//


package ch.fd.invoice500.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für balanceTSType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="balanceTSType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" fixed="CHF" /&gt;
 *       &lt;attribute name="amount" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="amount_reminder" type="{http://www.w3.org/2001/XMLSchema}double" default="0" /&gt;
 *       &lt;attribute name="amount_due" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="amount_paid" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="amount_unpaid" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "balanceTSType")
public class BalanceTSType {

    @XmlAttribute(name = "currency")
    protected String currency;
    @XmlAttribute(name = "amount", required = true)
    protected double amount;
    @XmlAttribute(name = "amount_reminder")
    protected Double amountReminder;
    @XmlAttribute(name = "amount_due", required = true)
    protected double amountDue;
    @XmlAttribute(name = "amount_paid", required = true)
    protected double amountPaid;
    @XmlAttribute(name = "amount_unpaid", required = true)
    protected double amountUnpaid;

    /**
     * Ruft den Wert der currency-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        if (currency == null) {
            return "CHF";
        } else {
            return currency;
        }
    }

    /**
     * Legt den Wert der currency-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Ruft den Wert der amount-Eigenschaft ab.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Legt den Wert der amount-Eigenschaft fest.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Ruft den Wert der amountReminder-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getAmountReminder() {
        if (amountReminder == null) {
            return  0.0D;
        } else {
            return amountReminder;
        }
    }

    /**
     * Legt den Wert der amountReminder-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmountReminder(Double value) {
        this.amountReminder = value;
    }

    /**
     * Ruft den Wert der amountDue-Eigenschaft ab.
     * 
     */
    public double getAmountDue() {
        return amountDue;
    }

    /**
     * Legt den Wert der amountDue-Eigenschaft fest.
     * 
     */
    public void setAmountDue(double value) {
        this.amountDue = value;
    }

    /**
     * Ruft den Wert der amountPaid-Eigenschaft ab.
     * 
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Legt den Wert der amountPaid-Eigenschaft fest.
     * 
     */
    public void setAmountPaid(double value) {
        this.amountPaid = value;
    }

    /**
     * Ruft den Wert der amountUnpaid-Eigenschaft ab.
     * 
     */
    public double getAmountUnpaid() {
        return amountUnpaid;
    }

    /**
     * Legt den Wert der amountUnpaid-Eigenschaft fest.
     * 
     */
    public void setAmountUnpaid(double value) {
        this.amountUnpaid = value;
    }

}
