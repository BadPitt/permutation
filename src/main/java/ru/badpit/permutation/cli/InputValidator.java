package ru.badpit.permutation.cli;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public class InputValidator {
    // todo: add validation for every command
    public static void validateArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new InputException("", Constants.EMPTY_ARGS_MESSAGE);
        }
        // all commands are valid
        Set<Commands> commandsSet = new HashSet<>();
        for (String inputKey: args) {
            if (isCommand(inputKey)) {
                Commands command = Commands.getCommand(inputKey);
                if (command == null) {
                    throw new InputException(inputKey,
                            Constants.KEY_NOT_FOUND_MESSAGE);
                }
                if (commandsSet.contains(command)) {
                    throw new InputException(inputKey,
                            Constants.KEY_DUPLICATE_MESSAGE);
                }
                commandsSet.add(command);
            }
        }
        // all arguments are valid
        for (int i = 0; i < args.length; i++) {
            if (!isCommand(args[i]) &&
                    (i-1 < 0 || !isCommand(args[i-1]))) {
                throw new InputException(args[i],
                        Constants.EMPTY_KEY_MESSAGE);
            }
        }
    }

    private static boolean isCommand(String inputKey) {
        return inputKey.startsWith("-") ||
                inputKey.startsWith("--");
    }
}
