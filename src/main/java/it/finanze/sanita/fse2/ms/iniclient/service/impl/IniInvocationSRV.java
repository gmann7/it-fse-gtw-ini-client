/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient.service.impl;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXB;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.webjars.NotFoundException;

import it.finanze.sanita.fse2.ms.iniclient.client.IIniClient;
import it.finanze.sanita.fse2.ms.iniclient.config.Constants;
import it.finanze.sanita.fse2.ms.iniclient.dto.DeleteRequestDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.DocumentTreeDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.GetMergedMetadatiDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.IniResponseDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.JWTPayloadDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.JWTTokenDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.MergedMetadatiRequestDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.UpdateRequestDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.response.GetReferenceResponseDTO;
import it.finanze.sanita.fse2.ms.iniclient.enums.ActionEnumType;
import it.finanze.sanita.fse2.ms.iniclient.enums.ProcessorOperationEnum;
import it.finanze.sanita.fse2.ms.iniclient.enums.ResultLogEnum;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.BusinessException;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.NoRecordFoundException;
import it.finanze.sanita.fse2.ms.iniclient.logging.LoggerHelper;
import it.finanze.sanita.fse2.ms.iniclient.repository.entity.IniEdsInvocationETY;
import it.finanze.sanita.fse2.ms.iniclient.repository.mongo.impl.IniInvocationRepo;
import it.finanze.sanita.fse2.ms.iniclient.service.IIniInvocationSRV;
import it.finanze.sanita.fse2.ms.iniclient.utility.RequestUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.StringUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.common.CommonUtility;
import it.finanze.sanita.fse2.ms.iniclient.utility.update.UpdateBodyBuilderUtility;
import lombok.extern.slf4j.Slf4j;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

@Service
@Slf4j
@ConditionalOnProperty(name="ini.client.mock-enable", havingValue="false")
public class IniInvocationSRV implements IIniInvocationSRV {

	private static final String WARNING = "urn:oasis:names:tc:ebxml-regrep:ErrorSeverityType:Warning";
	
	@Autowired
	private IniInvocationRepo iniInvocationRepo;

	@Autowired
	private IIniClient iniClient;

	@Autowired
	private LoggerHelper logger;

	
	@Override
	public IniResponseDTO publishOrReplaceOnIni(final String workflowInstanceId, final ProcessorOperationEnum operation) {
		final Date startingDate = new Date();

		IniEdsInvocationETY iniInvocationETY = iniInvocationRepo.findByWorkflowInstanceId(workflowInstanceId);
		if(iniInvocationETY==null) {
			logger.error("Record non trovato", ProcessorOperationEnum.PUBLISH.getOperation(), 
					ResultLogEnum.KO, startingDate, ProcessorOperationEnum.PUBLISH.getErrorType(), 
					Constants.IniClientConstants.JWT_MISSING_ISSUER_PLACEHOLDER, 
					Constants.IniClientConstants.MISSING_DOC_TYPE_PLACEHOLDER, 
					Constants.IniClientConstants.JWT_MISSING_ISSUER_PLACEHOLDER, 
					Constants.IniClientConstants.JWT_MISSING_SUBJECT, 
					Constants.IniClientConstants.JWT_MISSING_LOCALITY);
			throw new NotFoundException("Record non trovato");
		}

		IniResponseDTO out = null;
		if(ProcessorOperationEnum.PUBLISH.equals(operation)) {
			out = publishByWorkflowInstanceId(iniInvocationETY,startingDate);
		} else if(ProcessorOperationEnum.REPLACE.equals(operation)) {
			out = replaceByWorkflowInstanceId(iniInvocationETY,startingDate);
		}
		return out;
	}

