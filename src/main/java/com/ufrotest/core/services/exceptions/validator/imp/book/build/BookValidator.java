package com.ufrotest.core.services.exceptions.validator.imp.book.build;

import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.services.exceptions.validator.out.IValidator;

public abstract class BookValidator implements IValidator<BookDTO> {
    @Override
    public abstract boolean validate(BookDTO DTO) throws BookValidationException;
}
