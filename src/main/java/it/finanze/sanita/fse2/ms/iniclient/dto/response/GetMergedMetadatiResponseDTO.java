/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.dto.response;

import it.finanze.sanita.fse2.ms.iniclient.dto.GetMergedMetadatiDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMergedMetadatiResponseDTO extends ResponseDTO {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 5457503502983726876L;
	
	private GetMergedMetadatiDTO response;

	public GetMergedMetadatiResponseDTO() {
		super();
	}

	public GetMergedMetadatiResponseDTO(final LogTraceInfoDTO traceInfo, final GetMergedMetadatiDTO inResponse) {
		super(traceInfo);
		response = inResponse;
	}

}

