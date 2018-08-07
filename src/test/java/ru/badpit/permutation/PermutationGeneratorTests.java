package ru.badpit.permutation;

import org.junit.Test;
import ru.badpit.permutation.core.Generator;
import ru.badpit.permutation.core.Permutation;
import ru.badpit.permutation.core.PermutationGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Danil Popov
 *         BadPit@211.ru
 *         on 27.11.17.
 */
public class PermutationGeneratorTests {

    @Test
    public void generateAllForTwoTest() {
        Integer[] input = new Integer[]{1,2};
        List<Permutation<Integer>> allPermutations =
                new PermutationGenerator<>(input).generateAll();
        assertEquals(2, allPermutations.size());
        assertTrue(Objects.equals(Arrays.asList(input), allPermutations.get(0).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,1), allPermutations.get(1).getValue()));
    }

    @Test
    public void generateAllForThreeTest() {
        Integer[] input = new Integer[]{1,2,3};
        List<Permutation<Integer>> allPermutations =
                new PermutationGenerator<>(input).generateAll();
        assertEquals(6, allPermutations.size());
        assertTrue(Objects.equals(Arrays.asList(input), allPermutations.get(0).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,3,2), allPermutations.get(1).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,1,3), allPermutations.get(2).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,3,1), allPermutations.get(3).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,1,2), allPermutations.get(4).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,2,1), allPermutations.get(5).getValue()));
    }

    @Test
    public void nextAllForThreeTest() {
        Integer[] input = new Integer[]{1,2,3};
        Generator<Permutation<Integer>> generator = new PermutationGenerator<>(input);
        assertFalse(generator.isDone());
        assertTrue(Objects.equals(Arrays.asList(1,3,2), generator.next().getValue()));
        assertFalse(generator.isDone());
        assertTrue(Objects.equals(Arrays.asList(2,1,3), generator.next().getValue()));
        assertFalse(generator.isDone());
        assertTrue(Objects.equals(Arrays.asList(2,3,1), generator.next().getValue()));
        assertFalse(generator.isDone());
        assertTrue(Objects.equals(Arrays.asList(3,1,2), generator.next().getValue()));
        assertFalse(generator.isDone());
        assertTrue(Objects.equals(Arrays.asList(3,2,1), generator.next().getValue()));
        assertTrue(generator.isDone());
    }

    @Test
    public void generateAllForFourTest() {
        Integer[] input = new Integer[]{1,2,3,4};
        List<Permutation<Integer>> allPermutations =
                new PermutationGenerator<>(input).generateAll();
        assertEquals(24, allPermutations.size());
        assertTrue(Objects.equals(Arrays.asList(input), allPermutations.get(0).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,2,4,3), allPermutations.get(1).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,3,2,4), allPermutations.get(2).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,3,4,2), allPermutations.get(3).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,4,2,3), allPermutations.get(4).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,4,3,2), allPermutations.get(5).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,1,3,4), allPermutations.get(6).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,1,4,3), allPermutations.get(7).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,3,1,4), allPermutations.get(8).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,3,4,1), allPermutations.get(9).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,4,1,3), allPermutations.get(10).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,4,3,1), allPermutations.get(11).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,1,2,4), allPermutations.get(12).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,1,4,2), allPermutations.get(13).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,2,1,4), allPermutations.get(14).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,2,4,1), allPermutations.get(15).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,4,1,2), allPermutations.get(16).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,4,2,1), allPermutations.get(17).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,1,2,3), allPermutations.get(18).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,1,3,2), allPermutations.get(19).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,2,1,3), allPermutations.get(20).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,2,3,1), allPermutations.get(21).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,3,1,2), allPermutations.get(22).getValue()));
        assertTrue(Objects.equals(Arrays.asList(4,3,2,1), allPermutations.get(23).getValue()));
    }

    @Test
    public void generateAllReversForThreeTest() {
        Integer[] input = new Integer[]{1,2,3};
        List<Permutation<Integer>> allPermutations =
                new PermutationGenerator<>(input, Comparator.reverseOrder())
                        .generateAll();
        assertEquals(6, allPermutations.size());
        assertTrue(Objects.equals(Arrays.asList(3,2,1), allPermutations.get(0).getValue()));
        assertTrue(Objects.equals(Arrays.asList(3,1,2), allPermutations.get(1).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,3,1), allPermutations.get(2).getValue()));
        assertTrue(Objects.equals(Arrays.asList(2,1,3), allPermutations.get(3).getValue()));
        assertTrue(Objects.equals(Arrays.asList(1,3,2), allPermutations.get(4).getValue()));
        assertTrue(Objects.equals(Arrays.asList(input), allPermutations.get(5).getValue()));
    }

    @Test
    public void invertTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int count = 8;
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            input.add(i);
        }
        invertTailInvocation(input, 0);
        System.out.println(Arrays.toString(input.toArray()));
        assertEquals("[8, 7, 6, 5, 4, 3, 2, 1]", Arrays.toString(input.toArray()));
    }

    @Test
    public void invertTest2() throws IllegalAccessException, InvocationTargetException {
        int count = 2;
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            input.add(i);
        }
        invertTailInvocation(input, 0);
        System.out.println(Arrays.toString(input.toArray()));
        assertEquals("[2, 1]", Arrays.toString(input.toArray()));
    }

    @Test
    public void invertTest3() throws IllegalAccessException, InvocationTargetException {
        int count = 3;
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            input.add(i);
        }
        invertTailInvocation(input, 0);
        System.out.println(Arrays.toString(input.toArray()));
        assertEquals("[3, 2, 1]", Arrays.toString(input.toArray()));
    }

    @Test
    public void generateForwardPermutationFromArrayTest() {
        Integer[] array = new Integer[]{1, 3, 4, 2, 5, 10};
        List<Permutation<Integer>> permutations =
                new PermutationGenerator<>(array).generateAll();
        System.out.println(Arrays.toString(permutations.toArray()));
        assertEquals(6*5*4*3*2, permutations.size());
    }

    @Test
    public void permutationWithEqualsElementsTest() {
        Integer[] array = new Integer[]{1, 2, 2, 2};
        List<Permutation<Integer>> permutations =
                new PermutationGenerator<>(array).generateAll();
        System.out.println(Arrays.toString(permutations.toArray()));
        assertEquals(4, permutations.size());
    }

    @Test
    public void permutationWithTwoEqualsElementsTest() {
        Integer[] array = new Integer[]{1, 3, 2, 2};
        List<Permutation<Integer>> permutations =
                new PermutationGenerator<>(array).generateAll();
        System.out.println(Arrays.toString(permutations.toArray()));
        assertEquals(12, permutations.size());
    }

    private <T extends Comparable<T>> void invertTailInvocation(List<T> input, int i) throws InvocationTargetException, IllegalAccessException {
        PermutationGenerator<T> permutationGenerator =
                new PermutationGenerator<>(input);
        Method invertTailMethod = null;
        for (Method method : permutationGenerator.getClass().getDeclaredMethods()) {
            if ("invertTail".equals(method.getName())) {
                invertTailMethod = method;
                break;
            }
        }
        if (invertTailMethod == null) {
            return;
        }
        invertTailMethod.setAccessible(true);
        invertTailMethod.invoke(permutationGenerator, input, i);
    }

    @Test
    public void forEachTest() {
        Integer[] input = {1,2,3};
        for (Permutation<Integer> permutation: new PermutationGenerator<>(input)) {
            System.out.println(Arrays.toString(permutation.getValue().toArray()));
        }
    }
}
