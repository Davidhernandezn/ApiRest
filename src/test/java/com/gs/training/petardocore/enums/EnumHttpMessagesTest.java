package com.gs.training.petardocore.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class EnumHttpMessagesTest {
	
	@Test
    public void testGetBasePathCapitalized() throws Exception {
        
        Method method = EnumHttpMessages.class.getDeclaredMethod("getBasePathCapitalized", String.class);
        
        method.setAccessible(true);
        
        String input = "/dev-ops/cicd/v1";
        String expectedOutput = "Dev-Ops-Cicd";
        
        String result = (String) method.invoke(null, input);
        
        assertEquals(expectedOutput, result);
        
        method.setAccessible(false);
    }
}

