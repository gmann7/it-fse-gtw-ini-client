
package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per COCT_MT510000UV06.FinancialParticipationCharge complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="COCT_MT510000UV06.FinancialParticipationCharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="code" type="{urn:hl7-org:v3}CD" minOccurs="0"/>
 *         &lt;element name="effectiveTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *         &lt;element name="unitQuantity" type="{urn:hl7-org:v3}RTO_PQ_PQ" minOccurs="0"/>
 *         &lt;element name="unitPriceAmt" type="{urn:hl7-org:v3}RTO_MO_PQ" minOccurs="0"/>
 *         &lt;element name="netAmt" type="{urn:hl7-org:v3}MO" minOccurs="0"/>
 *         &lt;element name="factorNumber" type="{urn:hl7-org:v3}REAL" minOccurs="0"/>
 *         &lt;element name="reference" type="{urn:hl7-org:v3}COCT_MT510000UV06.Reference2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:hl7-org:v3}InfrastructureRootAttributes"/>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}ActClass" fixed="INVE" />
 *       &lt;attribute name="moodCode" use="required" type="{urn:hl7-org:v3}ActMoodCriterion" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COCT_MT510000UV06.FinancialParticipationCharge", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "code",
    "effectiveTime",
    "unitQuantity",
    "unitPriceAmt",
    "netAmt",
    "factorNumber",
    "reference"
})
public class COCTMT510000UV06FinancialParticipationCharge {

    protected List<CS> realmCode;
    protected II typeId;
    protected List<II> templateId;
    protected List<II> id;
    protected CD code;
    protected IVLTS effectiveTime;
    protected RTOPQPQ unitQuantity;
    protected RTOMOPQ unitPriceAmt;
    protected MO netAmt;
    protected REAL factorNumber;
    @XmlElement(nillable = true)
    protected List<COCTMT510000UV06Reference2> reference;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode", required = true)
    protected List<String> classCode;
    @XmlAttribute(name = "moodCode", required = true)
    protected ActMoodCriterion moodCode;

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
     * Recupera il valore della proprietà code.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getCode() {
        return code;
    }

    /**
     * Imposta il valore della proprietà code.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setCode(CD value) {
        this.code = value;
    }

    /**
     * Recupera il valore della proprietà effectiveTime.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Imposta il valore della proprietà effectiveTime.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setEffectiveTime(IVLTS value) {
        this.effectiveTime = value;
    }

    /**
     * Recupera il valore della proprietà unitQuantity.
     * 
     * @return
     *     possible object is
     *     {@link RTOPQPQ }
     *     
     */
    public RTOPQPQ getUnitQuantity() {
        return unitQuantity;
    }

    /**
     * Imposta il valore della proprietà unitQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link RTOPQPQ }
     *     
     */
    public void setUnitQuantity(RTOPQPQ value) {
        this.unitQuantity = value;
    }

    /**
     * Recupera il valore della proprietà unitPriceAmt.
     * 
     * @return
     *     possible object is
     *     {@link RTOMOPQ }
     *     
     */
    public RTOMOPQ getUnitPriceAmt() {
        return unitPriceAmt;
    }

    /**
     * Imposta il valore della proprietà unitPriceAmt.
     * 
     * @param value
     *     allowed object is
     *     {@link RTOMOPQ }
     *     
     */
    public void setUnitPriceAmt(RTOMOPQ value) {
        this.unitPriceAmt = value;
    }

    /**
     * Recupera il valore della proprietà netAmt.
     * 
     * @return
     *     possible object is
     *     {@link MO }
     *     
     */
    public MO getNetAmt() {
        return netAmt;
    }

    /**
     * Imposta il valore della proprietà netAmt.
     * 
     * @param value
     *     allowed object is
     *     {@link MO }
     *     
     */
    public void setNetAmt(MO value) {
        this.netAmt = value;
    }

    /**
     * Recupera il valore della proprietà factorNumber.
     * 
     * @return
     *     possible object is
     *     {@link REAL }
     *     
     */
    public REAL getFactorNumber() {
        return factorNumber;
    }

    /**
     * Imposta il valore della proprietà factorNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link REAL }
     *     
     */
    public void setFactorNumber(REAL value) {
        this.factorNumber = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT510000UV06Reference2 }
     * 
     * 
     */
    public List<COCTMT510000UV06Reference2> getReference() {
        if (reference == null) {
            reference = new ArrayList<COCTMT510000UV06Reference2>();
        }
        return this.reference;
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
     * Gets the value of the classCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
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
     * Recupera il valore della proprietà moodCode.
     * 
     * @return
     *     possible object is
     *     {@link ActMoodCriterion }
     *     
     */
    public ActMoodCriterion getMoodCode() {
        return moodCode;
    }

    /**
     * Imposta il valore della proprietà moodCode.
     * 
     * @param value
     *     allowed object is
     *     {@link ActMoodCriterion }
     *     
     */
    public void setMoodCode(ActMoodCriterion value) {
        this.moodCode = value;
    }

}
