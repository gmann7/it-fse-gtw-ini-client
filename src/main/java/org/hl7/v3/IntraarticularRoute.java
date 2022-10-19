/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IntraarticularRoute.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="IntraarticularRoute">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="IARTINJ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IntraarticularRoute")
@XmlEnum
public enum IntraarticularRoute {

    IARTINJ;

    public String value() {
        return name();
    }

    public static IntraarticularRoute fromValue(String v) {
        return valueOf(v);
    }

}
