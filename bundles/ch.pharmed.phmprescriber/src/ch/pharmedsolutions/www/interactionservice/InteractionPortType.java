
package ch.pharmedsolutions.www.interactionservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.6-1b01 Generated
 * source version: 2.2
 *
 */
@WebService(name = "InteractionPortType", targetNamespace = "https://www.pharmedsolutions.ch/InteractionService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
public interface InteractionPortType {

	/**
	 * Check interactions between different substances (based on ATC-Codes).
	 *
	 * @param interactionrequest
	 * @return returns https.www_pharmedsolutions_ch.interactionservice.Interactions
	 */
	@WebMethod(action = "https://www.pharmedsolutions.ch/InteractionService#checkInteraction")
	@WebResult(name = "Interactions", targetNamespace = "https://www.pharmedsolutions.ch/InteractionService", partName = "interactionresponse")
	public Interactions checkInteraction(
			@WebParam(name = "InteractionRequest", targetNamespace = "https://www.pharmedsolutions.ch/InteractionService", partName = "interactionrequest") InteractionRequest interactionrequest);

}
