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
public class InputChannels {
    static final Function<String, InputChannel> FILE =
            new Function<String, InputChannel>() {
                @Override
                public InputChannel apply(String s) {
                    return new InputChannel() {
                        @Override
                        public String[] read() {
                            String[] def = new String[0];
                            try {
                                return Files.readAllLines(Paths.get(s)).toArray(def);
                            } catch (IOException e) {
                                throw new ApplicationException(e.getMessage());
                            }
                        }
                    };
                }
            };
    static final Function<String, InputChannel> CONSOLE =
            new Function<String, InputChannel>() {
                @Override
                public InputChannel apply(final String s) {
                    return new InputChannel() {
                        @Override
                        public String[] read() {
                            String[] result = s.split(",");
                            for (int i = 0; i < result.length; i++) {
                                result[i] = result[i].trim();
                            }
                            return result;
                        }
                    };
                }
            };
}
