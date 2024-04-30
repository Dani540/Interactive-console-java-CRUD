package com.ufrotest.model;

import com.ufrotest.utils.UserType;

import java.util.List;

public record UserDTO(String name, UserType type, List<BookDTO> loanHistory, List<BookDTO> reservationHistory, List<Integer> ratings) {
}
