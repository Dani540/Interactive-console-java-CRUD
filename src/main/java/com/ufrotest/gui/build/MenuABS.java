package com.ufrotest.gui.build;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public abstract class MenuABS {
    protected final Map<Integer, OptionDTO> options;
    protected int index = 0;

    public abstract void select(int option);
    public abstract void exit();

    public void display(){
        options.forEach((key, value) -> System.out.println(key + ". " + value.text()));
    }

    public void selectOption(int option){
        if(options.containsKey(option)){
            select(option);
        }else{
            System.out.println("Invalid option");
        }
    }

    public void moveUp(){
        if(index > 0){
            index--;
        }
    }

    public void moveDown(){
        if(index < options.size() - 1){
            index++;
        }
    }
}
