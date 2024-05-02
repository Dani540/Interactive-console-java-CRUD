package com.ufrotest.services.exceptions.validator.book.imp;

import com.ufrotest.services.exceptions.validator.book.BookValidationException;

public class BookErrorFormat extends BookValidationException {
    public BookErrorFormat(String message) {
        super(message);
    }
}
