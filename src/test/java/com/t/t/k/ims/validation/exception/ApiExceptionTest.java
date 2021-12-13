package com.t.t.k.ims.validation.exception;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class ApiExceptionTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        ApiException actualApiException = new ApiException("Msg");
        HashMap<String, String> stringStringMap = new HashMap<String, String>(1);
        actualApiException.setInfo(stringStringMap);

        // Assert
        assertSame(stringStringMap, actualApiException.getInfo());
    }
}