	private IniResponseDTO publishByWorkflowInstanceId(final IniEdsInvocationETY iniInvocationETY, final Date startingDate) {
		DocumentTreeDTO documentTreeDTO = RequestUtility.extractDocumentsFromMetadata(iniInvocationETY.getMetadata());

		String issuer = CommonUtility.extractIssuer(documentTreeDTO);
		String documentType = CommonUtility.extractDocumentType(documentTreeDTO);
		String subjectRole = CommonUtility.extractSubjectRole(documentTreeDTO);
		String fiscalCode = CommonUtility.extractFiscalCodeFromDocumentTree(documentTreeDTO);
		String locality =  CommonUtility.extractLocality(documentTreeDTO);
		IniResponseDTO out = new IniResponseDTO();
		try { 
			RegistryResponseType res = iniClient.sendPublicationData(documentTreeDTO.getDocumentEntry(), documentTreeDTO.getSubmissionSetEntry(), documentTreeDTO.getTokenEntry());
			if (res.getRegistryErrorList() != null && !CollectionUtils.isEmpty(res.getRegistryErrorList().getRegistryError())) {
				StringBuilder errorMsg = new StringBuilder();
				for(RegistryError error : res.getRegistryErrorList().getRegistryError()) {
					if (!WARNING.equals(error.getSeverity())) {
						errorMsg.append(Constants.IniClientConstants.SEVERITY_HEAD_ERROR_MESSAGE).append(error.getSeverity()).append(Constants.IniClientConstants.CODE_HEAD_ERROR_MESSAGE).append(error.getErrorCode());
					}
				}

				if(!StringUtility.isNullOrEmpty(errorMsg.toString())) {
					out.setErrorMessage(errorMsg.toString());
					out.setEsito(false);
				}
			} 

			String message = "Operazione eseguita su INI";
			if(Boolean.FALSE.equals(out.getEsito())) {
				message += ": " + out.getErrorMessage();
				logger.error(message, ProcessorOperationEnum.PUBLISH.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.PUBLISH.getErrorType(), issuer, documentType, subjectRole, fiscalCode, locality);
			} else {
				logger.info(message, ProcessorOperationEnum.PUBLISH.getOperation(), ResultLogEnum.OK, startingDate, issuer, documentType, subjectRole,fiscalCode,locality);
			}
		} catch(Exception ex) {
			logger.error("Errore riscontrato durante l'esecuzione dell'operazione su INI:" + out.getErrorMessage(), 
					ProcessorOperationEnum.PUBLISH.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.PUBLISH.getErrorType(), issuer, documentType, subjectRole,
					fiscalCode, locality);
			throw new BusinessException(ex);
		}

		return out;
	}
	
