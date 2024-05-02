package com.ufrotest.services.exceptions.validator.user;

import com.ufrotest.model.UserDTO;
import com.ufrotest.repositories.UserRepo;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.validator.user.imp.UserAlreadyExists;
import com.ufrotest.services.exceptions.validator.user.imp.UserErrorFormat;
import com.ufrotest.services.exceptions.validator.user.imp.UserNullValidation;
import lombok.AllArgsConstructor;

import java.util.List;
public class UserValidationChain {
    private final List<UserValidator> validators;

    public UserValidationChain(UserRepo repo) {
        validators = List.of(
                new UserNullValidation(),
                new UserErrorFormat(),
                new UserAlreadyExists(repo));
    }

    public void validate(UserDTO user) throws UserValidationException{
        for (UserValidator validator : validators) {
            validator.validate(user);
        }
    }
}
