package com.gs.training.petardocore.controller;

import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gs.training.petardocore.aspect.ValidatorHeaderAnnotation;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.dto.registry.PetardoCoreRequestRegistry;
import com.gs.training.petardocore.model.GenericResponse;
import com.gs.training.petardocore.service.IPetardoCoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.gs.ftt.log.LogMonitor;

/**
 * <b>PetardoCoreController.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: PetardoCoreController Class
 * @author: CoE Microservicios
 */
@RestController
@RequestMapping("${basePath}")
public class PetardoCoreController {
	
  /**
   * <strong>HLOGGER</strong>
   * <ul>
   * <li><strong>Description:</strong> LOGGER</li>
   * </ul>
   **/
  private static final LogMonitor LOGGER = new LogMonitor(PetardoCoreController.class);

  /**
   * Service PetardoCoreClient.
   */
  private final IPetardoCoreService iPetardoCoreService;

  /**
   * Constructor
   *
   * @param iPetardoCoreService request of ts-demo-base-v1
   */
  public PetardoCoreController(IPetardoCoreService iPetardoCoreService) {
  	
    this.iPetardoCoreService = iPetardoCoreService;
  }

  /**
   * Consult ts-demo-base-v1
   *
   * @param headers headers
   * @param id
   * @return GenericResponse<List < PetardoCoreDto>>
   */
  @GetMapping(PetardoCoreConstants.PATH_CONTROLLER_GET_ID)
  @ResponseStatus(value = HttpStatus.OK)
  @ValidatorHeaderAnnotation(required = {PetardoCoreConstants.USER_HEADER})
  public GenericResponse consultPetardoCore(@RequestHeader Map<String, String> headers, 
  		@PathVariable(required = true) String id) {
  	
    LOGGER.info("GET /ts-demo-base-v1 request= {}", id);
    return new GenericResponse(this.iPetardoCoreService.consultPetardoCore(id));
  }

  /**
   * Create ts-demo-base-v1
   *
   * @param requestRegistry
   * @return GenericResponse<List < PetardoCoreDto>>
   */
  @PostMapping(PetardoCoreConstants.PATH_CONTROLLER)
  @ResponseStatus(value = HttpStatus.OK)
  public GenericResponse createPetardoCore(@Valid @RequestBody PetardoCoreRequestRegistry requestRegistry) {
  	
    LOGGER.info("POST /ts-demo-base-v1 request= {}", requestRegistry);
    return new GenericResponse(this.iPetardoCoreService.createPetardoCore(requestRegistry));
  }
}
