package com.ufrotest.testClasses;

import com.ufrotest.utils.UserType;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private UserType type; // Estudiante, Profesor, Personal de la Library
    private final List<Book> loanHistory;
    private final List<Book> reservationHistory;
    private final List<Integer> ratings;

    public User(String name, UserType type, List<Book> loanHistory, List<Book> reservationHistory, List<Integer> ratings) {
        this.name = name;
        this.type = type;

        this.loanHistory = new ArrayList<>(loanHistory);
        this.reservationHistory = new ArrayList<>(reservationHistory);
        this.ratings = new ArrayList<>(ratings);
    }

    public void addLoan(Book book) {
        loanHistory.add(book);
    }

    public void makeReservation(Book book) {
        reservationHistory.add(book);
    }

    public void addRating(int calificacion) {
        ratings.add(calificacion);
    }

}
