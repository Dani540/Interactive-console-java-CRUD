package com.ufrotest.gui.imp.inner;

import com.ufrotest.constants.By;
import com.ufrotest.core.services.imp.BookService;
import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.gui.build.OptionDTO;
import com.ufrotest.gui.controller.MenuController;
import com.ufrotest.gui.imp.commands.search.FindBy;
import com.ufrotest.constants.EMenu;
import com.ufrotest.gui.util.InputUtil;

import java.util.HashMap;
import java.util.Map;

public class SearchBookMenu extends MenuABS {
    private final FindBy findBy;
    public SearchBookMenu(BookService service, InputUtil inputUtil) {
        super(new HashMap<>(
                Map.of(
                        1, new OptionDTO("By title"),
                        2, new OptionDTO("By author"),
                        3, new OptionDTO("By category"),
                        4, new OptionDTO("By rating"),
                        5, new OptionDTO("By available copies"),
                        6, new OptionDTO("Back")
                )
        ), inputUtil);
        findBy = new FindBy(service, new InputUtil());
    }

    @Override
    public void select(int option) {
        switch (option) {
            case 1 -> findBy.setBy(By.TITLE);
            case 2 -> findBy.setBy(By.AUTHOR);
            case 3 -> findBy.setBy(By.CATEGORY);
            case 4 -> findBy.setBy(By.RATING);
            case 5 -> findBy.setBy(By.COPIES);
            case 6 -> exit();
        }
        findBy.execute();
    }

    @Override
    public void exit() {
        MenuController.getINSTANCE().changeMenuBehavior(EMenu.LIBRARY);
    }
}
