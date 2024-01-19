package it.finanze.sanita.fse2.ms.iniclient.controller.impl;

import it.finanze.sanita.fse2.ms.iniclient.controller.IIssuerCTL;
import it.finanze.sanita.fse2.ms.iniclient.dto.IssuerCreateRequestDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.response.IniTraceResponseDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.response.IssuerDeleteResponseDTO;
import it.finanze.sanita.fse2.ms.iniclient.dto.response.IssuerResponseDTO;
import it.finanze.sanita.fse2.ms.iniclient.exceptions.base.BusinessException;
import it.finanze.sanita.fse2.ms.iniclient.service.IIssuerSRV;
import it.finanze.sanita.fse2.ms.iniclient.utility.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class IssuerCTL extends AbstractCTL implements IIssuerCTL {

    @Autowired
    private IIssuerSRV issuerSRV;

    @Override
    public IssuerResponseDTO create(IssuerCreateRequestDTO requestBody, HttpServletRequest request) {
        if (StringUtility.isNullOrEmpty(requestBody.getIssuer())){
            String message = String.format("La stringa issuer deve essere valorizzata");
            log.error(message);
            throw new BusinessException(message);
        }
        IssuerResponseDTO response = issuerSRV.createIssuer(requestBody);
        response.setTraceID(getLogTraceInfo().getTraceID());
        response.setSpanID(getLogTraceInfo().getSpanID());
        return response;
    }

    @Override
    public IssuerDeleteResponseDTO delete(String issuer, HttpServletRequest request) {
        IssuerDeleteResponseDTO response = issuerSRV.removeIssuer(issuer);
        response.setTraceID(getLogTraceInfo().getTraceID());
        response.setSpanID(getLogTraceInfo().getSpanID());
        return response;
    }
}
