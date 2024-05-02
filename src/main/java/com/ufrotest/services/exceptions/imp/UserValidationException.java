package com.ufrotest.services.exceptions.imp;

import com.ufrotest.services.exceptions.out.IValidateException;

public class UserValidationException extends IValidateException {
    public UserValidationException(String message) {
        super(message);
    }
}
