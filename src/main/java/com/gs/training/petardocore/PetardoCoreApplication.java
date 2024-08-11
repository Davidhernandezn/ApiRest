package com.gs.training.petardocore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.gs.ftt.log.MainLog;
import com.gs.training.petardocore.soap.AddResponse;
import com.gs.training.petardocore.soap.client.SoapClient;

/**
 * <b>PetardoCoreApplication.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreApplication Class
 * @author: CoE Microservicios
 */
//@ComponentScan(basePackages = {"com.gs.training.petardocore", "com.gs.training.petardocore.soap"})
@SpringBootApplication
public class PetardoCoreApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApplication.class);

	/**
	 * Clase main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MainLog.generateUID();
		SpringApplication.run(PetardoCoreApplication.class, args);
	}

	@Bean
	CommandLineRunner init(SoapClient soapClient) {
		return args -> {
			try {
				AddResponse addResponse = soapClient.getAddResponse(2, 2);
				LOGGER.info("RESPONSE" + addResponse);
				LOGGER.info("RESPONSE" + addResponse.getAddResult());
				LOGGER.info("El resultado de la suma es {}", addResponse.getAddResult());
			} catch (Exception e) {
				LOGGER.error("Error al obtener la respuesta del servicio SOAP", e);
			}
		};
	}

}
