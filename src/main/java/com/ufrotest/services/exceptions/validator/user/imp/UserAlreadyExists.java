package com.ufrotest.services.exceptions.validator.user.imp;

import com.ufrotest.model.UserDTO;
import com.ufrotest.repositories.UserRepo;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.out.IValidator;
import com.ufrotest.services.exceptions.validator.user.UserValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyExists extends UserValidator {
    private final UserRepo userRepo;
    @Override
    public boolean validate(UserDTO DTO) throws UserValidationException {
        if(byIdAndUsername(DTO)) {
            throw new UserValidationException("User already exists");
        }
        return false;
    }

    private boolean byId(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id());
    }

    private boolean byUsername(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.username().equals(user.username()));
    }

    private boolean byIdAndUsername(UserDTO user) {
        return userRepo.findAll().stream().anyMatch(userDTO -> userDTO.id() == user.id() && userDTO.username().equals(user.username()));
    }

}
