package com.ufrotest.gui.util.out;

import lombok.Getter;

@Getter
public abstract class ResultInput<Type> {
    private Type value; // Experimental, the polymorphism make me problems
    private final boolean isSuccess;
    private final String message;

    private ResultInput(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static <Type> SuccessResultInput<Type> success(Type value) {
        return new SuccessResultInput<>(value);
    }

    public static <Type> FailResultInput<Type> fail(String message) {
        return new FailResultInput<>(message);
    }

    @Getter
    private static class SuccessResultInput<Type> extends ResultInput<Type>{
        private final Type value;
        private SuccessResultInput(Type value) {
            super(true, null);
            this.value = value;
        }
    }

    private static class FailResultInput<Type> extends ResultInput<Type>{
        private FailResultInput(String message) {
            super(false, message);
        }
    }
}
