package io.wangxiao.settings.errors;

import org.springframework.validation.Errors;

/**
 * This is an exception that let us return a json with
 * all the validations errors
 */
public class InvalidRequestException extends RuntimeException {

    private Errors errors;

    /**
     * Contructor that get the message and the errors object
     * @param message - a custom message as string
     * @param errors - the Errors that we have
     */
    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() { return errors; }

}
