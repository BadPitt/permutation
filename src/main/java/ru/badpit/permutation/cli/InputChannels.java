package ru.badpit.permutation.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public final class InputChannels {
    private InputChannels() {
    }

    static final Function<String, InputChannel> FILE =
            s -> (InputChannel) () -> {
                String[] def = new String[0];
                try {
                    return Files.readAllLines(Paths.get(s)).toArray(def);
                } catch (IOException e) {
                    throw new ApplicationException(e.getMessage());
                }
            };
    static final Function<String, InputChannel> CONSOLE =
            s -> (InputChannel) () -> {
                String[] result = s.split(",");
                for (int i = 0; i < result.length; i++) {
                    result[i] = result[i].trim();
                }
                return result;
            };
}
