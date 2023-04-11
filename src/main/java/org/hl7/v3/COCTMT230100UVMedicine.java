
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
 * <p>Classe Java per COCT_MT230100UV.Medicine complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="COCT_MT230100UV.Medicine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II" minOccurs="0"/>
 *         &lt;element name="code" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:hl7-org:v3}TN" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="desc" type="{urn:hl7-org:v3}ED" minOccurs="0"/>
 *         &lt;element name="riskCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="handlingCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="formCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="lotNumberText" type="{urn:hl7-org:v3}ST" minOccurs="0"/>
 *         &lt;element name="expirationTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *         &lt;element name="stabilityTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *         &lt;element name="asDistributedProduct" type="{urn:hl7-org:v3}COCT_MT230100UV.DistributedProduct" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="asMedicineManufacturer" type="{urn:hl7-org:v3}COCT_MT230100UV.MedicineManufacturer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="asContent" type="{urn:hl7-org:v3}COCT_MT230100UV.Content" minOccurs="0"/>
 *         &lt;element name="asSpecializedKind" type="{urn:hl7-org:v3}COCT_MT230100UV.SpecializedKind" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="part" type="{urn:hl7-org:v3}COCT_MT230100UV.Part" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ingredient" type="{urn:hl7-org:v3}COCT_MT230100UV.Ingredient" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:hl7-org:v3}InfrastructureRootAttributes"/>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" use="required" type="{urn:hl7-org:v3}EntityClassManufacturedMaterial" />
 *       &lt;attribute name="determinerCode" use="required" type="{urn:hl7-org:v3}EntityDeterminer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COCT_MT230100UV.Medicine", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "code",
    "name",
    "desc",
    "riskCode",
    "handlingCode",
    "formCode",
    "lotNumberText",
    "expirationTime",
    "stabilityTime",
    "asDistributedProduct",
    "asMedicineManufacturer",
    "asContent",
    "asSpecializedKind",
    "part",
    "ingredient"
})
public class COCTMT230100UVMedicine {

