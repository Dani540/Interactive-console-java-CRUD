package com.ufrotest.core.model;

import com.ufrotest.constants.UserType;

import java.util.List;

public record UserDTO(int id, String username, String password, UserType type, List<BookDTO> borrowedBooksHistory, List<BookDTO> reservationHistory, List<BookDTO> ratings)
implements IEntityDTO {}
