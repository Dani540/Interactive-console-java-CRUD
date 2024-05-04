package com.ufrotest.gui.imp.commands.search;

import com.ufrotest.constants.By;
import com.ufrotest.constants.Categories;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.imp.BookService;
import com.ufrotest.gui.build.ICommand;
import com.ufrotest.gui.util.InputUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Scanner;

@RequiredArgsConstructor
public class FindBy implements ICommand {
    @Setter
    private By by;
    private final BookService bookService;
    private final InputUtil inputUtil;
    @Override
    public void execute() {
        try {
            switch (by) {
                case TITLE -> findByTitle();
                case AUTHOR -> findByAuthor();
                case CATEGORY -> findByCategory();
                case RATING -> findByRating();
                case COPIES -> findByAvailableCopies();
            }
        }catch (BookValidationException e){
            System.out.println(e.getMessage());
        }
    }

    private void findByTitle() throws BookValidationException {
        System.out.print("Enter title: ");
        bookService.findByTitle(inputUtil.getString()).stream().map(bookService::format).forEach(System.out::println);
    }

    private void findByAuthor() throws BookValidationException {
        System.out.print("Enter author: ");
        bookService.findByAuthor(inputUtil.getString()).stream().map(bookService::format).forEach(System.out::println);
    }

    private void findByCategory() throws BookValidationException {
        System.out.print("Enter category: ");
        Arrays.stream(Categories.values()).forEach(category -> System.out.println(category.ordinal() + " - " + category));
        bookService.findByCategory(Categories.values()[new InputUtil().getInt()]).stream().map(bookService::format).forEach(System.out::println);
    }

    private void findByRating() throws BookValidationException {
        System.out.print("Enter rating: ");
        bookService.findByAverageRating(inputUtil.getInt()).stream().map(bookService::format).forEach(System.out::println);
    }

    private void findByAvailableCopies() throws BookValidationException {
        System.out.print("Enter available copies: ");
        bookService.findByAvailableCopies(inputUtil.getInt()).stream().map(bookService::format).forEach(System.out::println);
    }
}
