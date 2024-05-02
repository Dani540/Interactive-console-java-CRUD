package com.ufrotest;

import com.ufrotest.model.UserDTO;
import com.ufrotest.repositories.UserRepo;
import com.ufrotest.services.UserService;
import com.ufrotest.services.exceptions.validator.user.UserValidationChain;
import com.ufrotest.utils.UserType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserRepo userRepo = new UserRepo();
        UserService userService= new UserService(userRepo, new UserValidationChain(userRepo));

        userRepo.save(new UserDTO(0,"dani","password", UserType.ADMIN, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        System.out.println();
    }
}
