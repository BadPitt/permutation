package ru.badpit.permutation.core;

import java.util.List;

/**
 * @author Danil Popov
 * BadPit@211.ru
 * on 6/5/18.
 */
public interface Generator<T> extends Iterable<T> {
    List<T> generateAll();
    T next();
    boolean isDone();
}
