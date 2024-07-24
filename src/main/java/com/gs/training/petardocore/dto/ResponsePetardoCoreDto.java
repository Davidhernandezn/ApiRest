package com.gs.training.petardocore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>ResponsePetardoCoreDto.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: ResponsePetardoCoreDto Class
 * @author: CoE Microservicios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePetardoCoreDto {
	
	/**
	 * message.
	 */
	private String message;
	/**
	 * folio.
	 */
	private String folio;
	/**
	 * list
	 */
	private List<PetardoCoreDto> result;
}
