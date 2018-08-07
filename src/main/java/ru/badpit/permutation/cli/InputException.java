package ru.badpit.permutation.cli;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public class InputException extends ApplicationException {
    private final String input;

    public InputException(String input,
                          String message) {
        super(message);
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
