package com.ufrotest.core.services.exceptions.validator.imp.user.imp;

import com.ufrotest.core.services.exceptions.validator.imp.user.build.UserValidator;
import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.repositories.UserRepo;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyExists extends UserValidator {
    private final UserRepo userRepo;
    @Override
    public boolean validate(UserDTO DTO) throws UserValidationException {
        if(byId(DTO.id()) && byUsername(DTO)){
            throw new UserValidationException("User already exists");
        }
        return false;
    }

    private boolean byId(int id) {
        return !userRepo.findAll().isEmpty() && userRepo.findAll().contains(userRepo.findById(id));
    }

    private boolean byUsername(UserDTO user) {
        return !userRepo.findAll().isEmpty() && userRepo.findAll().stream().anyMatch(userDTO -> userDTO.username().equals(user.username()));
    }


}
