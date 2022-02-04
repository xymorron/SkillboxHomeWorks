package com.example.bookshop.errs;

public class BookstoreApiWrongParameterException extends Throwable {
    public BookstoreApiWrongParameterException(String message) {
        super(message);
    }
}
