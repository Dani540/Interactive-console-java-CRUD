package com.ufrotest.services.exceptions.validator.book;

import com.ufrotest.model.BookDTO;
import com.ufrotest.services.exceptions.out.IValidator;

public abstract class BookValidator implements IValidator<BookDTO> {
    @Override
    public abstract boolean validate(BookDTO DTO) throws BookValidationException;
}
