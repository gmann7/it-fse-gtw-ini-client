/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JWTPayloadDTO extends AbstractDTO {

	public JWTPayloadDTO() {
		
	}
	/**
	 * Identificativo dell’entità che ha generato il token.
	 * Valorizzato con l’identificativo della struttura utente.
	 * Codifica ISTAT della Azienda (ASL) concatenato alla codifica HSP.11 -
	 * HSP.11bis - STS.11 - RIA.11. Nel caso di nodo regionale valorizzato con il
	 * codice ISTAT della regione.
	 */
	private String iss;

	/**
	 * Numero intero (timestamp in secondi) che indica il momento in cui il token è
	 * stato generato, serve per conoscere l’età di un token.
	 */
	private int iat;

	/**
	 * Numero intero (timestamp in secondi) che indica fino a quando il token sarà
	 * valido.
	 */
	private int exp;

	/**
	 * Identificativo univoco del token, serve per prevenire la generazione
	 * accidentale di token uguali.
	 */
	private String jti;

	/**
	 * Indica il destinatario per cui è stato creato il token.
	 */
	private String aud;

	/**
	 * Codice Fiscale dell’utente che fa richiesta del servizio di interoperabilità.
	 * Formato codifica conforme alle specifiche IHE (ITI TF-3).
	 */
	private String sub;

	private String subject_organization;

	/**
	 * Identificativo del dominio dell’utente (vedi TABELLA ORGANIZZAZIONE).
	 */
	private String subject_organization_id;

	/**
	 * Identificativo della struttura utente.
	 * Codifica ISTAT della Azienda (ASL) concatenato alla codifica HSP.11 -
	 * HSP.11bis - STS.11 - RIA.11.
	 * Nel caso di accesso da parte della struttura coincide con l’issuer.
	 * Nel caso di ruolo APR assume il valore del codice ISTAT dell’Azienda (ASL)
	 */
	private String locality;

	/**
	 * Ruolo dell’utente che effettua la richiesta, vedi TABELLA RUOLO.
	 */
	private String subject_role;

	/**
	 * Codice Fiscale dell’assistito cui si riferisce la richiesta o del
	 * genitore/tutore che ha richiesto l’operazione
	 * Codice fiscale dell’assistito, del genitore o del tutore, codificato secondo
	 * il tipo di dato CX HL7 V2.5 (per come indicato alle specifiche IHE TF-3).
	 */
	private String person_id;

	/**
	 * Indica la presa in carico del paziente.
	 * Valore booleano.
	 */
	private Boolean patient_consent;

	/**
	 * Contesto operativo della richiesta
	 * Vedi TABELLA CONTESTO OPERATIVO.
	 */
	private String purpose_of_use;

	/**
	 * Tipo di documento da registrare
	 * Codifica LOINC nel formato ('code1^^coding-scheme1','code2^^coding-scheme2').
	 */
	private String resource_hl7_type;

	/**
	 * Descrive il tipo di attività: CREATE, READ, UPDATE, DELETE.
	 * Vedi TABELLA TIPO ATTIVITA’.
	 */
	private String action_id;

	private String attachment_hash;
}
