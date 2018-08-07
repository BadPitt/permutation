package ru.badpit.permutation.cli;

import ru.badpit.permutation.core.Generator;
import ru.badpit.permutation.core.Permutation;
import ru.badpit.permutation.core.PermutationGenerator;

import static ru.badpit.permutation.cli.Constants.HELP_PAGE;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/9/18.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Options options = Options.parseOptions(args);

            if (options.isHelp()) {
                System.out.println(HELP_PAGE);
                return;
            }
            if (options.isVersion()) {
                String version = Main.class.getPackage().getImplementationVersion();
                System.out.println(version);
                return;
            }

            String[] input = options.getInput();
            // todo: move to validators
            if (options.getMaxQuantity() < input.length) {
                throw new InputException(
                        String.valueOf(input.length),
                        Constants.ELEMENTS_Q_MORE_THEN_MAX_MESSAGE
                );
            }
            Generator<Permutation<String>> generator =
                    new PermutationGenerator<>(input);
            for (Permutation<String> permutation : generator) {
                options.getOutput().write(
                        Formats.DEFAULT.format(
                                permutation.getValue(),
                                options.getElementDelimiter(),
                                options.getPermutationDelimiter()
                        )
                );
            }
        } catch (InputException e) {
            String message = e.getAppMessage().replaceAll("%key%", e.getInput());
            System.err.println("Error: " + message);
        } catch (ApplicationException e) {
            String message = e.getAppMessage();
            System.err.println("Error: " + message);
        }
    }
}
