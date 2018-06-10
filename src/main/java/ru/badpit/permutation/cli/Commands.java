package ru.badpit.permutation.cli;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public enum Commands {
    VERSION(new String[]{"-v", "--version"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            options.setVersion(true);
        }
    },
    HELP(new String[]{"-h", "--help"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            options.setHelp(true);
        }
    },
    INPUT(new String[]{"-s", "--sequence", "-f", "--file"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            if (index < args.length) {
                if ("-f".equals(args[index-1]) ||
                        "--file".equals(args[index-1])) {
                    options.setInput(InputChannels.FILE.apply(args[index]));
                } else {
                    options.setInput(InputChannels.CONSOLE.apply(args[index]));
                }
            } else {
                throw new InputException(
                        args[index-1],
                        Constants.EMPTY_INPUT_ARG_MESSAGE);
            }
        }
    },
    OUTPUT(new String[]{"-o", "--output"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            if (index < args.length) {
                options.setOutput(OutputChannels.FILE.apply(args[index]));
            }
        }
    },
    DELIMITER_E(new String[]{"-ed", "--elem_delimiter"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            if (index < args.length) {
                options.setElementDelimiter(args[index]);
            }
        }
    },
    DELIMITER_P(new String[]{"-pd", "--perm_delimiter"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            if (index < args.length) {
                options.setPermutationDelimiter(args[index]);
            }
        }
    },
    MAX_QUANTITY(new String[]{"-mq", "--max_quantity"}) {
        @Override
        void setToOption(Options options,
                         String[] args,
                         int index) {
            options.setMaxQuantity(Integer.parseInt(args[index]));
        }
    },
    ;

    private String[] keys;

    Commands(String[] keys) {
        this.keys = keys;
    }

    static Commands getCommand(String str) {
        Commands result = null;
        for (Commands command : Commands.values()) {
            boolean isFound = false;
            for (String key: command.keys) {
                if (key.equals(str)) {
                    isFound = true;
                }
            }
            if (isFound) {
                result = command;
            }
        }
        return result;
    }

    abstract void setToOption(Options options,
                              String[] args,
                              int index);
}
