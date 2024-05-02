package com.ufrotest.model;

import com.ufrotest.utils.UserType;

import java.util.List;

public record UserDTO(int id, String username, String password, UserType type, List<BookDTO> loanHistory, List<BookDTO> reservationHistory, List<Integer> ratings)
implements IEntityDTO {}
