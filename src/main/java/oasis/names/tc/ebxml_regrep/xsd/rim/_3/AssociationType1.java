
package oasis.names.tc.ebxml_regrep.xsd.rim._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Association is the mapping of the same named interface in ebRIM.
 *         It extends RegistryObject.
 *         An Association specifies references to two previously submitted
 *         registry entrys.
 *         The sourceObject is id of the sourceObject in association
 *         The targetObject is id of the targetObject in association
 *       
 * 
 * <p>Classe Java per AssociationType1 complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AssociationType1">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}RegistryObjectType">
 *       &lt;attribute name="associationType" use="required" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *       &lt;attribute name="sourceObject" use="required" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *       &lt;attribute name="targetObject" use="required" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssociationType1")
public class AssociationType1
    extends RegistryObjectType
{

    @XmlAttribute(name = "associationType", required = true)
    protected String associationType;
    @XmlAttribute(name = "sourceObject", required = true)
    protected String sourceObject;
    @XmlAttribute(name = "targetObject", required = true)
    protected String targetObject;

    /**
     * Recupera il valore della proprietÓ associationType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociationType() {
        return associationType;
    }

    /**
     * Imposta il valore della proprietÓ associationType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociationType(String value) {
        this.associationType = value;
    }

    /**
     * Recupera il valore della proprietÓ sourceObject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceObject() {
        return sourceObject;
    }

    /**
     * Imposta il valore della proprietÓ sourceObject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceObject(String value) {
        this.sourceObject = value;
    }

    /**
     * Recupera il valore della proprietÓ targetObject.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetObject() {
        return targetObject;
    }

    /**
     * Imposta il valore della proprietÓ targetObject.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetObject(String value) {
        this.targetObject = value;
    }

}
