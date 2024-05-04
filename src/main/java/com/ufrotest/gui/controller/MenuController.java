package com.ufrotest.gui.controller;

import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.data.MenuRepo;
import com.ufrotest.constants.EMenu;
import lombok.Getter;

@Getter
public class MenuController {
    private static MenuController INSTANCE = new MenuController();
    private MenuABS currentMenuBehavior;

    public void changeMenuBehavior(EMenu menu) {
        currentMenuBehavior = MenuRepo.getINSTANCE().findMenu(menu);
        while (true) {
            currentMenuBehavior.display();
            currentMenuBehavior.selectOption();
        }
    }

    public static MenuController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new MenuController();
        return INSTANCE;
    }
}
