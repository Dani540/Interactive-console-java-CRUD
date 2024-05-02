package com.ufrotest.services.exceptions.validator.user.imp;

import com.ufrotest.model.UserDTO;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.out.IValidator;
import com.ufrotest.services.exceptions.validator.user.UserValidator;

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
