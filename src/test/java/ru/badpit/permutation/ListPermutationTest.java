package ru.badpit.permutation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Danil Popov BadPit@211.ru on 03.12.17.
 */
public class ListPermutationTest {
    @Test
    public void test() {
        List<Integer> source = Arrays.asList(1, 2, 3, 4);
        getPermutation(source);
    }

    private void getPermutation(List<Integer> source) {
        List<Entry<Integer>> lists = getLists(source);
        System.out.println(Arrays.deepToString(lists.toArray()));
        List<List<Integer>> result = new ArrayList<>();
        //List<Integer> res = new ArrayList<>();

        Entry<Integer> outerEntity = lists.get(lists.size() - 1);
        while (outerEntity.hasNext()) {
            result.add(outerEntity.next());
        }
        System.out.println(Arrays.deepToString(result.toArray()));
    }

    private List<Entry<Integer>> getLists(List<Integer> source) {
        List<Entry<Integer>> result = new ArrayList<>();
        Entry<Integer> inner = null; //
        List<Integer> entries = null;
        for (int i = source.size() - 2; i >= 0; i--) {
            entries = new ArrayList<>(source.subList(i, source.size()));
            result.add(new Entry<Integer>(inner, entries, 0));
            inner = result.get(result.size() - 1);
        }
        return result;
    }

    public static class Entry<T> {
        private Entry<T> inner;
        private List<T> entries;
        private int index;

        public Entry(List<T> entries, int index) {
            this.entries = entries;
            this.index = index;
        }

        public Entry(Entry<T> inner, List<T> entries, int index) {
            this.inner = inner;
            this.entries = entries;
            this.index = index;
        }

        public boolean hasNext() {
            if (inner == null) {
                return index < entries.size() - 1;
            }
            return inner.hasNext() || index < entries.size() - 1;
        }

        public List<T> next() {
            if (!hasNext() && inner != null) {
                index = 0;
            }
            if (inner == null) {
                if (index == 0) {
                    index++;
                    return entries;
                }
                List<T> result = new ArrayList<T>(entries);
                Collections.reverse(result);
                index=0;
                return result;
            }
            boolean hasNext = inner.hasNext();
            List<T> next = new ArrayList<>(inner.next());
            next.add(0, entries.get(index));
            if (!hasNext) {
                index++;
            }
            return next;
            // return new ArrayList<>();
        }

        public Entry<T> getInner() {
            return inner;
        }

        public void setInner(Entry<T> inner) {
            this.inner = inner;
        }

        public List<T> getEntries() {
            return entries;
        }

        public void setEntries(List<T> entries) {
            this.entries = entries;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "entries=" + Arrays.toString(entries.toArray()) +
                    '}';
        }
    }
}