	private IniResponseDTO replaceByWorkflowInstanceId(final IniEdsInvocationETY iniInvocationETY, final Date startingDate) {
		IniResponseDTO out = new IniResponseDTO();
		DocumentTreeDTO documentTreeDTO = RequestUtility.extractDocumentsFromMetadata(iniInvocationETY.getMetadata());

		String issuer = CommonUtility.extractIssuer(documentTreeDTO);
		String documentType = CommonUtility.extractDocumentType(documentTreeDTO);
		String subjectRole = CommonUtility.extractSubjectRole(documentTreeDTO);
		String fiscalCode = CommonUtility.extractFiscalCodeFromDocumentTree(documentTreeDTO);
		String locality =  CommonUtility.extractLocality(documentTreeDTO);

		try {
			RegistryResponseType res = iniClient.sendReplaceData(documentTreeDTO.getDocumentEntry(), documentTreeDTO.getSubmissionSetEntry(), documentTreeDTO.getTokenEntry(), iniInvocationETY.getRiferimentoIni());
			if (res.getRegistryErrorList() != null && !CollectionUtils.isEmpty(res.getRegistryErrorList().getRegistryError())) {
				StringBuilder errorMsg = new StringBuilder();
				out.setEsito(false);
				for(RegistryError error : res.getRegistryErrorList().getRegistryError()) {
					errorMsg.append(Constants.IniClientConstants.SEVERITY_HEAD_ERROR_MESSAGE).append(error.getSeverity()).append(Constants.IniClientConstants.CODE_HEAD_ERROR_MESSAGE).append(error.getErrorCode());
				}
				
				if(!StringUtility.isNullOrEmpty(errorMsg.toString())) {
					out.setErrorMessage(errorMsg.toString());
					out.setEsito(false);
				}
			}

			String message = "Operazione eseguita su INI";
			if(Boolean.FALSE.equals(out.getEsito())) {
				message += ": " + out.getErrorMessage();
				logger.error(message, ProcessorOperationEnum.REPLACE.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.REPLACE.getErrorType(), issuer, documentType, subjectRole, fiscalCode, locality);
			} else {
				logger.info(message, ProcessorOperationEnum.REPLACE.getOperation(), ResultLogEnum.OK, startingDate, issuer, documentType, subjectRole,fiscalCode,locality);
			}
		} catch(Exception ex) {
			logger.error("Errore riscontrato durante l'esecuzione dell'operazione su INI:" + out.getErrorMessage(), 
					ProcessorOperationEnum.REPLACE.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.REPLACE.getErrorType(), issuer, documentType, subjectRole,
					fiscalCode, locality);
			throw new BusinessException(ex);
		}
		return out;
	}
	 
	
	@Override
	public IniResponseDTO deleteByDocumentId(final DeleteRequestDTO deleteRequestDTO) {
		final Date startingDate = new Date();
		IniResponseDTO out = new IniResponseDTO();
		
		String fiscalCode = CommonUtility.extractFiscalCodeFromJwtSub(deleteRequestDTO.getSub());
		JWTPayloadDTO jwtPayloadDTO = CommonUtility.buildJwtPayloadFromDeleteRequest(deleteRequestDTO);
		try {
			StringBuilder errorMsg = new StringBuilder();
			RegistryResponseType res = iniClient.sendDeleteData(deleteRequestDTO.getIdDoc(),jwtPayloadDTO, deleteRequestDTO.getUuid());
			if (res.getRegistryErrorList() != null && !CollectionUtils.isEmpty(res.getRegistryErrorList().getRegistryError())) {
				for(RegistryError error : res.getRegistryErrorList().getRegistryError()) {
					errorMsg.append(Constants.IniClientConstants.SEVERITY_HEAD_ERROR_MESSAGE).append(error.getSeverity()).append(Constants.IniClientConstants.CODE_HEAD_ERROR_MESSAGE).append(error.getErrorCode());
				}
				
				if(!StringUtility.isNullOrEmpty(errorMsg.toString())) {
					out.setErrorMessage(errorMsg.toString());
					out.setEsito(false);
				}
			}

			if(!StringUtility.isNullOrEmpty(errorMsg.toString())) {
				out.setEsito(false);						
				out.setErrorMessage(errorMsg.toString());
			}
			
			String message = "Operazione eseguita su INI";
			if(Boolean.FALSE.equals(out.getEsito())) {
				message += ": " + out.getErrorMessage();
				logger.error(message, ProcessorOperationEnum.DELETE.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.DELETE.getErrorType(), 
						jwtPayloadDTO.getIss(), deleteRequestDTO.getDocumentType(), jwtPayloadDTO.getSubject_role(), 
						fiscalCode, jwtPayloadDTO.getLocality());
			} else {
				logger.info(message, ProcessorOperationEnum.DELETE.getOperation(), ResultLogEnum.OK, startingDate, jwtPayloadDTO.getIss(), deleteRequestDTO.getDocumentType(), jwtPayloadDTO.getSubject_role(),
						fiscalCode,jwtPayloadDTO.getLocality());
			}
		} catch (Exception ex) {
			logger.error("Errore riscontrato durante l'esecuzione dell'operazione su INI:" + out.getErrorMessage(), 
					ProcessorOperationEnum.DELETE.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.DELETE.getErrorType(), jwtPayloadDTO.getIss(), deleteRequestDTO.getDocumentType(), jwtPayloadDTO.getSubject_role(),
					fiscalCode, jwtPayloadDTO.getLocality());
			throw new BusinessException(ex);
		}
		return out;
	}

	@Override
	public IniResponseDTO updateByRequestBody(SubmitObjectsRequest submitObjectRequest, final UpdateRequestDTO updateRequestDTO) {
		final Date startingDate = new Date();
		IniResponseDTO out = new IniResponseDTO();
		JWTTokenDTO token = new JWTTokenDTO(updateRequestDTO.getToken());
		
		String fiscalCode = CommonUtility.extractFiscalCodeFromJwtSub(token.getPayload().getSub());
		String locality = token.getPayload().getLocality();
		String issuer = token.getPayload().getIss();
		String subjectRole = token.getPayload().getSubject_role();
		try {
			StringBuilder errorMsg = new StringBuilder();
			RegistryResponseType registryResponse = iniClient.sendUpdateData(submitObjectRequest,token);
			if (registryResponse.getRegistryErrorList() != null && !CollectionUtils.isEmpty(registryResponse.getRegistryErrorList().getRegistryError())) {
				for(RegistryError error : registryResponse.getRegistryErrorList().getRegistryError()) {
					if (!WARNING.equals(error.getSeverity())) {
						errorMsg.append(Constants.IniClientConstants.SEVERITY_HEAD_ERROR_MESSAGE).append(error.getSeverity()).append(Constants.IniClientConstants.CODE_HEAD_ERROR_MESSAGE).append(error.getErrorCode());
					}
				}
			}

			if(!StringUtility.isNullOrEmpty(errorMsg.toString())) {
				out.setEsito(false);						
				out.setErrorMessage(errorMsg.toString());
			}
			
			String message = "Operazione eseguita su INI";
			if(Boolean.FALSE.equals(out.getEsito())) {
				message += ": " + out.getErrorMessage();
				logger.error(message, ProcessorOperationEnum.UPDATE.getOperation(), ResultLogEnum.KO, startingDate, ProcessorOperationEnum.UPDATE.getErrorType(), 
						issuer, updateRequestDTO.getDocumentType(),subjectRole ,  
						fiscalCode, locality);
			} else {
				logger.info(message, ProcessorOperationEnum.UPDATE.getOperation(), ResultLogEnum.OK, startingDate, issuer, 
						updateRequestDTO.getDocumentType(), subjectRole, fiscalCode,token.getPayload().getLocality());
			}
		} catch(Exception ex) {
			logger.error("Errore riscontrato durante l'esecuzione dell'operazione su INI:" + out.getErrorMessage(), 
					ProcessorOperationEnum.UPDATE.getOperation(), ResultLogEnum.KO, startingDate, 
					ProcessorOperationEnum.UPDATE.getErrorType(), issuer,updateRequestDTO.getDocumentType(), subjectRole,
					fiscalCode, locality);
			throw new BusinessException(ex);
		}
		return out;
	}


