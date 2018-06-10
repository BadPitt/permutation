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
public class OutputChannels {
    final static OutputChannel CONSOLE =
            new OutputChannel() {
                @Override
                public void write(String data) {
                    System.out.print(data);
                }
            };
    final static Function<String, OutputChannel> FILE =
            new Function<String, OutputChannel>() {
                @Override
                public OutputChannel apply(String s) {
                    return new OutputChannel() {
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
            };
}
