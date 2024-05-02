package com.ufrotest.data;

import com.ufrotest.data.out.IFileHandler;
import com.ufrotest.data.out.IJsonHandler;

public record DataHandler<E>(IFileHandler fileHandler, IJsonHandler<E> jsonHandler) {
}
