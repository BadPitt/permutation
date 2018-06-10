package ru.badpit.permutation.core;

import java.util.*;

/**
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 28.11.17.
 */
public interface Permutation<T> {
    int size();
    List<T> getValue();
}
