package com.ufrotest.services.exceptions.validator.user.imp;

import com.ufrotest.model.UserDTO;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.out.IValidator;
import com.ufrotest.services.exceptions.validator.user.UserValidator;

public class UserNullValidation extends UserValidator {
    @Override
    public boolean validate(UserDTO userDTO) throws UserValidationException {
        if (userDTO == null) {
            throw new UserValidationException("User is null");
        }
        return false;
    }
}
