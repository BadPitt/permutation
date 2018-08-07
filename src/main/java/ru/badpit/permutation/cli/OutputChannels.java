package ru.badpit.permutation.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public final class OutputChannels {
    private OutputChannels() {
    }

    static final OutputChannel CONSOLE = System.out::print;
    static final Function<String, OutputChannel> FILE =
            s -> new OutputChannel() {
                String filename = s;

                @Override
                public void write(String data) {
                    try {
                        Files.write(
                                Paths.get(filename),
                                data.getBytes(),
                                CREATE, APPEND
                        );
                    } catch (IOException e) {
                        throw new ApplicationException(e.getMessage());
                    }
                }
            };
}
