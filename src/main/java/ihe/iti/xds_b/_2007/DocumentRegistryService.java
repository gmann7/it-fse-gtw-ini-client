
package ihe.iti.xds_b._2007;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DocumentRegistry_Service", targetNamespace = "urn:ihe:iti:xds-b:2007", wsdlLocation = "file:/C:/Users/066070758/Desktop/INI_NEW/wsdl/XDS.b_DocumentRegistry.wsdl")
public class DocumentRegistryService
    extends Service
{

    private final static URL DOCUMENTREGISTRYSERVICE_WSDL_LOCATION;
    private final static WebServiceException DOCUMENTREGISTRYSERVICE_EXCEPTION;
    private final static QName DOCUMENTREGISTRYSERVICE_QNAME = new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Service");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/066070758/Desktop/INI_NEW/wsdl/XDS.b_DocumentRegistry.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DOCUMENTREGISTRYSERVICE_WSDL_LOCATION = url;
        DOCUMENTREGISTRYSERVICE_EXCEPTION = e;
    }

    public DocumentRegistryService() {
        super(__getWsdlLocation(), DOCUMENTREGISTRYSERVICE_QNAME);
    }

    public DocumentRegistryService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DOCUMENTREGISTRYSERVICE_QNAME, features);
    }

    public DocumentRegistryService(URL wsdlLocation) {
        super(wsdlLocation, DOCUMENTREGISTRYSERVICE_QNAME);
    }

    public DocumentRegistryService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DOCUMENTREGISTRYSERVICE_QNAME, features);
    }

    public DocumentRegistryService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DocumentRegistryService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DocumentRegistryPortType
     */
    @WebEndpoint(name = "DocumentRegistry_Port_Soap12")
    public DocumentRegistryPortType getDocumentRegistryPortSoap12() {
        return super.getPort(new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Port_Soap12"), DocumentRegistryPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DocumentRegistryPortType
     */
    @WebEndpoint(name = "DocumentRegistry_Port_Soap12")
    public DocumentRegistryPortType getDocumentRegistryPortSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("urn:ihe:iti:xds-b:2007", "DocumentRegistry_Port_Soap12"), DocumentRegistryPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DOCUMENTREGISTRYSERVICE_EXCEPTION!= null) {
            throw DOCUMENTREGISTRYSERVICE_EXCEPTION;
        }
        return DOCUMENTREGISTRYSERVICE_WSDL_LOCATION;
    }

}
