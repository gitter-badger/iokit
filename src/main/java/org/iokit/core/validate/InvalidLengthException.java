package org.iokit.core.validate;

public class InvalidLengthException extends ValidatorException {

    public InvalidLengthException(String input, int minLength) {
        super("input [%s] must contain at least %d character(s)", input, minLength);
    }
}