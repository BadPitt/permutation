package ru.badpit.permutation.cli;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public final class Constants {

    public static final String HELP_PAGE =
            " options:\n" +
                    "          -s  --sequence       : enter the sequence of symbols,\n" +
                    "                                 it haves to be in quotes and\n" +
                    "                                 separated by commas.\n" +
                    "                                 Example:\n" +
                    "                                 java -jar permutations.jar -s \"A, was, The, first\"\n" +
                    "          -f  --file           : enter the file name for input.\n" +
                    "                                 Only one key (-f or -s) can be used.\n" +
                    "    \n" +
                    "          -o  --output         : enter the file name for output. Console output by default\n" +
                    "    \n" +
                    "          -ed --elem_delimiter : delimiter for elements in sequence.\n" +
                    "                                 Empty by default.\n" +
                    "    \n" +
                    "          -pd --perm_delimiter : delimiter for every permutation.\n" +
                    "                                 \"\\n\" by default.\n" +
                    "    \n" +
                    "          -mq --max_quantity   : sets the max element's quantity in sequence.\n" +
                    "                                 10 by default.\n" +
                    "                                 Not recommended sets it more then 10.\n" +
                    "    \n" +
                    "          -h  --help           : shows this page.\n" +
                    "    \n" +
                    "          -v  --version        : shows generator's version.";

    public static final String EMPTY_INPUT_ARG_MESSAGE =
            "Empty sequence of symbols or filename. See --help for details";

    public static final String EMPTY_ARGS_MESSAGE =
            "Empty args invocation is prohibited. See --help for details";

    public static final String KEY_NOT_FOUND_MESSAGE =
            "There is not entered key(%key%). See --help for details";

    public static final String KEY_DUPLICATE_MESSAGE =
            "Key(%key%) already exists. See --help for details";

    public static final String EMPTY_KEY_MESSAGE =
            "There is not key before value. See --help for details";

    public static final String ELEMENTS_Q_MORE_THEN_MAX_MESSAGE =
            "Element's quantity(%key%) more then max element's quantity. See --help for details";

    private Constants() {}
}
