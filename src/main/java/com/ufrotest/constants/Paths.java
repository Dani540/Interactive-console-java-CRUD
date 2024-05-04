package com.ufrotest.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Paths {
    BOOK_PATH(System.getProperty("user.dir") + "/data/books.json"),
    USER_PATH(System.getProperty("user.dir")+ "/data/users.json");

    private final String path;
}
