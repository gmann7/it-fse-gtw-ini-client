/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.enums;

public enum LowLevelDocEnum {

	PRESCRIZIONE("2.16.840.1.113883.2.9.10.1.2","Prescrizione"),
	REFERTO_LABORATORIO("2.16.840.1.113883.2.9.10.1.1","Referto di Laboratorio"),
	PROFILO_SANITARIO_SINTETICO("2.16.840.1.113883.2.9.10.2.4.1.1","Profilo Sanitario Sintetico"),
	PDF("PDF","PDF"),
	TXT("TXT","TXT"),
	LETTERA_DIMISSIONE_OSPEDALIERA("2.16.840.1.113883.2.9.10.1.5","Lettera di Dimissione Ospedaliera"),
	REFERTO_RADIOLOGIA("2.16.840.1.113883.2.9.10.1.7","Referto di Radiologia"),
	EROGATO_SISTEMATS("SistemaTS-Prestazione","Erogato SistemaTS"),
	PRESCRIZIONE_SISTEMATS("SistemaTS-Esenzione","Prescrizione SistemaTS"),
	ESENZIONE_REDDITO_SISTEMATS("SistemaTS-Prescrizione","Esenzione da reddito SistemaTS");

	private String code;
	private String description;

	private LowLevelDocEnum(String inCode, String inDescription) {
		code = inCode;
		description = inDescription;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}