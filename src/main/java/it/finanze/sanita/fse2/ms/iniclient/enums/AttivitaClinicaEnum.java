/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.enums;

public enum AttivitaClinicaEnum {

	PHR("PHR", "Personal Health Record Update"),
	CON("CON", "Consulto"),
	DIS("DIS", "Discharge"),
	ERP("ERP", "Erogazione Prestazione Prenotata"),
	Sistema_TS("Sistema TS", "Documenti sistema TS"),
	INI("INI","Documenti INI"),
	PN_DGC("PN-DGC","Documenti PN-DGC"),
	OBS("OBS","Documento stato di salute");

	private String code;
	private String description;

	private AttivitaClinicaEnum(String inCode, String inDescription) {
		code = inCode;
		description = inDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

}