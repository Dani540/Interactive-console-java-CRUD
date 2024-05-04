package com.ufrotest.core.services.exceptions.imp;

import com.ufrotest.core.services.exceptions.validator.out.IValidateException;

public class UserValidationException extends IValidateException {
    public UserValidationException(String message) {
        super(message);
    }
}
