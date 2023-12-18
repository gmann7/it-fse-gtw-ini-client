/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 * 
 * Copyright (C) 2023 Ministero della Salute
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package it.finanze.sanita.fse2.ms.iniclient.client.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.developer.JAXWSProperties;
import com.sun.xml.ws.developer.WSBindingProvider;

import ihe.iti.xds_b._2007.DocumentRegistryPortType;
import ihe.iti.xds_b._2007.DocumentRegistryService;
import ihe.iti.xds_b._2010.XDSDeletetWS;
import ihe.iti.xds_b._2010.XDSDeletetWSService;
import it.finanze.sanita.fse2.ms.iniclient.client.IIniClient;
import it.finanze.sanita.fse2.ms.iniclient.config.IniCFG;
import it.finanze.sanita.fse2.ms.iniclient.dto.DocumentEntryDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.JWTPayloadDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.JWTTokenDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.SubmissionSetEntryDTO;
import it.finanze.sanita.fse2.ms.iniclient.enums.ActionEnumType;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.IdDocumentNotFoundException;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.base.BusinessException;
import it.finanze.sanita.fse2.ms.iniclient.service.ISecuritySRV;
import it.finanze.sanita.fse2.ms.iniclient.utility.RequestUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.StringUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.common.SamlHeaderBuilderUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.create.PublishReplaceBodyBuilderUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.delete.DeleteBodyBuilderUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.read.ReadBodyBuilderUtility;
import lombok.extern.slf4j.Slf4j;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.RemoveObjectsRequestType;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

/**
 * Production implemention of Ini Client.
 */
@Slf4j
@Component
public class IniClient implements IIniClient {

	@Autowired
	private IniCFG iniCFG;

	@Autowired
	private SamlHeaderBuilderUtility samlHeaderBuilderUtility;

	@Autowired
	private ISecuritySRV securitySRV;

	private SSLContext sslContext;


	private XDSDeletetWS deletePort;


	private DocumentRegistryPortType port;


