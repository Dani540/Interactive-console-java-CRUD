package com.ufrotest.core.services.exceptions.validator.imp.book.imp;

import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidator;
import com.ufrotest.constants.Categories;

import java.util.List;

public class BookErrorFormat extends BookValidator {
    @Override
    public boolean validate(BookDTO DTO) throws BookValidationException {
        validateId(DTO.id());
        validateTitle(DTO.title());
        validateAuthor(DTO.author());
        validateCategory(Categories.valueOf(DTO.category()));
        validateCopies(DTO.copies());
        validateRatings(DTO.ratings());
        validateComments(DTO.comments());
        return false;
    }

    private void validateTitle(String title) throws BookValidationException {
        if (title == null || title.isEmpty()) {
            throw new BookValidationException("Title is null or empty");
        }
    }

    private void validateAuthor(String author) throws BookValidationException {
        if (author == null || author.isEmpty()) {
            throw new BookValidationException("Author is null or empty");
        }
    }

    private void validateCategory(Categories category) throws BookValidationException {
        if (category == null) {
            throw new BookValidationException("Category is null");
        }
    }

    private void validateCopies(int copies) throws BookValidationException {
        if (copies < 0) {
            throw new BookValidationException("Copies is negative");
        }
    }

    private void validateRating(int rating) throws BookValidationException {
        if (rating < 0 || rating > 5) {
            throw new BookValidationException("Rating is not between 0 and 5");
        }
    }

    private void validateRatings(List<Integer> ratings) throws BookValidationException {
        for (int rating : ratings) {
            validateRating(rating);
        }
    }

    private void validateId(int id) throws BookValidationException {
        if (id < 0) {
            throw new BookValidationException("Id is negative");
        }
    }

    private void validateComments(List<String> comments) throws BookValidationException {
        for (String comment : comments) {
            if (comment == null || comment.isEmpty()) {
                throw new BookValidationException("Comment is null or empty");
            }
        }
    }

}
