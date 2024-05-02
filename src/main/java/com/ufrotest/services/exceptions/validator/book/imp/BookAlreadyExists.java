package com.ufrotest.services.exceptions.validator.book.imp;

import com.ufrotest.model.BookDTO;
import com.ufrotest.repositories.BookRepo;
import com.ufrotest.services.exceptions.validator.book.BookValidationException;
import com.ufrotest.services.exceptions.validator.book.BookValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookAlreadyExists extends BookValidator {
    private final BookRepo bookRepo;
    @Override
    public boolean validate(BookDTO DTO) throws BookValidationException {
        boolean byId = byId(DTO.id());
        boolean byTitle = byTitle(DTO.title());
        if ( byId || byTitle ){
            throw new BookValidationException("Book Already Exists", byId, byTitle);
        }
        return false;
    }

    private boolean byTitle(String title) {
        return bookRepo.findAll().stream().noneMatch(book -> book.title().equalsIgnoreCase(title));
    }

    private boolean byId(int id){
        return bookRepo.findAll().contains(bookRepo.findById(id));
    }
}
