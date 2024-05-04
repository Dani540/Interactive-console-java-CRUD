package com.ufrotest.gui.imp.global;

import com.ufrotest.constants.UserData;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.build.OptionDTO;
import com.ufrotest.gui.controller.MenuController;
import com.ufrotest.constants.EMenu;
import com.ufrotest.core.services.imp.UserService;
import com.ufrotest.gui.util.InputUtil;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends MenuABS {
    private final UserService service;
    public MainMenu(Map<Integer, OptionDTO> menu, UserService service, InputUtil inputUtil) {
        super(menu, inputUtil);
        this.service = service;
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1 -> login();
            case 2 -> register();
            case 3 -> exit();
        }
    }

    private void register() {
        System.out.println("Not availability");
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = inputUtil.getString();
        System.out.print("Enter password: ");
        String password = inputUtil.getString();
        if (service.login(username, password)) {
            System.out.println("Logged in");
            try {
                UserData.getInstance().setUserLogged(service.getUserByName(username));
            } catch (UserValidationException e) {
                throw new RuntimeException(e);
            }
            MenuController.getINSTANCE().changeMenuBehavior(EMenu.LIBRARY);
        } else {
            System.err.println("Invalid credentials");
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
