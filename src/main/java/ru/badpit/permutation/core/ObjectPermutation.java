package ru.badpit.permutation.core;

import java.util.Arrays;
import java.util.List;

/**
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 30.11.17.
 */
public class ObjectPermutation<T> implements Permutation<T> {

    private final List<T> value;

    ObjectPermutation(List<T> value) {
        this.value = value;
    }

    @Override
    public List<T> getValue() {
        return value;
    }

    @Override
    public int size() {
        return value.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(value.toArray());
    }
}
