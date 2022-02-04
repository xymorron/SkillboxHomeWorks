package com.example.bookshop.errs;

public class EmptySearchException extends Throwable {
    public EmptySearchException(String message) {
        super(message);
    }
}
