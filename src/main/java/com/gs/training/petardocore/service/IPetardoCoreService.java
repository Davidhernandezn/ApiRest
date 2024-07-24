package com.gs.training.petardocore.service;

import java.util.List;

import com.gs.training.petardocore.dto.PetardoCoreDto;
import com.gs.training.petardocore.dto.registry.PetardoCoreRequestRegistry;

/**
 * <b>IPetardoCoreService.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: IPetardoCoreService Class
 * @author: CoE Microservicios
 */
public interface IPetardoCoreService {

	/**
	 * Consult ts-demo-base-v1
	 *
	 * @param String   id
	 * @return List<PetardoCoreDto>
	 */
	List<PetardoCoreDto> consultPetardoCore(String id);

	/**
	 * Create ts-demo-base-v1
	 *
	 * @param requestRegistry
	 * @return List<PetardoCoreDto>
	 */
	List<PetardoCoreDto> createPetardoCore(PetardoCoreRequestRegistry requestRegistry);
}
