package com.ufrotest.core.services.imp;

import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidationChain;
import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.repositories.BookRepo;
import com.ufrotest.constants.Categories;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final BookValidationChain validator;
    public void saveBook(BookDTO book) throws BookValidationException {
//        new BookAlreadyExists(bookRepo).validate(book);
//        validator.validate(book);
        bookRepo.save(book);
    }

    public BookDTO findById(int id) throws BookValidationException {
        BookDTO bookDTO = bookRepo.findById(id);
        validator.validate(bookDTO);
        return bookDTO;
    }

    public List<BookDTO> findByAverageRating(int rating) throws BookValidationException {
        List<BookDTO> bookDTOS = bookRepo.findAll().stream().filter(book -> (getAverage(book.ratings()) == rating)).toList();
        validateList(bookDTOS);
        return bookDTOS;
    }

    public List<BookDTO> findByCategory(Categories category) throws BookValidationException {
        List<BookDTO> bookDTOS = bookRepo.findAll().stream().filter(book -> book.category().equals(category.name())).toList();
        validateList(bookDTOS);
        return bookDTOS;
    }

    public List<BookDTO> findByAuthor(String author) throws BookValidationException {
        List<BookDTO> bookDTOS = bookRepo.findAll().stream().filter(book -> book.author().equals(author)).toList();
        validateList(bookDTOS);
        return bookDTOS;
    }

    public List<BookDTO> findByTitle(String title) throws BookValidationException {
        List<BookDTO> bookDTOS = bookRepo.findAll().stream().filter(book -> book.title().equals(title)).toList();
        validateList(bookDTOS);
        return bookDTOS;
    }

    public List<BookDTO> findByAvailableCopies(int copies) throws BookValidationException {
        List<BookDTO> bookDTOS = bookRepo.findAll().stream().filter(book -> book.copies() >= copies).toList();
        validateList(bookDTOS);
        return bookDTOS;
    }

    public int getBookAverageRating(int id) throws BookValidationException {
        BookDTO bookDTO = bookRepo.findById(id);
        validator.validate(bookDTO);
        return getAverage(bookDTO.ratings());
    }

    public void addRating(int id, int rating) throws BookValidationException {
        BookDTO book = bookRepo.findById(id);
        validator.validate(book);
        List<Integer> ratings = new ArrayList<>(book.ratings());
        ratings.add(rating);
        BookDTO updatedBook = new BookDTO(book.id(), book.title(), book.author(), book.category(), book.copies(), ratings, book.comments());
        bookRepo.update(updatedBook.id(), updatedBook);
    }

    public void addComment(int id, String comment) throws BookValidationException {
        BookDTO book = bookRepo.findById(id);
        validator.validate(book);
        List<String> comments = new ArrayList<>(book.comments());
        comments.add(comment);
        BookDTO updatedBook = new BookDTO(book.id(), book.title(), book.author(), book.category(), book.copies(), book.ratings(), comments);
        bookRepo.update(updatedBook.id(), updatedBook);
    }

    private void validateList(List<BookDTO> bookDTOS) throws BookValidationException {
        for (BookDTO bookDTO : bookDTOS) {
            validator.validate(bookDTO);
        }
    }

    private int getAverage(List<Integer> ratings){
        return ratings.isEmpty() ? 0 : ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
    }

    public List<BookDTO> findAll() throws BookValidationException {
        List<BookDTO> books = bookRepo.findAll();
        validateList(books);
        return books;
    }

    public String format(BookDTO book) {
        return book.id() + " - " + book.title() + " | " + book.author() + " | " + book.category() + " | copies: " + book.copies();
    }
}
