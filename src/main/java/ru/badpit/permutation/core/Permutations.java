package ru.badpit.permutation.core;

import java.util.*;

/**
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 30.11.17.
 */
public final class Permutations {
    private Permutations() {
    }

    public static <T> Permutation<T> createFromList(List<T> value) {
        return new ObjectPermutation<>(new ArrayList<>(value));
    }

    public static <T> Permutation<T> createFromArray(T[] value) {
        return new ObjectPermutation<>(Arrays.asList(value));
    }

    public static <T> Permutation<T> copy(Permutation<T> value) {
        return createFromList(value.getValue());
    }

    public static <T extends Comparable<T>> Permutation<T> createFirstPermutation(Permutation<T> value) {
        List<T> dest = new ArrayList<>(value.getValue());
        Collections.sort(dest);
        return createFromList(dest);
    }

    public static <T> Permutation<T> createFirstPermutation(Permutation<T> value, Comparator<T> comparator) {
        List<T> dest = new ArrayList<>(value.getValue());
        dest.sort(comparator);
        return createFromList(dest);
    }
}
