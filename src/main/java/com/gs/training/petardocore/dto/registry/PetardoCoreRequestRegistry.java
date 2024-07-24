package com.gs.training.petardocore.dto.registry;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>PetardoCoreRequestRegistry.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreRequestRegistry Class
 * @author: CoE Microservicios
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetardoCoreRequestRegistry {
	
	/**
	 * <strong>serialVersionUID</strong>
	 **/
	private static final long serialVersionUID = 1L;

	/**
	 * PetardoCore identifier
	 */
	@NotNull(message = "The idPetardoCore field is required")
	@Valid
	private String idPetardoCore;

	/**
	 * Name
	 */
	@NotNull(message = "The name field is required")
	@Valid
	private String name;
}