    protected List<CS> realmCode;
    protected II typeId;
    protected List<II> templateId;
    protected II id;
    protected CE code;
    protected List<TN> name;
    protected ED desc;
    protected CE riskCode;
    protected CE handlingCode;
    protected CE formCode;
    protected ST lotNumberText;
    protected IVLTS expirationTime;
    protected IVLTS stabilityTime;
    @XmlElement(nillable = true)
    protected List<COCTMT230100UVDistributedProduct> asDistributedProduct;
    @XmlElement(nillable = true)
    protected List<COCTMT230100UVMedicineManufacturer> asMedicineManufacturer;
    @XmlElementRef(name = "asContent", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<COCTMT230100UVContent> asContent;
    @XmlElement(nillable = true)
    protected List<COCTMT230100UVSpecializedKind> asSpecializedKind;
    @XmlElement(nillable = true)
    protected List<COCTMT230100UVPart> part;
    @XmlElement(nillable = true)
    protected List<COCTMT230100UVIngredient> ingredient;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode", required = true)
    protected String classCode;
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
     * Recupera il valore della propriet� typeId.
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
     * Imposta il valore della propriet� typeId.
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
     * Recupera il valore della propriet� id.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getId() {
        return id;
    }

    /**
     * Imposta il valore della propriet� id.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setId(II value) {
        this.id = value;
    }

    /**
     * Recupera il valore della propriet� code.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getCode() {
        return code;
    }

    /**
     * Imposta il valore della propriet� code.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setCode(CE value) {
        this.code = value;
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
     * {@link TN }
     * 
     * 
     */
    public List<TN> getName() {
        if (name == null) {
            name = new ArrayList<TN>();
        }
        return this.name;
    }

    /**
     * Recupera il valore della propriet� desc.
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
     * Imposta il valore della propriet� desc.
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
     * Recupera il valore della propriet� riskCode.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getRiskCode() {
        return riskCode;
    }

    /**
     * Imposta il valore della propriet� riskCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setRiskCode(CE value) {
        this.riskCode = value;
    }

    /**
     * Recupera il valore della propriet� handlingCode.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getHandlingCode() {
        return handlingCode;
    }

    /**
     * Imposta il valore della propriet� handlingCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setHandlingCode(CE value) {
        this.handlingCode = value;
    }

    /**
     * Recupera il valore della propriet� formCode.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getFormCode() {
        return formCode;
    }

    /**
     * Imposta il valore della propriet� formCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setFormCode(CE value) {
        this.formCode = value;
    }

    /**
     * Recupera il valore della propriet� lotNumberText.
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getLotNumberText() {
        return lotNumberText;
    }

    /**
     * Imposta il valore della propriet� lotNumberText.
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setLotNumberText(ST value) {
        this.lotNumberText = value;
    }

    /**
     * Recupera il valore della propriet� expirationTime.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getExpirationTime() {
        return expirationTime;
    }

    /**
     * Imposta il valore della propriet� expirationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setExpirationTime(IVLTS value) {
        this.expirationTime = value;
    }

    /**
     * Recupera il valore della propriet� stabilityTime.
     * 
     * @return
     *     possible object is
     *     {@link IVLTS }
     *     
     */
    public IVLTS getStabilityTime() {
        return stabilityTime;
    }

    /**
     * Imposta il valore della propriet� stabilityTime.
     * 
     * @param value
     *     allowed object is
     *     {@link IVLTS }
     *     
     */
    public void setStabilityTime(IVLTS value) {
        this.stabilityTime = value;
    }

    /**
     * Gets the value of the asDistributedProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asDistributedProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsDistributedProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT230100UVDistributedProduct }
     * 
     * 
     */
    public List<COCTMT230100UVDistributedProduct> getAsDistributedProduct() {
        if (asDistributedProduct == null) {
            asDistributedProduct = new ArrayList<COCTMT230100UVDistributedProduct>();
        }
        return this.asDistributedProduct;
    }

    /**
     * Gets the value of the asMedicineManufacturer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asMedicineManufacturer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsMedicineManufacturer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT230100UVMedicineManufacturer }
     * 
     * 
     */
    public List<COCTMT230100UVMedicineManufacturer> getAsMedicineManufacturer() {
        if (asMedicineManufacturer == null) {
            asMedicineManufacturer = new ArrayList<COCTMT230100UVMedicineManufacturer>();
        }
        return this.asMedicineManufacturer;
    }

    /**
     * Recupera il valore della propriet� asContent.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link COCTMT230100UVContent }{@code >}
     *     
     */
    public JAXBElement<COCTMT230100UVContent> getAsContent() {
        return asContent;
    }

    /**
     * Imposta il valore della propriet� asContent.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link COCTMT230100UVContent }{@code >}
     *     
     */
    public void setAsContent(JAXBElement<COCTMT230100UVContent> value) {
        this.asContent = value;
    }

    /**
     * Gets the value of the asSpecializedKind property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asSpecializedKind property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsSpecializedKind().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT230100UVSpecializedKind }
     * 
     * 
     */
    public List<COCTMT230100UVSpecializedKind> getAsSpecializedKind() {
        if (asSpecializedKind == null) {
            asSpecializedKind = new ArrayList<COCTMT230100UVSpecializedKind>();
        }
        return this.asSpecializedKind;
    }

    /**
     * Gets the value of the part property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the part property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPart().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT230100UVPart }
     * 
     * 
     */
    public List<COCTMT230100UVPart> getPart() {
        if (part == null) {
            part = new ArrayList<COCTMT230100UVPart>();
        }
        return this.part;
    }

    /**
     * Gets the value of the ingredient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ingredient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIngredient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COCTMT230100UVIngredient }
     * 
     * 
     */
    public List<COCTMT230100UVIngredient> getIngredient() {
        if (ingredient == null) {
            ingredient = new ArrayList<COCTMT230100UVIngredient>();
        }
        return this.ingredient;
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
     * Recupera il valore della propriet� classCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassCode() {
        return classCode;
    }

    /**
     * Imposta il valore della propriet� classCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassCode(String value) {
        this.classCode = value;
    }

    /**
     * Recupera il valore della propriet� determinerCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeterminerCode() {
        return determinerCode;
    }

    /**
     * Imposta il valore della propriet� determinerCode.
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
