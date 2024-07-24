package com.gs.training.petardocore.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <b>Converters.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: Converters Class
 * @author: CoE Microservicios
 */
@Component
public class Converters {
  /**
   * @param object
   * @return String
   * @throws JsonProcessingException
   */
  public String convertObjectToJson(Object object) throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }
}
