package com.ufrotest.gui.build;

import com.ufrotest.gui.util.InputUtil;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Scanner;

@RequiredArgsConstructor
public abstract class MenuABS {
    protected final Map<Integer, OptionDTO> options;
    protected final InputUtil inputUtil;

    public abstract void select(int option);
    public abstract void exit();

    public void display(){
        options.forEach((key, value) -> System.out.println(key + ". " + value.text()));
    }

    public void selectOption(){
        select(inputUtil.getInt());
    }
}
