package com.ufrotest.core.repositories;

import com.ufrotest.data.DataHandler;
import com.ufrotest.data.imp.FileHandler;
import com.ufrotest.data.imp.JsonHandler;
import com.ufrotest.core.model.UserDTO;

import static com.ufrotest.constants.Paths.USER_PATH;

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
