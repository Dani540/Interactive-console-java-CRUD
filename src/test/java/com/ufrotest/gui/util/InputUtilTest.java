package com.ufrotest.gui.util;

import com.ufrotest.gui.util.imp.ResultInputStrategies;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilTest {
    private InputUtil inputUtil;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void getString() {
        String simulatedInput = "test\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        inputUtil = new InputUtil(new Scanner(System.in));
        assertEquals("test", inputUtil.getString());
    }

    @Test
    void getInt() {
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        inputUtil = new InputUtil(new Scanner(System.in));
        assertEquals(1, inputUtil.getInt());
    }

    @Test
    void getDouble() {
        String simulatedInput = "1.0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); // Use US locale to read double values
        inputUtil = new InputUtil(scanner);
        assertEquals(1.0, inputUtil.getDouble(), 0.001);
    }


    @Test
    void getBoolean() {
        String simulatedInput = "true\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        inputUtil = new InputUtil(new Scanner(System.in));
        assertTrue(inputUtil.getBoolean());
    }

    @Test
    void isNaturalNumber() {
        assertTrue( ResultInputStrategies.isValidInteger("1").isSuccess() );
        assertFalse(ResultInputStrategies.isValidInteger("-12").isSuccess() );
    }

    @Test
    void isString() {
        assertTrue(ResultInputStrategies.isString("test").isSuccess());
        assertFalse(ResultInputStrategies.isString("123").isSuccess());
        assertEquals("Invalid input, please enter a valid string", ResultInputStrategies.isString("123").getMessage());
        assertEquals("test", ResultInputStrategies.isString("test").getValue());
    }

    @Test
    void close() {
        inputUtil = new InputUtil(new Scanner(System.in));
        inputUtil.close();
        assertNull(inputUtil.getScanner().ioException());
    }
}
