package com.ufrotest.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Paths {
    BOOK_PATH("src/main/resources/data/books.json"),
    USER_PATH("src/main/resources/data/users.json");

    private final String path;
}
