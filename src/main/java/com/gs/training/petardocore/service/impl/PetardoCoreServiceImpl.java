package com.gs.training.petardocore.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.dto.PetardoCoreDto;
import com.gs.training.petardocore.dto.registry.PetardoCoreRequestRegistry;
import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.exception.GenericException;
import com.gs.training.petardocore.exception.ExceptionsManager;
import com.gs.training.petardocore.service.IPetardoCoreService;
import com.gs.ftt.log.LogMonitor;

/**
 * <b>PetardoCoreServiceImpl.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: PetardoCoreServiceImpl Class
 * @author: CoE Microservicios
 */
@Service
public class PetardoCoreServiceImpl implements IPetardoCoreService {
	
	/**
	 * Logger implementacion FTT
	 */
	private static final LogMonitor LOGGER = new LogMonitor(PetardoCoreServiceImpl.class);

	/**
	 * Consult ts-demo-base-v1.
	 *
	 * @param id
	 * @return GenericResponse<List < PetardoCoreDto>>
	 */
	@Override
	public List<PetardoCoreDto> consultPetardoCore(String id) {

		try {

			List<PetardoCoreDto> result = new ArrayList<>();

			result.add(PetardoCoreDto.builder()
					.id(Long.valueOf(id))
					.name("CoE Microservices")
					.build());

			return result;
			
		}
		catch (RuntimeException e) {
			
			LOGGER.error(PetardoCoreConstants.LOG_ERROR, e, e.getStackTrace());
			List<String> details = List.of(PetardoCoreConstants.DETAILS_EXCEPTION_500);
			GenericException genericException = new GenericException(details, EnumHttpMessages.E500);

			throw ExceptionsManager.returnGenericException(genericException);
		}
	}

	/**
	 * @param PetardoCoreRequestRegistry
	 */
	@Override
	public List<PetardoCoreDto> createPetardoCore(PetardoCoreRequestRegistry requestRegistry) {

		try {

			List<PetardoCoreDto> result = new ArrayList<>();

			result.add(PetardoCoreDto.builder()
					.id(Long.valueOf(requestRegistry.getIdPetardoCore()))
					.name(requestRegistry.getName())
					.build());

			return result;
			
		}
		catch (RuntimeException e) {
			
			LOGGER.error(PetardoCoreConstants.LOG_ERROR, e, e.getStackTrace());
			List<String> details = List.of(PetardoCoreConstants.DETAILS_EXCEPTION_500);
			GenericException genericException = new GenericException(details, EnumHttpMessages.E500);

			throw ExceptionsManager.returnGenericException(genericException);
		}
	}
}
