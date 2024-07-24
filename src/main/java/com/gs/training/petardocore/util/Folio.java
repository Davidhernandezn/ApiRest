package com.gs.training.petardocore.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
            
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gs.training.petardocore.constant.PetardoCoreConstants;

/**
 * <b>Folio.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: Folio Class
 * @author: CoE Microservicios
 */
@Component
@Order(1)
public class Folio  extends OncePerRequestFilter
{
  public static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

  @Value("${mso.id}")
  private String idMSO;

  public String getFolioWithIdMSO() {
    return idMSO + "-" + ZonedDateTime.now()
    		.withZoneSameInstant(ZoneId.of(PetardoCoreConstants.ZONE_ID))
    		.format(DateTimeFormatter.ofPattern(PetardoCoreConstants.DATE_FORMAT_FOLIO));
  }

  public String generateFolio(String headerFolio) {
    if (headerFolio == null || headerFolio.isBlank() || headerFolio.isEmpty()) {
      String folio = getFolioWithIdMSO();
      MDC.put("currentFolio", folio);
      HOLDER.set(folio);

      return folio;
    }
    else {
      HOLDER.set(headerFolio);
      return headerFolio;
    }
  }

  public Map<String, String> saveFolio(Map<String, String> headers) {
    if (!headers.containsKey(PetardoCoreConstants.FOLIO_TRACE)) {
      headers.put(PetardoCoreConstants.FOLIO_TRACE, Folio.HOLDER.get());
    }
    else {
      headers.replace(PetardoCoreConstants.FOLIO_TRACE, Folio.HOLDER.get());
    }

    return headers;
  }

  public void cleanFolio() {
    HOLDER.remove();
  }

  @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
    {
    String header = request.getHeader(PetardoCoreConstants.FOLIO_TRACE);
    generateFolio(header);
    filterChain.doFilter(request, response);
    }
}
