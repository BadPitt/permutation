package ru.badpit.permutation.cli;

import static ru.badpit.permutation.cli.InputValidator.validateArgs;

/**
 * Representation of input arguments
 *
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public class Options {

    /**
     * creates Options object for user's input
     *
     * @param args
     * @return
     */
    static Options parseOptions(String[] args) {
        validateArgs(args);

        Options result = new Options();
        int currentIndex = 0;
        while (currentIndex < args.length) {
            Commands command =
                    Commands.getCommand(args[currentIndex]);
            currentIndex++;
            if (command == null) {
                continue;
            }

            command.setToOption(result, args, currentIndex);
        }
        return result;
    }

    private InputChannel  input         = null;
    private OutputChannel output        = OutputChannels.CONSOLE;
    private boolean       isHelp        = false;
    private boolean       isVersion     = false;

    private String elementDelimiter     = "";
    private String permutationDelimiter = "\n";
    private int    maxQuantity          = 10;

    void setInput(InputChannel input) {
        this.input = input;
    }

    void setOutput(OutputChannel output) {
        this.output = output;
    }

    void setHelp(boolean help) {
        isHelp = help;
    }

    void setVersion(boolean version) {
        isVersion = version;
    }

    public void setElementDelimiter(String elementDelimiter) {
        this.elementDelimiter = elementDelimiter;
    }

    public void setPermutationDelimiter(String permutationDelimiter) {
        this.permutationDelimiter = permutationDelimiter;
    }

    public String[] getInput() {
        return input.read();
    }

    public OutputChannel getOutput() {
        return output;
    }

    public boolean isHelp() {
        return isHelp;
    }

    public boolean isVersion() {
        return isVersion;
    }

    public String getElementDelimiter() {
        return elementDelimiter;
    }

    public String getPermutationDelimiter() {
        return permutationDelimiter;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }
}
