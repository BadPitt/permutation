package ru.badpit.permutation.cli;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public class ApplicationException extends RuntimeException {
    private final String appMessage;

    public ApplicationException(String message) {
        super(message);
        this.appMessage = message;
    }

    public String getAppMessage() {
        return appMessage;
    }
}
