package com.ufrotest.core.services.exceptions.validator.imp.user.imp;

import com.ufrotest.core.services.exceptions.validator.imp.user.build.UserValidator;
import com.ufrotest.core.model.UserDTO;
import com.ufrotest.core.services.exceptions.imp.UserValidationException;

public class UserErrorFormat extends UserValidator {
    @Override
    public boolean validate(UserDTO DTO) throws UserValidationException {
        byId(DTO);
        byUsername(DTO,3);
        byPassword(DTO, 8);
        return false;
    }

    private void byId(UserDTO user) throws UserValidationException {
        if (user.id() < 0){
            throw new UserValidationException("Id is invalid");
        }
    }

    private void byUsername(UserDTO user, int length) throws UserValidationException {
        if (user.username().length() < length) {
            throw new UserValidationException("Username is invalid, least" + length + " characters");
        }
    }

    private void byPassword(UserDTO user, int length) throws UserValidationException{
        if( user.password().length() < length){
            throw new UserValidationException("Password is invalid, least " + length + " characters");
        }
    }


}
