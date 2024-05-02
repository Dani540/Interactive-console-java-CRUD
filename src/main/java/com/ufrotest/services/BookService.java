package com.ufrotest.services;

import com.ufrotest.model.BookDTO;
import com.ufrotest.repositories.BookRepo;
import com.ufrotest.utils.Categories;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    public List<BookDTO> findByRating(int rating){
        return bookRepo.findAll().stream().filter(book -> (getBookRating(book.id()) == rating)).toList().stream().filter(Objects::nonNull).toList();
    }

    public List<BookDTO> findByCategory(Categories category){
        return bookRepo.findAll().stream().filter(book -> book.category().equals(category)).toList().stream().filter(Objects::nonNull).toList();
    }

    public List<BookDTO> findByAuthor(String author){
        return bookRepo.findAll().stream().filter(book -> book.author().equals(author)).toList().stream().filter(Objects::nonNull).toList();
    }

    public List<BookDTO> findByTitle(String title){
        return bookRepo.findAll().stream().filter(book -> book.title().equals(title)).toList().stream().filter(Objects::nonNull).toList();
    }

    public List<BookDTO> findByAvailableCopies(int copies){
        return bookRepo.findAll().stream().filter(book -> book.copies() == copies).toList().stream().filter(Objects::nonNull).toList();
    }

    public int getBookRating(int id){
        return bookRepo.findById(id).ratings().stream().reduce(0, Integer::sum)/bookRepo.findById(id).ratings().size();
    }


}
