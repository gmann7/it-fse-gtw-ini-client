
package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per MCCI_MT000100UV01.Device complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MCCI_MT000100UV01.Device">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II" maxOccurs="unbounded"/>
 *         &lt;element name="name" type="{urn:hl7-org:v3}EN" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="desc" type="{urn:hl7-org:v3}ED" minOccurs="0"/>
 *         &lt;element name="existenceTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:hl7-org:v3}TEL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="manufacturerModelName" type="{urn:hl7-org:v3}SC" minOccurs="0"/>
 *         &lt;element name="softwareName" type="{urn:hl7-org:v3}SC" minOccurs="0"/>
 *         &lt;element name="asAgent" type="{urn:hl7-org:v3}MCCI_MT000100UV01.Agent" minOccurs="0"/>
 *         &lt;element name="asLocatedEntity" type="{urn:hl7-org:v3}MCCI_MT000100UV01.LocatedEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:hl7-org:v3}InfrastructureRootAttributes"/>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}EntityClassDevice" />
 *       &lt;attribute name="determinerCode" use="required" type="{urn:hl7-org:v3}EntityDeterminer" fixed="INSTANCE" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MCCI_MT000100UV01.Device", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "name",
    "desc",
    "existenceTime",
    "telecom",
    "manufacturerModelName",
    "softwareName",
    "asAgent",
    "asLocatedEntity"
})
public class MCCIMT000100UV01Device {

    protected List<CS> realmCode;
    protected II typeId;
    protected List<II> templateId;
    @XmlElement(required = true)
    protected List<II> id;
    protected List<EN> name;
    protected ED desc;
    protected IVLTS existenceTime;
    protected List<TEL> telecom;
    protected SC manufacturerModelName;
    protected SC softwareName;
    @XmlElementRef(name = "asAgent", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<MCCIMT000100UV01Agent> asAgent;
    @XmlElement(nillable = true)
    protected List<MCCIMT000100UV01LocatedEntity> asLocatedEntity;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode", required = true)
    protected EntityClassDevice classCode;
    @XmlAttribute(name = "determinerCode", required = true)
    protected String determinerCode;

    /**
     * Gets the value of the realmCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realmCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
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
     * Recupera il valore della proprietà typeId.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getTypeId() {
        return typeId;
    }

    /**
     * Imposta il valore della proprietà typeId.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setTypeId(II value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the templateId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
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
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
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
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EN }
     * 
     * 
     */
    public List<EN> getName() {
        if (name == null) {
            name = new ArrayList<EN>();
        }
        return this.name;
    }

    /**
     * Recupera il valore della proprietà desc.
     * 
     * @return
     *     possible object is
     *     {@link ED }
     *     
     */
    public ED getDesc() {
        return desc;
    }

    /**
     * Imposta il valore della proprietà desc.
     * 
     * @param value
     *     allowed object is
     *     {@link ED }
     *     
     */
    public void setDesc(ED value) {
        this.desc = value;
    }

    /**
     * Recupera il valore della proprietà existenceTime.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getExistenceTime() {
        return existenceTime;
    }

    /**
     * Imposta il valore della proprietà existenceTime.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setExistenceTime(IVLTS value) {
        this.existenceTime = value;
    }

    /**
     * Gets the value of the telecom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telecom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelecom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TEL }
     * 
     * 
     */
    public List<TEL> getTelecom() {
        if (telecom == null) {
            telecom = new ArrayList<TEL>();
        }
        return this.telecom;
    }

    /**
     * Recupera il valore della proprietà manufacturerModelName.
     * 
     * @return
     *     possible object is
     *     {@link SC }
     *     
     */
    public SC getManufacturerModelName() {
        return manufacturerModelName;
    }

    /**
     * Imposta il valore della proprietà manufacturerModelName.
     * 
     * @param value
     *     allowed object is
     *     {@link SC }
     *     
     */
    public void setManufacturerModelName(SC value) {
        this.manufacturerModelName = value;
    }

    /**
     * Recupera il valore della proprietà softwareName.
     * 
     * @return
     *     possible object is
     *     {@link SC }
     *     
     */
    public SC getSoftwareName() {
        return softwareName;
    }

    /**
     * Imposta il valore della proprietà softwareName.
     * 
     * @param value
     *     allowed object is
     *     {@link SC }
     *     
     */
    public void setSoftwareName(SC value) {
        this.softwareName = value;
    }

    /**
     * Recupera il valore della proprietà asAgent.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MCCIMT000100UV01Agent }{@code >}
     *     
     */
    public JAXBElement<MCCIMT000100UV01Agent> getAsAgent() {
        return asAgent;
    }

    /**
     * Imposta il valore della proprietà asAgent.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MCCIMT000100UV01Agent }{@code >}
     *     
     */
    public void setAsAgent(JAXBElement<MCCIMT000100UV01Agent> value) {
        this.asAgent = value;
    }

    /**
     * Gets the value of the asLocatedEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asLocatedEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsLocatedEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MCCIMT000100UV01LocatedEntity }
     * 
     * 
     */
    public List<MCCIMT000100UV01LocatedEntity> getAsLocatedEntity() {
        if (asLocatedEntity == null) {
            asLocatedEntity = new ArrayList<MCCIMT000100UV01LocatedEntity>();
        }
        return this.asLocatedEntity;
    }

    /**
     * Gets the value of the nullFlavor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nullFlavor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
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
     * Recupera il valore della proprietà classCode.
     * 
     * @return
     *     possible object is
     *     {@link EntityClassDevice }
     *     
     */
    public EntityClassDevice getClassCode() {
        return classCode;
    }

    /**
     * Imposta il valore della proprietà classCode.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityClassDevice }
     *     
     */
    public void setClassCode(EntityClassDevice value) {
        this.classCode = value;
    }

    /**
     * Recupera il valore della proprietà determinerCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeterminerCode() {
        if (determinerCode == null) {
            return "INSTANCE";
        } else {
            return determinerCode;
        }
    }

    /**
     * Imposta il valore della proprietà determinerCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeterminerCode(String value) {
        this.determinerCode = value;
    }

}
