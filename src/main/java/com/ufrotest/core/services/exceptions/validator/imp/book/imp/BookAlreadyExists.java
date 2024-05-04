package com.ufrotest.core.services.exceptions.validator.imp.book.imp;

import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.repositories.BookRepo;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookAlreadyExists extends BookValidator {
    private final BookRepo bookRepo;
    @Override
    public boolean validate(BookDTO DTO) throws BookValidationException {
        if ( byId(DTO.id()) && byTitle(DTO.title()) ){
            throw new BookValidationException("Book Already Exists");
        }
        return false;
    }

    private boolean byTitle(String title) {
        return !bookRepo.findAll().isEmpty() && bookRepo.findAll().stream().anyMatch(book -> book.title().equalsIgnoreCase(title));
    }

    private boolean byId(int id){
        return !bookRepo.findAll().isEmpty() && bookRepo.findAll().contains(bookRepo.findById(id));
    }
}
