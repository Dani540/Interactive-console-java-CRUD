package com.ufrotest.gui.imp.commands.toList;

import com.ufrotest.constants.UserData;
import com.ufrotest.core.services.exceptions.imp.BookValidationException;
import com.ufrotest.core.services.imp.BookService;
import com.ufrotest.core.services.imp.UserService;
import com.ufrotest.gui.build.ICommand;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
public class ToList implements ICommand {
    private final BookService bookService;
    private final UserService userService;
    private com.ufrotest.constants.ToList toList;

    @Override
    public void execute() {
        try {
            switch (toList) {
                case BOOK -> listAllBooks();
                case USER -> listUserProfile();
            }
        }catch (BookValidationException e){
            System.out.println(e.getMessage());
        }
    }

    private void listAllBooks() throws BookValidationException {
        bookService.findAll().stream().map(bookService::format).forEach(System.out::println);
    }

    private void listUserProfile(){
        System.out.println(userService.format(UserData.getInstance().getUserLogged()));
    }
}
