package com.gs.training.petardocore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gs.ftt.log.MainLog;

/**
 * <b>PetardoCoreApplication.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreApplication Class
 * @author: CoE Microservicios
 */
@SpringBootApplication 
public class PetardoCoreApplication {

	/**
	 * Clase main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MainLog.generateUID();
		SpringApplication.run(PetardoCoreApplication.class, args);
	}
}
