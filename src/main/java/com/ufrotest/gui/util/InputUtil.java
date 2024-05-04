package com.ufrotest.gui.util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner = new Scanner(System.in);
    public String getString() {
        String input = scanner.nextLine();
        while (!isString(input)) {
            System.out.println("Invalid input, please enter a valid string");
            input = scanner.nextLine();
        }
        return input;
    }

    public int getInt() {
        int input = 0;
        try {
            input = scanner.nextInt();
            while (!isNumber(input)) {
                System.out.println("Invalid input, please enter a valid number");
                input = scanner.nextInt();
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid input, please enter a valid number");
        }
        scanner.nextLine();
        return input;
    }

    public double getDouble() {
        double input = scanner.nextDouble();
        try{
            while (!isDouble(input)) {
                System.out.println("Invalid input, please enter a valid number");
                input = scanner.nextDouble();
            }
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Invalid input, please enter a valid number");
            scanner.nextLine();
            return getDouble();
        }
        return input;
    }

    public boolean getBoolean() {
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("true") && !input.equalsIgnoreCase("false")) {
            System.out.println("Invalid input, please enter a valid boolean");
            input = scanner.nextLine();
        }
        return Boolean.parseBoolean(input);
    }

    public static boolean isNumber(int input) {
        return Integer.toString(input).matches("\\d+") && input > 0;
    }

    public static boolean isString(String input) {
        return !input.isEmpty() && input.matches("[\\d+a-zA-Z]+");
    }

    private boolean isDouble(double input) {
        return Double.toString(input).matches("\\d+\\.\\d+");
    }

    public void close() {
        scanner.close();
    }
}
