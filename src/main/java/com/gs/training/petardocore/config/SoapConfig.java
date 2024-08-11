package com.gs.training.petardocore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.gs.training.petardocore.soap.client.SoapClient;

@Configuration
public class SoapConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoapConfig.class);

	@Bean
	public Jaxb2Marshaller marshaller() {
		try {
			Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
			marshaller.setContextPath("com.gs.training.petardocore.soap");
			LOGGER.info("MARSHALLER", marshaller);
			return marshaller;
		} catch (Exception e) {
			LOGGER.error("Error creating Jaxb2Marshaller: ", e);
			throw e;
		}
	}

	@Bean
	public SoapClient getSoapClient(Jaxb2Marshaller marshaller) {
		try {
			SoapClient soapClient = new SoapClient();
			soapClient.setDefaultUri("http://www.dneonline.com/calculator.asmx");
			soapClient.setMarshaller(marshaller);
			soapClient.setUnmarshaller(marshaller);
			// LOGGER.info("RESPONSE" + soapClient);
			return soapClient;
		} catch (Exception e) {
			LOGGER.error("RES" + e);
		}
		return getSoapClient(marshaller);
	}

}