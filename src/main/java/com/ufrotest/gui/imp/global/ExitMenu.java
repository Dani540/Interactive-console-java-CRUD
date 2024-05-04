package com.ufrotest.gui.imp.global;

import com.ufrotest.constants.EMenu;
import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.build.OptionDTO;
import com.ufrotest.gui.controller.MenuController;
import com.ufrotest.gui.util.InputUtil;

import java.util.Map;

public class ExitMenu extends MenuABS {
    public ExitMenu(Map<Integer, OptionDTO> optionMap, InputUtil inputUtil) {
        super(optionMap, inputUtil);
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1 -> exit();
            case 2 -> MenuController.getINSTANCE().changeMenuBehavior(EMenu.MAIN_MENU);
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
