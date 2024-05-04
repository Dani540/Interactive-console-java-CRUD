package com.ufrotest.constants;

import com.ufrotest.core.model.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserData {
    private static UserData instance;
    @Setter
    private UserDTO userLogged = null;

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
}
