/*
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */
package it.finanze.sanita.fse2.ms.iniclient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import it.finanze.sanita.fse2.ms.iniclient.dto.*;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import it.finanze.sanita.fse2.ms.iniclient.client.IIniClient;
import it.finanze.sanita.fse2.ms.iniclient.config.Constants;
import it.finanze.sanita.fse2.ms.iniclient.enums.ProcessorOperationEnum;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.BusinessException;
import it.finanze.sanita.fse2.ms.iniclient.repository.entity.IniEdsInvocationETY;
import it.finanze.sanita.fse2.ms.iniclient.service.IIniInvocationSRV;
import it.finanze.sanita.fse2.ms.iniclient.utility.JsonUtility;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

import javax.xml.bind.JAXBException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(Constants.Profile.TEST)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IniInvocationTest {

    @Autowired
    private IIniInvocationSRV iniInvocationSRV;

    @Autowired
    private MongoTemplate mongoTemplate;

    @MockBean
    private IIniClient iniClient;

    @BeforeEach
    void dataInit() {
        Document entity = JsonUtility.jsonToObject(TestConstants.TEST_INI_EDS_ENTRY, Document.class);
        mongoTemplate.save(entity, Constants.Profile.TEST_PREFIX + Constants.Collections.INI_EDS_INVOCATION);
    }

    @AfterEach
    void dropCollection() {
        mongoTemplate.dropCollection(IniEdsInvocationETY.class);
    }

    @Test
    @DisplayName("Publish - success test")
    void publishSuccessTest() {
        Mockito.when(iniClient.sendPublicationData(any(DocumentEntryDTO.class), any(SubmissionSetEntryDTO.class), any(JWTTokenDTO.class)))
                .thenReturn(new RegistryResponseType());
        IniResponseDTO response = iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.PUBLISH);
        assertTrue(response.getEsito());
        assertNull(response.getErrorMessage());
    }

    @Test
    @DisplayName("Publish - error test")
    void publishErrorTest() {
        Mockito.when(iniClient.sendPublicationData(any(DocumentEntryDTO.class), any(SubmissionSetEntryDTO.class), any(JWTTokenDTO.class)))
                .thenThrow(new BusinessException(""));
        assertThrows(BusinessException.class, () -> iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.PUBLISH));
    }

    @Test
    @DisplayName("Publish - error response test")
    void publishErrorResponseTest() {
        RegistryResponseType registryResponseType = TestUtility.mockRegistryError();
        Mockito.when(iniClient.sendPublicationData(any(DocumentEntryDTO.class), any(SubmissionSetEntryDTO.class), any(JWTTokenDTO.class)))
                .thenReturn(registryResponseType);
        IniResponseDTO response = iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.PUBLISH);
        assertFalse(response.getEsito());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    @DisplayName("Replace - success test")
    void replaceSuccessTest() {
        RegistryResponseType registryResponseType = TestUtility.mockRegistrySuccess();
        Mockito.when(iniClient.sendReplaceData(any(), any(), any(),any()))
                .thenReturn(registryResponseType);
        IniResponseDTO response = iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.REPLACE);
        assertTrue(response.getEsito());
        assertNull(response.getErrorMessage());
    }

    @Test
    @DisplayName("Replace - error test")
    void replaceErrorTest() {
        Mockito.when(iniClient.sendReplaceData(any(), any(), any(), any())).thenThrow(new BusinessException(""));
        ReplaceRequestDTO requestDTO = new ReplaceRequestDTO();
        requestDTO.setRiferimentoIni("identificativoDoc");
        requestDTO.setWorkflowInstanceId(TestConstants.TEST_WII);
        assertThrows(BusinessException.class, () -> iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.REPLACE));
    }

    @Test
    @DisplayName("Replace - error response test")
    void replaceErrorResponseTest() {
        RegistryResponseType registryResponseType = TestUtility.mockRegistryError();
        Mockito.when(iniClient.sendReplaceData(any(), any(), any(),any()))
                .thenReturn(registryResponseType);
        ReplaceRequestDTO requestDTO = new ReplaceRequestDTO();
        requestDTO.setRiferimentoIni("identificativoDoc");
        requestDTO.setWorkflowInstanceId(TestConstants.TEST_WII);
        IniResponseDTO response = iniInvocationSRV.publishOrReplaceOnIni(TestConstants.TEST_WII, ProcessorOperationEnum.REPLACE);
        assertFalse(response.getEsito());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    @DisplayName("Update - success test")
    void updateSuccessTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        RegistryResponseType registryResponseType = TestUtility.mockRegistrySuccess();
        Mockito.when(iniClient.sendUpdateData(any(), any())).thenReturn(registryResponseType);

        String json = TestConstants.TEST_UPDATE_REQ_NEW ;
        UpdateRequestDTO updateRequestDTO = JsonUtility.jsonToObject(json, UpdateRequestDTO.class);
        IniResponseDTO iniResponse = iniInvocationSRV.updateByRequestBody(new SubmitObjectsRequest(),updateRequestDTO);
        assertTrue(iniResponse.getEsito());
        assertNull(iniResponse.getErrorMessage());
    }

    @Test
    @DisplayName("Update - error test")
    void updateErrorTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.sendUpdateData(any(), any()))
                .thenThrow(new BusinessException(""));
        UpdateRequestDTO updateRequestDTO = JsonUtility.jsonToObject(TestConstants.TEST_UPDATE_REQ_NEW, UpdateRequestDTO.class);
        assertThrows(BusinessException.class, () -> iniInvocationSRV.updateByRequestBody(new SubmitObjectsRequest(),updateRequestDTO));
    }

    @Test
    @DisplayName("Update - error response test")
    void updateErrorResponseTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        RegistryResponseType registryResponseType = TestUtility.mockRegistryError();
        Mockito.when(iniClient.sendUpdateData(any(), any())).thenReturn(registryResponseType);
        UpdateRequestDTO updateRequestDTO = JsonUtility.jsonToObject(TestConstants.TEST_UPDATE_REQ_NEW, UpdateRequestDTO.class);
        IniResponseDTO iniResponse = iniInvocationSRV.updateByRequestBody(null,updateRequestDTO);
        assertFalse(iniResponse.getEsito());
        assertNotNull(iniResponse.getErrorMessage());
    }

    @Test
    @DisplayName("Delete - success test")
    void deleteSuccessTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.sendDeleteData(any(), any(), any()))
                .thenReturn(new RegistryResponseType());
        DeleteRequestDTO deleteRequestDTO = JsonUtility.jsonToObject(TestConstants.TEST_DELETE_REQ, DeleteRequestDTO.class);
        IniResponseDTO iniResponse = iniInvocationSRV.deleteByDocumentId(deleteRequestDTO);
        assertTrue(iniResponse.getEsito());
        assertNull(iniResponse.getErrorMessage());
    }

    @Test
    @DisplayName("Delete - error test")
    void deleteErrorTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.sendDeleteData(any(), any(), any()))
                .thenThrow(new BusinessException(""));
        DeleteRequestDTO deleteRequestDTO = JsonUtility.jsonToObject(TestConstants.TEST_DELETE_REQ, DeleteRequestDTO.class);
        assertThrows(BusinessException.class, () ->iniInvocationSRV.deleteByDocumentId(deleteRequestDTO));
    }

    @Test
    @DisplayName("Delete - error response test")
    void deleteErrorResponseTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        RegistryResponseType registryResponseType = TestUtility.mockRegistryError();
        Mockito.when(iniClient.sendDeleteData(any(), any(), any()))
                .thenReturn(registryResponseType);
        DeleteRequestDTO deleteRequestDTO = JsonUtility.jsonToObject(TestConstants.TEST_DELETE_REQ, DeleteRequestDTO.class);
        IniResponseDTO iniResponse = iniInvocationSRV.deleteByDocumentId(deleteRequestDTO);
        assertFalse(iniResponse.getEsito());
        assertNotNull(iniResponse.getErrorMessage());
    }

    @Test
    @DisplayName("Get Metadata - success test")
    void getMetadatiSuccessTest() throws JAXBException {
        AdhocQueryResponse response = TestUtility.mockQueryResponse();
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        Mockito.when(iniClient.getReferenceMetadata(anyString(), any(JWTTokenDTO.class)))
                .thenReturn(response);
        AdhocQueryResponse apiResponse = iniInvocationSRV.getMetadata("oid", TestUtility.mockBasicToken());
        assertEquals(response, apiResponse);
        assertNotNull(apiResponse);
        assertNotNull(apiResponse.getRegistryObjectList());
        assertNotEquals(0, apiResponse.getRegistryObjectList().getIdentifiable().size());
    }

    @Test
    @DisplayName("Get Metadata - error test when get reference uuid fails")
    void getMetadatiErrorWhenGetReferenceErrorTest() {
        Mockito.when(iniClient.getReferenceUUID(anyString(), any(JWTTokenDTO.class)))
                .thenThrow(new BusinessException(""));
        assertThrows(BusinessException.class, () -> iniInvocationSRV.getMetadata("oid", TestUtility.mockBasicToken()));
    }
}
