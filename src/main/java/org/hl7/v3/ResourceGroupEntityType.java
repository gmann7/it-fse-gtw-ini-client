
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ResourceGroupEntityType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ResourceGroupEntityType">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="PRAC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ResourceGroupEntityType")
@XmlEnum
public enum ResourceGroupEntityType {

    PRAC;

    public String value() {
        return name();
    }

    public static ResourceGroupEntityType fromValue(String v) {
        return valueOf(v);
    }

}
