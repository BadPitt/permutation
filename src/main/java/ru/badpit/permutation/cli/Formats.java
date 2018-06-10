package ru.badpit.permutation.cli;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/10/18.
 */
public enum Formats {
    DEFAULT {
        @Override
        String format(List<String> data,
                      String elementDelimiter,
                      String permutationDelimiter) {
            return data.stream()
                    .collect(
                            Collectors.joining(elementDelimiter)
                    ) + permutationDelimiter;
        }
    },
    ;
    abstract String format(List<String> data,
                           String elementDelimiter,
                           String permutationDelimiter);
}
