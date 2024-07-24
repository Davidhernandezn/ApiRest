package com.gs.training.petardocore.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gs.training.petardocore.enums.EnumHttpMessages;

class GenericExceptionTest
{
	@Test
	void testConstructorAndGetters()
	{
		List<String> details = Arrays.asList("Error 1", "Error 2", "Error 3");
		EnumHttpMessages enumHttpMessages = EnumHttpMessages.E400;

		GenericException genericException = new GenericException(details, enumHttpMessages);

		// Verify that the details and enumHttpMessages are as expected.
		assertEquals(details, genericException.getDetails());
		assertEquals(enumHttpMessages, genericException.getEnumHttpMessages());

		// Verify that the exception message is the first detail
		assertEquals("Error 1", genericException.getMessage());
	}

	@Test
	void testEqualsAndHashCode()
	{
		List<String> details1 = Arrays.asList("Error 1", "Error 2");
		List<String> details2 = Arrays.asList("Error 1", "Error 2");
		EnumHttpMessages enumHttpMessages1 = EnumHttpMessages.E400;
		EnumHttpMessages enumHttpMessages2 = EnumHttpMessages.E500;

		GenericException genericException1 = new GenericException(details1, enumHttpMessages1);
		GenericException genericException2 = new GenericException(details2, enumHttpMessages2);
		GenericException genericException3 = new GenericException(details1, enumHttpMessages1);

		// Verify equals
		assertEquals(genericException1, genericException3);
		assertNotEquals(genericException1, genericException2);

		// Verify hashCode
		assertEquals(genericException1.hashCode(), genericException3.hashCode());
		assertNotEquals(genericException1.hashCode(), genericException2.hashCode());
	}

	@Test
	void testToString()
	{
		List<String> details = Arrays.asList("Error 1", "Error 2");
		EnumHttpMessages enumHttpMessages = EnumHttpMessages.E400;

		GenericException genericException = new GenericException(details, enumHttpMessages);

		// Verify toString
		String expectedToString = "GenericException(enumHttpMessages=E400, details=[Error 1, Error 2])";
		assertEquals(expectedToString, genericException.toString());
	}

	@Test
	void testHashCode()
	{
		EnumHttpMessages enumHttpMessages = EnumHttpMessages.E500;
		List<String> details = Arrays.asList("detalle1", "detalle2", "detalle3");

		GenericException exception = new GenericException(details, enumHttpMessages);
		GenericException exception2 = new GenericException(details, enumHttpMessages);

		// Verify that the hashCodes are the same
		assertEquals(exception.hashCode(), exception2.hashCode());
	}

}
