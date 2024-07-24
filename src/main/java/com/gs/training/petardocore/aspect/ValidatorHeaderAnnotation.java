package com.gs.training.petardocore.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>ValidatorHeaderAnnotation.java</b>
 *
 * @version: ts-demo-base-v1 1.0
 * @descripcion: Validate that the headers placed in the required section are
 *               present in the request. Also, validate that it is not null,
 *               empty, or blank.
 * @author: CoE Microservicios
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidatorHeaderAnnotation {

	String[] required() default {};
}
