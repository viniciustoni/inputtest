package com.vinicius.inputtest.exception;

public class ProcessFileException extends Exception {

    private static final long serialVersionUID = -800885505406273162L;

    public ProcessFileException() {
        super();
    }

    public ProcessFileException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProcessFileException(final String message) {
        super(message);
    }

    public ProcessFileException(final Throwable cause) {
        super(cause);
    }

}
