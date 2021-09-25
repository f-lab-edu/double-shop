package com.project.doubleshop.domain.exception;

public class DuplicatedEmailException extends IllegalArgumentException {

    public DuplicatedEmailException() {
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }

}
