package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for POCD_MT000040.RegionOfInterest complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="POCD_MT000040.RegionOfInterest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         &lt;element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II" maxOccurs="unbounded"/>
 *         &lt;element name="code" type="{urn:hl7-org:v3}CS"/>
 *         &lt;element name="value" type="{urn:hl7-org:v3}POCD_MT000040.RegionOfInterest.value" maxOccurs="unbounded"/>
 *         &lt;element name="subject" type="{urn:hl7-org:v3}POCD_MT000040.Subject" minOccurs="0"/>
 *         &lt;element name="specimen" type="{urn:hl7-org:v3}POCD_MT000040.Specimen" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="performer" type="{urn:hl7-org:v3}POCD_MT000040.Performer2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="author" type="{urn:hl7-org:v3}POCD_MT000040.Author" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="informant" type="{urn:hl7-org:v3}POCD_MT000040.Informant12" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="participant" type="{urn:hl7-org:v3}POCD_MT000040.Participant2" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entryRelationship" type="{urn:hl7-org:v3}POCD_MT000040.EntryRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reference" type="{urn:hl7-org:v3}POCD_MT000040.Reference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="precondition" type="{urn:hl7-org:v3}POCD_MT000040.Precondition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}ActClass" fixed="ROIOVL" />
 *       &lt;attribute name="moodCode" use="required" type="{urn:hl7-org:v3}ActMood" fixed="EVN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.RegionOfInterest", propOrder = { "realmCode", "typeId", "templateId", "id", "code",
		"value", "subject", "specimen", "performer", "author", "informant", "participant", "entryRelationship",
		"reference", "precondition" })
public class POCDMT000040RegionOfInterest {

	protected List<CS> realmCode;
	protected POCDMT000040InfrastructureRootTypeId typeId;
	protected List<II> templateId;
	@XmlElement(required = true)
	protected List<II> id;
	@XmlElement(required = true)
	protected CS code;
	@XmlElement(required = true)
	protected List<POCDMT000040RegionOfInterestValue> value;
	protected POCDMT000040Subject subject;
	protected List<POCDMT000040Specimen> specimen;
	protected List<POCDMT000040Performer2> performer;
	protected List<POCDMT000040Author> author;
	protected List<POCDMT000040Informant12> informant;
	protected List<POCDMT000040Participant2> participant;
	protected List<POCDMT000040EntryRelationship> entryRelationship;
	protected List<POCDMT000040Reference> reference;
	protected List<POCDMT000040Precondition> precondition;
	@XmlAttribute(name = "ID")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String attributeId;
	@XmlAttribute
	protected List<String> nullFlavor;
	@XmlAttribute(required = true)
	protected List<String> classCode;
	@XmlAttribute(required = true)
	protected List<String> moodCode;

	/**
	 * Gets the value of the realmCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the realmCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getRealmCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link CS }
	 *
	 *
	 */
	public List<CS> getRealmCode() {
		if (realmCode == null) {
			realmCode = new ArrayList<CS>();
		}
		return this.realmCode;
	}

	/**
	 * Gets the value of the typeId property.
	 *
	 * @return possible object is {@link POCDMT000040InfrastructureRootTypeId }
	 *
	 */
	public POCDMT000040InfrastructureRootTypeId getTypeId() {
		return typeId;
	}

	/**
	 * Sets the value of the typeId property.
	 *
	 * @param value allowed object is {@link POCDMT000040InfrastructureRootTypeId }
	 *
	 */
	public void setTypeId(POCDMT000040InfrastructureRootTypeId value) {
		this.typeId = value;
	}

	/**
	 * Gets the value of the templateId property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the templateId property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getTemplateId().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link II }
	 *
	 *
	 */
	public List<II> getTemplateId() {
		if (templateId == null) {
			templateId = new ArrayList<II>();
		}
		return this.templateId;
	}

	/**
	 * Gets the value of the id property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the id property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getId().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link II }
	 *
	 *
	 */
	public List<II> getId() {
		if (id == null) {
			id = new ArrayList<II>();
		}
		return this.id;
	}

	/**
	 * Gets the value of the code property.
	 *
	 * @return possible object is {@link CS }
	 *
	 */
	public CS getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 *
	 * @param value allowed object is {@link CS }
	 *
	 */
	public void setCode(CS value) {
		this.code = value;
	}

