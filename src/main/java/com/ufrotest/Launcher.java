package com.ufrotest;

import com.ufrotest.constants.EMenu;
import com.ufrotest.constants.UserType;
import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.repositories.BookRepo;
import com.ufrotest.core.repositories.UserRepo;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.book.build.BookValidationChain;
import com.ufrotest.core.services.exceptions.validator.imp.user.build.UserValidationChain;
import com.ufrotest.core.services.imp.BookService;
import com.ufrotest.core.services.imp.UserService;
import com.ufrotest.gui.build.OptionDTO;
import com.ufrotest.gui.controller.MenuController;
import com.ufrotest.gui.data.MenuRepo;
import com.ufrotest.gui.imp.global.ExitMenu;
import com.ufrotest.gui.imp.global.LibraryMenu;
import com.ufrotest.gui.imp.global.MainMenu;
import com.ufrotest.gui.util.InputUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Launcher {
    /**
     * Clase encargada de configurar los elementos a usar, tanto de UI como de lógica interna.
     */
    private final boolean[] flags = {false, false, false}; // <- Marcadores para saber si en tiempo de ejecución en que etapa del despliegue del programa voy

    private MenuRepo menuRepo; // <- Repositorio de Menus para las pruebas.
    private MenuController menuController; // <- Controlador de menus

    public void preRun(){
        if (!flags[0]){
            System.out.println("Preparing to run...");

            // Initialize statics

            menuRepo = MenuRepo.getINSTANCE();
            menuController = MenuController.getINSTANCE();

            flags[0] = true;
        }
        else{
            System.out.println("Already preparing to run...");
        }
    }
    public void run(){
        if (flags[0] && !flags[1]){
            flags[1] = true;
            System.out.println("Running...");

            // Configure global objects

            InputUtil inputUtil = new InputUtil();

            // Validators

            UserValidationChain userValidationChain = new UserValidationChain();
            BookValidationChain bookValidationChain = new BookValidationChain();

            // Repositories

            UserRepo userRepo = new UserRepo();
            BookRepo bookRepo = new BookRepo();

            // Services

            BookService bookService = new BookService(bookRepo, bookValidationChain);
            UserService userService = new UserService(userRepo, userValidationChain, bookService);

            // Configure Menus in MenuRepo

            initMenuRepo(bookService, userService, inputUtil);


            // Configure Initial Menu

            menuController.changeMenuBehavior(EMenu.MAIN_MENU);
        }
        else if (!flags[0]){
            System.out.println("Not yet preparing to run...");
        }
        else{
            System.out.println("Already running...");
        }
    }


    private void initMenuRepo(BookService bookService, UserService userService, InputUtil inputUtil) {
        menuRepo.addMenu(EMenu.MAIN_MENU,
                        new MainMenu(
                                new HashMap<>(
                                    Map.of(1, new OptionDTO("Login"),
                                            2, new OptionDTO("Register"),
                                            3, new OptionDTO("Exit")
                                    )),
                        userService,
                        inputUtil));
        menuRepo.addMenu(EMenu.LIBRARY,
                new LibraryMenu(
                        new HashMap<>(
                                Map.of(
                                        1, new OptionDTO("List books"),
                                        2, new OptionDTO("Search book"),
                                        3, new OptionDTO("Borrow book"),
                                        4, new OptionDTO("Return book"),
                                        5, new OptionDTO("Profile"),
                                        6, new OptionDTO("Log out")
                                )
                        ),
                        bookService,
                        userService,
                        inputUtil));
        menuRepo.addMenu(EMenu.EXIT, new ExitMenu(
                new HashMap<>(
                        Map.of(
                            1, new OptionDTO("Yes"),
                            2, new OptionDTO("No")
                        )
                ),
                inputUtil));
//        menuRepo.addMenu(EMenu.LOAN_BOOK, new MovementMenu(menuController));
//        menuRepo.addMenu(EMenu.LOANS, new ExitMenu(menuController));
    }

    public void stop(){
        if (flags[0] && flags[1] && !flags[2]){
            flags[2] = true;
            System.out.println("Stopping...");
        }
        else if (!flags[0]){
            System.out.println("Not yet preparing to run...");
        }
        else if (!flags[1]){
            System.out.println("Not yet running...");
        }
        else{
            System.out.println("Already stopped...");
        }
    }
}
