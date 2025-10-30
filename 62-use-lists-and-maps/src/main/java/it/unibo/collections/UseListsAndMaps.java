package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    private static final int ZERO = 0;
    private static final int LAST = 999;
    private static final int ELEM = 100000;

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> al= new ArrayList<Integer>();
        for(int i = 1000; i < 2000; i++){
            al.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> ll = IntStream.rangeClosed(1000, 2000).boxed().collect(Collectors.toCollection(LinkedList::new));
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        var a = al.get(ZERO);
        var b = al.get(LAST);
        al.set(LAST, a);
        al.set(ZERO,b);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for(Integer v : al){
            System.out.println(v);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long timeArrayList = System.nanoTime();
        for(int i = ZERO; i < ELEM; i++){
            al.add(ZERO, i);
        }
        timeArrayList = System.nanoTime() - timeArrayList;

        long timeLinkedList = System.nanoTime();
        for(int i = ZERO; i < ELEM; i++){
            ll.add(ZERO, i);
        }
        timeLinkedList = System.nanoTime() - timeLinkedList;

        final var millisArrayList = TimeUnit.NANOSECONDS.toMillis(timeArrayList);
        final var millisLinkedList = TimeUnit.NANOSECONDS.toMillis(timeLinkedList);
        System.out.println(
                "time required to add 100.000 elements as" +
                "first element of the collection in ArrayList "
                + timeArrayList
                + "ns ("
                + millisArrayList
                + "ms)"
        );
        System.out.println(
                "time required to add 100.000 elements as" +
                "first element of the collection in LinkedList "
                + timeLinkedList
                + "ns ("
                + millisLinkedList
                + "ms)"
        );
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        var middleArrayList = al.size()/2;
        var middleLinkedList = ll.size()/2;

        long timeArrayListr = System.nanoTime();
        for(int i = ZERO; i<1000; i++){
            al.get(middleArrayList);
        }
        timeArrayListr = System.nanoTime() - timeArrayListr;

        long timeLinkedListr = System.nanoTime();
        for(int i = ZERO; i<1000; i++){
            ll.get(middleLinkedList);
        }
        timeLinkedListr = System.nanoTime() - timeLinkedListr;

        final var millisArrayListr = TimeUnit.NANOSECONDS.toMillis(timeArrayListr);
        final var millisLinkedListr = TimeUnit.NANOSECONDS.toMillis(timeLinkedListr);
        System.out.println(
                "time required to eading 1000 times an element whose\n" + //
                "position is in the middle of the collection in ArrayList " +
                + timeArrayListr
                + "ns ("
                + millisArrayListr
                + "ms)"
        );
        System.out.println(
                "time required to eading 1000 times an element whose\n" + //
                "position is in the middle of the collection in LinkedList " +
                + timeLinkedListr
                + "ns ("
                + millisLinkedListr
                + "ms)"
        );

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> map = new HashMap<>();
        map.put("Africa", 1110635000L);
        map.put("Americas", 972005000L);
        map.put("Antartica", 0L);
        map.put("Asia", 4298723000L);
        map.put("Europe", 742452000L);
        map.put("Oceania", 38304000L);

        /*
         * 8) Compute the population of the world
         */
        long totalPopulation = 0;
        for(long i : map.values()){
            totalPopulation += i;
        }
        System.out.println("Sum of Africa, Americas, Antartica, Asia, Europoe, Oceania: " + totalPopulation);
    }
}
