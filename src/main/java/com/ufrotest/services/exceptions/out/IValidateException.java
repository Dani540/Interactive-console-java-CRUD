package com.ufrotest.services.exceptions.out;

import com.ufrotest.model.UserDTO;

public abstract class IValidateException extends Exception{
    public IValidateException(String message) {
        super(message);
    }
}
