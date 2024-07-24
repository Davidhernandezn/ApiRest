package com.gs.training.petardocore.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.gs.training.petardocore.enums.EnumHttpMessages;
import com.gs.training.petardocore.exception.GenericException;

/**
 * <b>HeadersAspect.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @description: HeadersAspect Class
 * @author: CoE Microservicios
 */
@Aspect
@Component
@ComponentScan
public class HeadersAspect {
	
	/**
	 * Method that requires the annotation of ValidatorHeaderAnnotation so that the
	 * execution of the aspect can be performed.
	 *
	 * @param joinPoint the join point
	 */
	@Before(value = "@annotation(ValidatorHeaderAnnotation)")
	public void validateRequiredHeaders(JoinPoint joinPoint) {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Parameter[] parameters = method.getParameters();

		if (Arrays.stream(parameters).anyMatch(p -> p.isAnnotationPresent(RequestHeader.class))) {
			
			Optional<Parameter> optional = Arrays.stream(parameters).filter(p -> p.isAnnotationPresent(RequestHeader.class))
					.findFirst();
			Parameter parameter = null;

			if (optional.isPresent()) {
				
				parameter = optional.get();
			}

			Map<String, String> headers = (Map<String, String>) joinPoint.getArgs()[Arrays.asList(parameters)
					.indexOf(parameter)];

			List<String> missingHeaders = new ArrayList<>();

			for (String h : method.getAnnotation(ValidatorHeaderAnnotation.class).required()) {
				
				if (!headers.containsKey(h) || headers.get(h) == null ||
						 headers.get(h).isEmpty() || "null".equals(headers.get(h))) {
					
					missingHeaders.add(h);
				}
			}
			
			if (!missingHeaders.isEmpty()) {
				
				throw new GenericException(
						Collections.singletonList("The headers " + missingHeaders + " are required or have no value"),
						EnumHttpMessages.E400);
			}
		}
	}
}
