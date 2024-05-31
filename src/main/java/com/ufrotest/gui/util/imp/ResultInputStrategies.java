package com.ufrotest.gui.util.imp;

import com.ufrotest.gui.util.out.ResultInput;

import java.util.regex.Pattern;

public class ResultInputStrategies {
    public static ResultInput<String> isString(String input) {
        return !input.isEmpty() && Pattern.matches("[+a-zA-Z]+", input) ? ResultInput.success(input) : ResultInput.fail("Invalid input, please enter a valid string");
    }

    public static ResultInput<Integer> isValidInteger(String input) {
        return Pattern.matches("^-?\\d+$", input) && Integer.parseInt(input)>0 ? ResultInput.success(Integer.valueOf(input)) : ResultInput.fail("Invalid input, please enter a valid number");
    }

    public static ResultInput<Double> isDouble(String input) {
        return Pattern.matches("^-?\\d+(\\.\\d+)?$", input) ? ResultInput.success(Double.valueOf(input)) : ResultInput.fail("Invalid input, please enter a valid number");
    }

    public static ResultInput<Boolean> isBoolean(String input) {
        return Pattern.matches("^(?i)true|false$", input) ? ResultInput.success(Boolean.valueOf(input)) : ResultInput.fail("Invalid input, please enter a valid boolean");
    }
}
