package com.ufrotest.gui.data;

import com.ufrotest.gui.build.MenuABS;
import com.ufrotest.constants.EMenu;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
public class MenuRepo {
    private static MenuRepo INSTANCE = new MenuRepo();
    private final Map<EMenu, MenuABS> menuMap = new HashMap<>();

    public void addMenu(EMenu menu, MenuABS menuABS){
        menuMap.put(menu, menuABS);
    }

    public MenuABS getMenu(EMenu menu){
        return menuMap.get(menu);
    }

    public MenuABS findMenu(EMenu menu){
        return menuMap.entrySet().stream()
                .filter(entry -> entry.getKey().equals(menu))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }


    public static MenuRepo getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new MenuRepo();
        return INSTANCE;
    }
}
