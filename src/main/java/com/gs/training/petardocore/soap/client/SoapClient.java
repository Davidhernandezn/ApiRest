package com.gs.training.petardocore.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import com.gs.training.petardocore.soap.Add;
import com.gs.training.petardocore.soap.AddResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public class SoapClient extends WebServiceGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoapClient.class);

	/**
	 * public SoapClient(Jaxb2Marshaller marshaller) {
	 * this.setDefaultUri("http://www.dneonline.com/calculator.asmx");
	 * this.setMarshaller(marshaller); this.setUnmarshaller(marshaller); }
	 **/

	public AddResponse getAddResponse(int numberA, int numberB) {
		try {
		Add addRequest = new Add();
		addRequest.setIntA(numberA);
		addRequest.setIntB(numberB);

		// Asegúrate de que la SOAP Action esté correctamente especificada
		SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Add");

		// Envía la solicitud y recibe la respuesta
		AddResponse addResponse = (AddResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", addRequest, soapActionCallback);

		LOGGER.info("SOAP Response: {}", addResponse.getAddResult());
		return addResponse;
		} catch (Exception e) {
			LOGGER.error("Error occurred while sending SOAP request: ", e);
		throw new RuntimeException("Failed to get response from SOAP service", e);
		}
	}}