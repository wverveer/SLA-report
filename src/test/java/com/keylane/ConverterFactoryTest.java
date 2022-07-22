package com.keylane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConverterFactoryTest {

    private ConverterFactory testFactory;

    @BeforeEach
    void setUp() {
        testFactory = new ConverterFactory();
    }

    @Test
    void createWriterReturnsNullWhenExtensionNotSupported() {
        String nonSupportedExtension = "test" + String.join("", testFactory.getConverterMap().keySet());
        assertNull(testFactory.createWriter(nonSupportedExtension));
    }

    @Test
    void createReaderReturnsNullWhenExtensionNotSupported() {
        String nonSupportedExtension = "test" + String.join("", testFactory.getReaderMap().keySet());
        assertNull(testFactory.createReader(nonSupportedExtension));
    }

    @Test
    void createWriterReturnsClassInConverterMapLowerAndUpperCaseIgnore() {
        testFactory.getConverterMap()
                .forEach((key, value) ->
                {
                    assertEquals(value.get().getClass(), testFactory.createWriter(key.toUpperCase()).getClass());
                    assertEquals(value.get().getClass(), testFactory.createWriter(key.toLowerCase()).getClass());
                });
    }

    @Test
    void createReaderReturnsClassInReaderMapLowerAndUpperCaseIgnore() {
        testFactory.getReaderMap()
                .forEach((key, value) -> {
                    assertEquals(value.get().getClass(), testFactory.createReader(key.toUpperCase()).getClass());
                    assertEquals(value.get().getClass(), testFactory.createReader(key.toLowerCase()).getClass());
                });
    }
}