package com.ufrotest.services.exceptions.validator.book;

import com.ufrotest.services.exceptions.out.IValidateException;

public class BookValidationException extends IValidateException {
    public BookValidationException(String message) {
        super(message);
    }

    public BookValidationException(String message, boolean byId, boolean byTitle) {
        super(message + " byId: " + byId + " byTitle: " + byTitle);
    }


}