	@PostConstruct
	void initialize() {
		try {
			if(!Boolean.TRUE.equals(iniCFG.isMockEnable())) {
				samlHeaderBuilderUtility.initialize();
				if(Boolean.TRUE.equals(iniCFG.isEnableSSL())) {
					sslContext = securitySRV.createSslCustomContext();
					HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
				}

				DocumentRegistryService documentRegistryService = new DocumentRegistryService();
				port = documentRegistryService.getDocumentRegistryPortSoap12();
				if (!StringUtility.isNullOrEmpty(iniCFG.getUrlWsdlDocumentRegistryService())) {
					BindingProvider bindingProvider = (BindingProvider) port;
					bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, iniCFG.getUrlWsdlDocumentRegistryService());

					if (Boolean.TRUE.equals(iniCFG.isEnableLog())) {
						List<Handler> handlerChain = bindingProvider.getBinding().getHandlerChain();
						handlerChain.add(new SOAPLoggingHandler());
						bindingProvider.getBinding().setHandlerChain(handlerChain);
					}

				} 

				if(Boolean.TRUE.equals(iniCFG.isEnableSSL())) {
					((BindingProvider) port).getRequestContext().put(JAXWSProperties.SSL_SOCKET_FACTORY, sslContext.getSocketFactory());
				}

				XDSDeletetWSService deletetWSService = new XDSDeletetWSService();
				deletePort = deletetWSService.getXDSDeletetWSSPort();
				if (!StringUtility.isNullOrEmpty(iniCFG.getUrlWsdlDeletetService())) {
					BindingProvider bindingProvider = (BindingProvider) deletePort;
					bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, iniCFG.getUrlWsdlDeletetService());
				}
			}
		} catch(Exception ex) {
			log.error("Error while initialiting INI context : " , ex);
			throw new BusinessException(ex);
		}
	}


	@Override
	public RegistryResponseType sendPublicationData(final DocumentEntryDTO documentEntryDTO, final SubmissionSetEntryDTO submissionSetEntryDTO, final JWTTokenDTO jwtTokenDTO) {
		log.debug("Call to INI publication");
		List<Header> headers = samlHeaderBuilderUtility.buildHeader(jwtTokenDTO, ActionEnumType.CREATE);
		WSBindingProvider bp = (WSBindingProvider)port;
		bp.setOutboundHeaders(headers);
		SubmitObjectsRequest submitObjectsRequest = PublishReplaceBodyBuilderUtility.buildSubmitObjectRequest(documentEntryDTO, submissionSetEntryDTO, jwtTokenDTO.getPayload(), null);
		return port.documentRegistryRegisterDocumentSetB(submitObjectsRequest);
	}

	@Override
	public RegistryResponseType sendDeleteData(String idDoc, JWTPayloadDTO jwtPayloadDTO, String uuid) {
		log.debug("Call to INI delete");

		JWTTokenDTO deleteToken = new JWTTokenDTO(jwtPayloadDTO);
		List<Header> headers = samlHeaderBuilderUtility.buildHeader(deleteToken, ActionEnumType.DELETE);

		WSBindingProvider bp = (WSBindingProvider)deletePort;
		bp.setOutboundHeaders(headers);

		RemoveObjectsRequestType removeObjectsRequest = DeleteBodyBuilderUtility.buildRemoveObjectsRequest(uuid);
		Holder<RegistryResponseType> holder = new Holder<>();

		deletePort.documentRegistryDeleteDocumentSet(removeObjectsRequest, holder);
		return holder.value;
	}

	@Override
	public RegistryResponseType sendUpdateData(SubmitObjectsRequest submitObjectsRequest, JWTTokenDTO jwtTokenDTO) {
		log.debug("Call to INI update");
		List<Header> headers = samlHeaderBuilderUtility.buildHeader(jwtTokenDTO, ActionEnumType.UPDATE);
		WSBindingProvider bp = (WSBindingProvider)port;
		bp.setOutboundHeaders(headers);

		return port.documentRegistryRegisterDocumentSetB(submitObjectsRequest);
	}

	@Override
	public RegistryResponseType sendReplaceData(final DocumentEntryDTO documentEntryDTO, final SubmissionSetEntryDTO submissionSetEntryDTO,
			final JWTTokenDTO jwtTokenDTO, final String uuid) {
		log.debug("Call to INI replace");

		// Reconfigure token and build request
		List<Header> headers = samlHeaderBuilderUtility.buildHeader(jwtTokenDTO, ActionEnumType.REPLACE);
		WSBindingProvider bp = (WSBindingProvider)port;
		bp.setOutboundHeaders(headers);

		SubmitObjectsRequest submitObjectsRequest = PublishReplaceBodyBuilderUtility.buildSubmitObjectRequest(documentEntryDTO, submissionSetEntryDTO, jwtTokenDTO.getPayload(), uuid);

		return port.documentRegistryRegisterDocumentSetB(submitObjectsRequest);

	}

	@Override
	public AdhocQueryResponse getReferenceUUID(String idDoc, String tipoRicerca,JWTTokenDTO tokenDTO) {
		log.debug("Call to INI get reference");

		List<Header> headers = samlHeaderBuilderUtility.buildHeader(tokenDTO, ActionEnumType.READ_REFERENCE);
		WSBindingProvider bp = (WSBindingProvider)port;
		bp.setOutboundHeaders(headers);

		AdhocQueryRequest adhocQueryRequest = ReadBodyBuilderUtility.buildAdHocQueryRequest(idDoc, tipoRicerca,ActionEnumType.READ_REFERENCE);
		return port.documentRegistryRegistryStoredQuery(adhocQueryRequest);
	}

	@Override
	public AdhocQueryResponse getReferenceMetadata(String uuid, String tipoRicerca, JWTTokenDTO jwtToken) {
		log.debug("Call to INI get reference metadata");

		JWTTokenDTO reconfiguredToken = RequestUtility.configureReadTokenPerAction(jwtToken, ActionEnumType.READ_METADATA);
		List<Header> headers = samlHeaderBuilderUtility.buildHeader(reconfiguredToken, ActionEnumType.READ_METADATA);

		WSBindingProvider bp = (WSBindingProvider)port;
		bp.setOutboundHeaders(headers);

		AdhocQueryRequest adhocQueryRequest = ReadBodyBuilderUtility.buildAdHocQueryRequest(uuid,tipoRicerca, ActionEnumType.READ_METADATA);
		AdhocQueryResponse response = port.documentRegistryRegistryStoredQuery(adhocQueryRequest);
		if (response.getRegistryErrorList()!=null) {
			for(RegistryError error : response.getRegistryErrorList().getRegistryError()) {
				if (error.getCodeContext().equals("No results from the query")) {
					throw new IdDocumentNotFoundException(error.getCodeContext());
				}
			}
			throw new BusinessException("Errore riscontrato su INI");
		}
		return response;

	}


}