	/**
	 * Gets the value of the value property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the value property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getValue().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040RegionOfInterestValue }
	 *
	 *
	 */
	public List<POCDMT000040RegionOfInterestValue> getValue() {
		if (value == null) {
			value = new ArrayList<POCDMT000040RegionOfInterestValue>();
		}
		return this.value;
	}

	/**
	 * Gets the value of the subject property.
	 *
	 * @return possible object is {@link POCDMT000040Subject }
	 *
	 */
	public POCDMT000040Subject getSubject() {
		return subject;
	}

	/**
	 * Sets the value of the subject property.
	 *
	 * @param value allowed object is {@link POCDMT000040Subject }
	 *
	 */
	public void setSubject(POCDMT000040Subject value) {
		this.subject = value;
	}

	/**
	 * Gets the value of the specimen property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the specimen property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getSpecimen().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Specimen }
	 *
	 *
	 */
	public List<POCDMT000040Specimen> getSpecimen() {
		if (specimen == null) {
			specimen = new ArrayList<POCDMT000040Specimen>();
		}
		return this.specimen;
	}

	/**
	 * Gets the value of the performer property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the performer property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getPerformer().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Performer2 }
	 *
	 *
	 */
	public List<POCDMT000040Performer2> getPerformer() {
		if (performer == null) {
			performer = new ArrayList<POCDMT000040Performer2>();
		}
		return this.performer;
	}

	/**
	 * Gets the value of the author property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the author property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getAuthor().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Author }
	 *
	 *
	 */
	public List<POCDMT000040Author> getAuthor() {
		if (author == null) {
			author = new ArrayList<POCDMT000040Author>();
		}
		return this.author;
	}

	/**
	 * Gets the value of the informant property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the informant property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getInformant().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Informant12 }
	 *
	 *
	 */
	public List<POCDMT000040Informant12> getInformant() {
		if (informant == null) {
			informant = new ArrayList<POCDMT000040Informant12>();
		}
		return this.informant;
	}

	/**
	 * Gets the value of the participant property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the participant property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getParticipant().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Participant2 }
	 *
	 *
	 */
	public List<POCDMT000040Participant2> getParticipant() {
		if (participant == null) {
			participant = new ArrayList<POCDMT000040Participant2>();
		}
		return this.participant;
	}

	/**
	 * Gets the value of the entryRelationship property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the entryRelationship property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getEntryRelationship().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040EntryRelationship }
	 *
	 *
	 */
	public List<POCDMT000040EntryRelationship> getEntryRelationship() {
		if (entryRelationship == null) {
			entryRelationship = new ArrayList<POCDMT000040EntryRelationship>();
		}
		return this.entryRelationship;
	}

	/**
	 * Gets the value of the reference property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the reference property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getReference().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Reference }
	 *
	 *
	 */
	public List<POCDMT000040Reference> getReference() {
		if (reference == null) {
			reference = new ArrayList<POCDMT000040Reference>();
		}
		return this.reference;
	}

	/**
	 * Gets the value of the precondition property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the precondition property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getPrecondition().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Precondition }
	 *
	 *
	 */
	public List<POCDMT000040Precondition> getPrecondition() {
		if (precondition == null) {
			precondition = new ArrayList<POCDMT000040Precondition>();
		}
		return this.precondition;
	}

	/**
	 * Gets the value of the attributeId property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getAttributeId() {
		return attributeId;
	}

	/**
	 * Sets the value of the attributeId property.
	 *
	 * @param value allowed object is {@link String }
	 *
	 */
	public void setAttributeId(String value) {
		this.attributeId = value;
	}

	/**
	 * Gets the value of the nullFlavor property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the nullFlavor property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getNullFlavor().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getNullFlavor() {
		if (nullFlavor == null) {
			nullFlavor = new ArrayList<String>();
		}
		return this.nullFlavor;
	}

	/**
	 * Gets the value of the classCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the classCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getClassCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getClassCode() {
		if (classCode == null) {
			classCode = new ArrayList<String>();
		}
		return this.classCode;
	}

	/**
	 * Gets the value of the moodCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the moodCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getMoodCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getMoodCode() {
		if (moodCode == null) {
			moodCode = new ArrayList<String>();
		}
		return this.moodCode;
	}

}
