package com.ufrotest.core.services.exceptions.validator.out;

public abstract class IValidateException extends Exception{
    public IValidateException(String message) {
        super(message);
    }
    public IValidateException(String s, IValidateException e) {
        super(s, e);
    }
}
