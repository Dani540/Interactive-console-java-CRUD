package com.ufrotest.gui.util;

import com.ufrotest.gui.util.imp.ResultInputStrategies;
import com.ufrotest.gui.util.out.ResultInput;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
@AllArgsConstructor
public class InputUtil {
    private final Scanner scanner;
    public InputUtil() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        String input = scanner.nextLine();
        ResultInput<String> result = ResultInputStrategies.isString(input);
        while ( !result.isSuccess() ) {
            System.out.println("Invalid input, please enter a valid string");
            input = scanner.nextLine();
            result = ResultInputStrategies.isString(input);
        }
        return result.getValue();
    }

    public int getInt() {
        String input = scanner.nextLine();
        ResultInput<Integer> result = ResultInputStrategies.isValidInteger(input);
        try {
            while ( !result.isSuccess() ) {
                System.out.println("Invalid input, please enter a valid number");
                input = scanner.nextLine();
                result = ResultInputStrategies.isValidInteger(input);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid input, please enter a valid number");
        }
        return result.getValue();
    }

    public double getDouble() {
        String input = scanner.next();
        ResultInput<Double> result = ResultInputStrategies.isDouble(input);
        try{
            while ( !result.isSuccess() ) {
                System.out.println("Invalid input, please enter a valid number");
                input = scanner.next();
                result = ResultInputStrategies.isDouble(input);
            }
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid input, please enter a valid number");
        }
        return result.getValue();
    }

    public boolean getBoolean() {
        String input = scanner.nextLine();
        ResultInput<Boolean> result = ResultInputStrategies.isBoolean(input);
        while ( !result.isSuccess() ) {
            System.out.println("Invalid input, please enter a valid boolean");
            input = scanner.nextLine();
            result = ResultInputStrategies.isBoolean(input);
        }
        return result.getValue();
    }

    public void close() {
        scanner.close();
    }

}
