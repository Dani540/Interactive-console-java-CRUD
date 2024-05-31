package com.ufrotest;

import com.ufrotest.constants.Categories;
import com.ufrotest.core.model.BookDTO;
import com.ufrotest.core.repositories.BookRepo;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidationChain;
import com.ufrotest.core.services.imp.BookService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            new BookService(new BookRepo(), new BookValidationChain()).saveBook(
                    new BookDTO(83, "test", "dani", Categories.ART.toString(), 1, new ArrayList<>(), new ArrayList<>())
            );
        } catch (BookValidationException e) {
            throw new RuntimeException(e);
        }

        //Launcher launcher = new Launcher();
        //launcher.preRun();
        //launcher.run();
    }
}
