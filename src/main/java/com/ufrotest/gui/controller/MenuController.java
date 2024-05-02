package com.ufrotest.gui.controller;

import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.data.MenuRepo;
import com.ufrotest.gui.util.EMenu;
import lombok.Getter;

@Getter
public class MenuController {
    private static MenuController INSTANCE = new MenuController();
    private MenuABS currentMenuBehavior;

    public void changeMenu(EMenu menu) {
        currentMenuBehavior = MenuRepo.getINSTANCE().findMenu(menu);
    }

    public static MenuController getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new MenuController();
        return INSTANCE;
    }
}
