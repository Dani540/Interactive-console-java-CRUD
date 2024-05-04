package com.ufrotest.core.services.exceptions.validator.imp.book.imp;

import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidator;

public class BookNullValidation extends BookValidator {

    @Override
    public boolean validate(BookDTO DTO) throws BookValidationException {
        if (DTO == null) {
            throw new BookValidationException("Book is null");
        }
        return false;
    }


}
