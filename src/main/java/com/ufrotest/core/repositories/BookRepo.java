package com.ufrotest.core.repositories;

import com.ufrotest.data.DataHandler;
import com.ufrotest.data.imp.FileHandler;
import com.ufrotest.data.imp.JsonHandler;
import com.ufrotest.core.model.BookDTO;

import static com.ufrotest.constants.Paths.BOOK_PATH;

public class BookRepo extends RepositoryABS<BookDTO>{

    public BookRepo(Class<BookDTO> type, DataHandler<BookDTO> dataHandler, String path) {
        super(type, dataHandler, path);
    }

    public BookRepo() {
        super(
                BookDTO.class,
                new DataHandler<>(
                        new FileHandler(),
                        new JsonHandler<>()
                ),
                BOOK_PATH.getPath()
        );
    }
}
