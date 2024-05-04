package com.ufrotest.core.services.exceptions.validator.imp.user.imp;

import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.exceptions.validator.imp.user.build.UserValidator;
import com.ufrotest.core.model.UserDTO;

public class UserNullValidation extends UserValidator {
    @Override
    public boolean validate(UserDTO userDTO) throws UserValidationException {
        if (userDTO == null) {
            throw new UserValidationException("User is null");
        }
        return false;
    }
}
