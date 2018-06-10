package ru.badpit.permutation.core;

import java.util.*;

/**
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 30.11.17.
 */
public final class Permutations {

    public static <Type> Permutation<Type> createFromList(List<Type> value) {
        return new ObjectPermutation<>(new ArrayList<>(value));
    }

    public static <Type> Permutation<Type> createFromArray(Type[] value) {
        return new ObjectPermutation<>(Arrays.asList(value));
    }

    public static <Type> Permutation<Type> copy(Permutation<Type> value) {
        return createFromList(value.getValue());
    }

    public static <Type extends Comparable<Type>> Permutation<Type> createFirstPermutation(Permutation<Type> value) {
        List<Type> dest = new ArrayList<>(value.getValue());
        Collections.sort(dest);
        return createFromList(dest);
    }

    public static <Type> Permutation<Type> createFirstPermutation(Permutation<Type> value, Comparator<Type> comparator) {
        List<Type> dest = new ArrayList<>(value.getValue());
        dest.sort(comparator);
        return createFromList(dest);
    }

    private Permutations() {
    }
}
