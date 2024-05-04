package com.ufrotest.core.services.exceptions.validator.imp.user.build;

import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;
import com.ufrotest.core.services.exceptions.validator.out.IValidator;

public abstract class UserValidator implements IValidator<UserDTO> {
    @Override
    public abstract boolean validate(UserDTO DTO) throws UserValidationException;
}
