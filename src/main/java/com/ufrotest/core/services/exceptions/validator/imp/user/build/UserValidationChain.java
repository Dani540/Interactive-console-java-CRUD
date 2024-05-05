package com.ufrotest.core.services.exceptions.validator.imp.user.build;

import com.ufrotest.core.services.exceptions.validator.out.IValidationChain;
import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.user.imp.UserErrorFormat;
import com.ufrotest.core.services.exceptions.validator.imp.user.imp.UserNullValidation;

import java.util.List;
public class UserValidationChain implements IValidationChain<UserDTO> {
    private final List<UserValidator> validators;

    public UserValidationChain() {
        validators = List.of(
                new UserNullValidation(),
                new UserErrorFormat()
        );
    }

    public void validate(UserDTO user) throws UserValidationException{
        for (UserValidator validator : validators) {
            validator.validate(user);
        }
    }
}
