package com.ufrotest.gui.imp.global;

import com.ufrotest.constants.UserData;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.imp.BookService;
import com.ufrotest.core.services.imp.UserService;
import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.build.OptionDTO;
import com.ufrotest.gui.controller.MenuController;
import com.ufrotest.gui.imp.commands.toList.ToList;
import com.ufrotest.gui.imp.inner.SearchBookMenu;
import com.ufrotest.constants.EMenu;
import com.ufrotest.gui.util.InputUtil;

import java.util.HashMap;
import java.util.Map;

import static com.ufrotest.constants.ToList.BOOK;
import static com.ufrotest.constants.ToList.USER;

public class LibraryMenu extends MenuABS {
    private final BookService bookService;
    private final UserService userService;
    private final ToList toList;

    public LibraryMenu(Map<Integer, OptionDTO> options, BookService bookService, UserService userService, InputUtil inputUtil) {
        super(options,inputUtil);
        this.bookService = bookService;
        this.userService = userService;
        toList = new ToList(bookService, userService);
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1->{
                toList.setToList(BOOK);
                toList.execute();
            }
            case 2 -> {
                SearchBookMenu searchBookMenu = new SearchBookMenu(bookService, inputUtil);
                searchBookMenu.display();
                searchBookMenu.selectOption();
            }
            case 3,4 -> userAction(option);
            case 5 ->{
                toList.setToList(USER);
                toList.execute();
            }
            case 6 -> exit();
        }
    }

    public void userAction(int action){
        try {
            switch (action) {
                case 3 -> {
                    toList.setToList(BOOK);
                    toList.execute();
                    userService.borrowBook(UserData.getInstance().getUserLogged().id(), inputUtil.getInt());
                    System.out.println("Book borrowed");
                }
                case 4 -> {
                    toList.setToList(BOOK);
                    toList.execute();
                    userService.removeBorrowBook(UserData.getInstance().getUserLogged().id(), inputUtil.getInt());
                    System.out.println("Book Returned!");
                }
            }

        }catch (UserValidationException | BookValidationException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exit() {
        MenuController.getINSTANCE().changeMenuBehavior(EMenu.MAIN_MENU);
    }
}
