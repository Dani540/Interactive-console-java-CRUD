package com.ufrotest.repositories;

import com.ufrotest.data.DataHandler;
import com.ufrotest.data.imp.FileHandler;
import com.ufrotest.data.imp.JsonHandler;
import com.ufrotest.model.UserDTO;
import com.ufrotest.services.exceptions.imp.UserValidationException;
import com.ufrotest.services.exceptions.validator.user.UserValidationChain;
import com.ufrotest.services.exceptions.validator.user.UserValidator;
import com.ufrotest.services.exceptions.validator.user.imp.UserNullValidation;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.ufrotest.utils.Paths.USER_PATH;

public class UserRepo extends RepositoryABS<UserDTO>{

    public UserRepo() {
        super(
                UserDTO.class,
                new DataHandler<>(
                        new FileHandler(),
                        new JsonHandler<>()
                ),
                USER_PATH.getPath()
        );
    }
}
