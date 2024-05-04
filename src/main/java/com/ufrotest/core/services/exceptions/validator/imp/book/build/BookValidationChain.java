package com.ufrotest.core.services.exceptions.validator.imp.book.build;

import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.imp.BookNullValidation;
import com.ufrotest.core.services.exceptions.validator.out.IValidationChain;

import java.util.List;
public class BookValidationChain implements IValidationChain<BookDTO> {
    private final List<BookValidator> validators;

    public BookValidationChain() {
        this.validators = List.of(
                new BookNullValidation()
        );
    }

    @Override
    public void validate(BookDTO entity) throws BookValidationException {
        for (BookValidator validator : validators) {
            validator.validate(entity);
        }
    }
}
