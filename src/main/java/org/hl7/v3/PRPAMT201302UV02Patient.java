
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
 * <p>Classe Java per PRPA_MT201302UV02.Patient complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PRPA_MT201302UV02.Patient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}PRPA_MT201302UV02.Patient.id" maxOccurs="unbounded"/>
 *         &lt;element name="addr" type="{urn:hl7-org:v3}AD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:hl7-org:v3}TEL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{urn:hl7-org:v3}PRPA_MT201302UV02.Patient.statusCode"/>
 *         &lt;element name="effectiveTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *         &lt;element name="confidentialityCode" type="{urn:hl7-org:v3}CE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="veryImportantPersonCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="patientPerson" type="{urn:hl7-org:v3}PRPA_MT201302UV02.Patient.patientPerson"/>
 *           &lt;element name="patientNonPersonLivingSubject" type="{urn:hl7-org:v3}PRPA_MT201302UV02.Patient.patientNonPersonLivingSubject"/>
 *         &lt;/choice>
 *         &lt;element name="providerOrganization" type="{urn:hl7-org:v3}COCT_MT150003UV03.Organization" minOccurs="0"/>
 *         &lt;element name="subjectOf" type="{urn:hl7-org:v3}PRPA_MT201302UV02.Subject4" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="coveredPartyOf" type="{urn:hl7-org:v3}PRPA_MT201302UV02.CoveredParty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:hl7-org:v3}InfrastructureRootAttributes"/>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}RoleClass" fixed="PAT" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PRPA_MT201302UV02.Patient", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "addr",
    "telecom",
    "statusCode",
    "effectiveTime",
    "confidentialityCode",
    "veryImportantPersonCode",
    "patientPerson",
    "patientNonPersonLivingSubject",
    "providerOrganization",
    "subjectOf",
    "coveredPartyOf"
})
public class PRPAMT201302UV02Patient {

    protected List<CS> realmCode;
    protected II typeId;
    protected List<II> templateId;
    @XmlElement(required = true)
    protected List<PRPAMT201302UV02PatientId> id;
    protected List<AD> addr;
    protected List<TEL> telecom;
    @XmlElement(required = true)
    protected PRPAMT201302UV02PatientStatusCode statusCode;
    protected IVLTS effectiveTime;
    protected List<CE> confidentialityCode;
    protected CE veryImportantPersonCode;
    @XmlElementRef(name = "patientPerson", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<PRPAMT201302UV02PatientPatientPerson> patientPerson;
    @XmlElementRef(name = "patientNonPersonLivingSubject", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<PRPAMT201302UV02PatientPatientNonPersonLivingSubject> patientNonPersonLivingSubject;
    @XmlElementRef(name = "providerOrganization", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<COCTMT150003UV03Organization> providerOrganization;
    @XmlElement(nillable = true)
    protected List<PRPAMT201302UV02Subject4> subjectOf;
    @XmlElement(nillable = true)
    protected List<PRPAMT201302UV02CoveredParty> coveredPartyOf;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode", required = true)
    protected List<String> classCode;

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
     * {@link PRPAMT201302UV02PatientId }
     * 
     * 
     */
    public List<PRPAMT201302UV02PatientId> getId() {
        if (id == null) {
            id = new ArrayList<PRPAMT201302UV02PatientId>();
        }
        return this.id;
    }

    /**
     * Gets the value of the addr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AD }
     * 
     * 
     */
    public List<AD> getAddr() {
        if (addr == null) {
            addr = new ArrayList<AD>();
        }
        return this.addr;
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
     * Recupera il valore della proprietà statusCode.
     * 
     * @return
     *     possible object is
     *     {@link PRPAMT201302UV02PatientStatusCode }
     *     
     */
    public PRPAMT201302UV02PatientStatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * Imposta il valore della proprietà statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link PRPAMT201302UV02PatientStatusCode }
     *     
     */
    public void setStatusCode(PRPAMT201302UV02PatientStatusCode value) {
        this.statusCode = value;
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
     * Gets the value of the confidentialityCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the confidentialityCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfidentialityCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CE }
     * 
     * 
     */
    public List<CE> getConfidentialityCode() {
        if (confidentialityCode == null) {
            confidentialityCode = new ArrayList<CE>();
        }
        return this.confidentialityCode;
    }

    /**
     * Recupera il valore della proprietà veryImportantPersonCode.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getVeryImportantPersonCode() {
        return veryImportantPersonCode;
    }

    /**
     * Imposta il valore della proprietà veryImportantPersonCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setVeryImportantPersonCode(CE value) {
        this.veryImportantPersonCode = value;
    }

    /**
     * Recupera il valore della proprietà patientPerson.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201302UV02PatientPatientPerson }{@code >}
     *     
     */
    public JAXBElement<PRPAMT201302UV02PatientPatientPerson> getPatientPerson() {
        return patientPerson;
    }

    /**
     * Imposta il valore della proprietà patientPerson.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201302UV02PatientPatientPerson }{@code >}
     *     
     */
    public void setPatientPerson(JAXBElement<PRPAMT201302UV02PatientPatientPerson> value) {
        this.patientPerson = value;
    }

    /**
     * Recupera il valore della proprietà patientNonPersonLivingSubject.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201302UV02PatientPatientNonPersonLivingSubject }{@code >}
     *     
     */
    public JAXBElement<PRPAMT201302UV02PatientPatientNonPersonLivingSubject> getPatientNonPersonLivingSubject() {
        return patientNonPersonLivingSubject;
    }

    /**
     * Imposta il valore della proprietà patientNonPersonLivingSubject.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201302UV02PatientPatientNonPersonLivingSubject }{@code >}
     *     
     */
    public void setPatientNonPersonLivingSubject(JAXBElement<PRPAMT201302UV02PatientPatientNonPersonLivingSubject> value) {
        this.patientNonPersonLivingSubject = value;
    }

    /**
     * Recupera il valore della proprietà providerOrganization.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link COCTMT150003UV03Organization }{@code >}
     *     
     */
    public JAXBElement<COCTMT150003UV03Organization> getProviderOrganization() {
        return providerOrganization;
    }

    /**
     * Imposta il valore della proprietà providerOrganization.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link COCTMT150003UV03Organization }{@code >}
     *     
     */
    public void setProviderOrganization(JAXBElement<COCTMT150003UV03Organization> value) {
        this.providerOrganization = value;
    }

    /**
     * Gets the value of the subjectOf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subjectOf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubjectOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRPAMT201302UV02Subject4 }
     * 
     * 
     */
    public List<PRPAMT201302UV02Subject4> getSubjectOf() {
        if (subjectOf == null) {
            subjectOf = new ArrayList<PRPAMT201302UV02Subject4>();
        }
        return this.subjectOf;
    }

    /**
     * Gets the value of the coveredPartyOf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coveredPartyOf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoveredPartyOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRPAMT201302UV02CoveredParty }
     * 
     * 
     */
    public List<PRPAMT201302UV02CoveredParty> getCoveredPartyOf() {
        if (coveredPartyOf == null) {
            coveredPartyOf = new ArrayList<PRPAMT201302UV02CoveredParty>();
        }
        return this.coveredPartyOf;
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

}
