package com.gs.training.petardocore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>PetardoCoreDto.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreDto Class
 * @author: CoE Microservicios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetardoCoreDto {
	
	/**
	 * Identifier
	 */
	private Long id;

	/**
	 * Name
	 */
	private String name;
}
