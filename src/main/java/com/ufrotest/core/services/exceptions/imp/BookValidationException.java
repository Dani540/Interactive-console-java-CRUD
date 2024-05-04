package com.ufrotest.core.services.exceptions.imp;

import com.ufrotest.core.services.exceptions.validator.out.IValidateException;

public class BookValidationException extends IValidateException {
    public BookValidationException(String message) {
        super(message);
    }
    public BookValidationException(String s, BookValidationException e) {
        super(s, e);
    }
}
