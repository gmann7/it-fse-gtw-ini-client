
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IntralymphaticRoute.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="IntralymphaticRoute">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="ILYMPJINJ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IntralymphaticRoute")
@XmlEnum
public enum IntralymphaticRoute {

    ILYMPJINJ;

    public String value() {
        return name();
    }

    public static IntralymphaticRoute fromValue(String v) {
        return valueOf(v);
    }

}
