package ru.badpit.permutation.core;

import java.util.*;

/**
 * Generates permutations for sequence of objects.
 *
 * @see Generator
 * @see Permutation
 *
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 27.11.17.
 */
public class PermutationGenerator<T extends Comparable<T>> implements Generator<Permutation<T>> {

    private final Comparator<T> defaultComparator = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return o1.compareTo(o2);
        }
    };

    private Permutation<T> input;

    private Comparator<T> comparator;

    public PermutationGenerator(T[] input) {
        this.input = Permutations.createFromArray(input);
        this.comparator = defaultComparator;
    }

    public PermutationGenerator(T[] input, Comparator<T> comparator) {
        this.input = Permutations.createFromArray(input);
        this.comparator = comparator;
    }

    public PermutationGenerator(List<T> input) {
        this.input = Permutations.createFromList(input);
        this.comparator = defaultComparator;
    }

    public PermutationGenerator(List<T> input, Comparator<T> comparator) {
        this.input = Permutations.createFromList(input);
        this.comparator = comparator;
    }

    /**
     * Generates all possible permutation for current
     * sequence in lexicographic order
     *
     */
    @Override
    public List<Permutation<T>> generateAll() {
        Permutation<T> firstPermutation =
                Permutations.createFirstPermutation(input, comparator);
        input = firstPermutation;
        List<Permutation<T>> result = new ArrayList<>(getPermutationsCount(firstPermutation.size()));
        result.add(firstPermutation);
        Permutation<T> prevPermutation = firstPermutation;
        Permutation<T> currentPermutation;
        for (int i = 0; i < getPermutationsCount(firstPermutation.size()) - 1; i++) {
            currentPermutation = next();
            if (Objects.equals(currentPermutation, prevPermutation)) {
                break;
            }
            prevPermutation = currentPermutation;
            result.add(currentPermutation);
        }
        return result;
    }

    @Override
    public Permutation<T> next() {
        List<T> value = new ArrayList<>(input.getValue());
        int tailsIndex = findTailsIndex(value, comparator);
        if (tailsIndex == 0) {
            return input;
        }
        swapTailsElementWithPreTailElement(value, tailsIndex, comparator);
        invertTail(value, tailsIndex);
        input = Permutations.createFromList(value);
        return input;
    }

    @Override
    public boolean isDone() {
        return findTailsIndex(input.getValue(), comparator) == 0;
    }

    @Override
    public Iterator<Permutation<T>> iterator() {
        return new IteratorImpl();
    }

    /**
     * Inverts tail's elements order
     *
     * @param input         input values, object's sequence of current generator
     * @param tailsIndex    index of tail's start
     */
    private void invertTail(List<T> input, int tailsIndex) {
        for (int k = 1; k < input.size(); k++) {
            for (int i = tailsIndex; i < input.size() - k; i++) {
                if (i + 1 == input.size()) {
                    return;
                }
                swap(input, i, i + 1);
            }
        }
    }

    /**
     * Swaps two elements:
     * A. Element is directly before tail
     * B. Element is the first from tail's end that grater then A
     *
     * @param input         input values, object's sequence of current generator
     * @param tailsIndex    index of tail's start
     * @param comparator    element's comparator
     */
    private void swapTailsElementWithPreTailElement(List<T> input,
                                                    int tailsIndex,
                                                    Comparator<T> comparator) {
        int elementIndex = tailsIndex;
        for (int i = input.size() - 1; i >= tailsIndex; i--) {
            if (comparator.compare(input.get(i), input.get(tailsIndex - 1)) > 0) {
                elementIndex = i;
                break;
            }
        }
        swap(input, tailsIndex - 1, elementIndex);
    }

    /**
     * Looking for tail's start. It must be index
     * of start the longest
     * downgrade(for natural order) or
     * upgrade(for invert order) sequence
     *
     * @param input         input values, object's sequence of current generator
     *
     * @return index of tail's start
     */
    private int findTailsIndex(List<T> input, Comparator<T> comparator) {
        int tailsIndex = -1;
        for (int i = 0; i < input.size(); i++) {
            if (tailsIndex == -1 && i + 1 == input.size()) {
                return 0;
            } else if (i + 1 == input.size()) {
                return tailsIndex;
            }
            if (comparator.compare(input.get(i), input.get(i + 1)) < 0) {
                tailsIndex = i + 1;
            }
        }
        return tailsIndex;
    }

    private void swap(List<T> input, int index1, int index2) {
        T old = input.get(index1);
        input.set(index1, input.get(index2));
        input.set(index2, old);
    }

    private Integer getPermutationsCount(int size) {
        return factorial(size);
    }

    private int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    private class IteratorImpl implements Iterator<Permutation<T>> {
        private boolean isFirst = true;
        public IteratorImpl() {
            input = Permutations.createFirstPermutation(input, comparator);
        }

        @Override
        public Permutation<T> next() {
            List<T> value = new ArrayList<>(input.getValue());
            int tailsIndex = findTailsIndex(value, comparator);
            if (tailsIndex == 0 || isFirst) {
                isFirst = false;
                return input;
            }
            swapTailsElementWithPreTailElement(value, tailsIndex, comparator);
            invertTail(value, tailsIndex);
            input = Permutations.createFromList(value);
            return input;
        }

        @Override
        public boolean hasNext() {
            return !(findTailsIndex(input.getValue(), comparator) == 0);
        }
    }
}
