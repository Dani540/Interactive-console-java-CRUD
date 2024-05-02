package com.ufrotest.services.exceptions.validator.user;

import com.ufrotest.model.UserDTO;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.out.IValidator;

public abstract class UserValidator implements IValidator<UserDTO> {
    @Override
    public abstract boolean validate(UserDTO DTO) throws UserValidationException;
}
