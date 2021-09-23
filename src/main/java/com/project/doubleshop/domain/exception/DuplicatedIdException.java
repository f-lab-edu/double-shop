package com.project.doubleshop.domain.exception;

public class DuplicatedIdException extends IllegalArgumentException {

    public DuplicatedIdException() {
    }

    public DuplicatedIdException(String message) {
        super(message);
    }

}