	@Override
	public AdhocQueryResponse getMetadata(String oid, JWTTokenDTO tokenDTO) {
		return iniClient.getReferenceUUID(oid, tokenDTO);
	}

	@Override
	public GetReferenceResponseDTO getReference(final String oid, final JWTTokenDTO tokenDTO) {
		GetReferenceResponseDTO out = new GetReferenceResponseDTO();

		JWTTokenDTO reconfiguredToken = RequestUtility.configureReadTokenPerAction(tokenDTO, ActionEnumType.READ_REFERENCE);

		AdhocQueryResponse response = iniClient.getReferenceUUID(oid, reconfiguredToken);
		StringBuilder sb = new StringBuilder();
		if (response.getRegistryErrorList() != null && !CollectionUtils.isEmpty(response.getRegistryErrorList().getRegistryError())) {
			for(RegistryError error : response.getRegistryErrorList().getRegistryError()) {
				if (error.getCodeContext().equals("No results from the query")) {
					throw new NoRecordFoundException("Non è stato possibile recuperare i riferimenti con i dati forniti in input");
				} else {
					sb.append(error.getCodeContext());
				}
			}
		}

		if(!StringUtility.isNullOrEmpty(sb.toString())){
			out.setErrorMessage(sb.toString());
		} else {
			out.setUuid(response.getRegistryObjectList().getIdentifiable().get(0).getValue().getId());
			String documentType = CommonUtility.extractDocumentTypeFromQueryResponse(response);
			out.setDocumentType(documentType);
		}

		return out;
	}

	 
	@Override
	public GetMergedMetadatiDTO getMergedMetadati(final String oidToUpdate,final MergedMetadatiRequestDTO updateRequestDTO) {
		GetMergedMetadatiDTO out = new GetMergedMetadatiDTO();
		JWTTokenDTO token = new JWTTokenDTO(updateRequestDTO.getToken());

		JWTTokenDTO reconfiguredToken = RequestUtility.configureReadTokenPerAction(new JWTTokenDTO(updateRequestDTO.getToken()), ActionEnumType.READ_REFERENCE);
		AdhocQueryResponse oldMetadata = iniClient.getReferenceUUID(oidToUpdate, reconfiguredToken);
		if(oldMetadata==null) {
			throw new NoRecordFoundException("Nessun metadato trovato");
		}
		out.setDocumentType(CommonUtility.extractDocumentTypeFromQueryResponse(oldMetadata));
		String uuid = oldMetadata.getRegistryObjectList().getIdentifiable().get(0).getValue().getId();
		try (StringWriter sw = new StringWriter();) {
			SubmitObjectsRequest req = UpdateBodyBuilderUtility.buildSubmitObjectRequest(updateRequestDTO,oldMetadata.getRegistryObjectList(), uuid,token);
			JAXB.marshal(req, sw);
			out.setMarshallResponse(sw.toString());
		} catch(Exception ex) {
			out.setErrorMessage("Error while merge metadati:" + ExceptionUtils.getMessage(ex));
			log.error("Error while merge metadati", ex);
		} 
		return out;
	}
	
	 
}
