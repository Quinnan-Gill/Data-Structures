package cs445.lab8;

import java.util.Random;
import java.util.Arrays;

public class SortTiming {

    // Random number generator for shuffling
    private static Random rand = new Random();
    // Static array that will be reused through all tests
    private static Integer[] a;

    private static final String sep = ",";


    public static void main(String[] args) {
        // print data header
        System.out.println("size" + sep + "isort" + sep + "qsort" + sep + "qsort2");

        // number of trials to average for each test
        int numTrials = 1000;
        // min array size
        int minSize = 5;
        // max array size
        int maxSize = 100;
        // step size when increasing array size
        int step = 1;

        // test each size
        for (int size = minSize; size <= maxSize; size += step) {
            // build the array for the tests of this size
            a = buildArray(size);
            // print the current size
            System.out.print(size + sep);
            // print the time for insertion sort
            System.out.print(timeInsertionSort(numTrials) + sep);
            // print the time for original quick sort
            System.out.print(timeQuickSort(numTrials) + sep);
            // print the time for modified quick sort
            System.out.print(timeQuickSort2(numTrials) + sep);
            // end the line
            System.out.println("");
        }
    }

    /**
     * time improved quicksort for given number of trials
     */
    /**
     * Time improved quicksort for the given number of trials
     * @param  numTrials The number of times quicksort is to run
     * @return The time it took to run quicksort all of those trials
     */
    public static long timeQuickSort2(int numTrials) {
        long time = 0;
        // repeat the test for the specified number of trials
        for(int trial = 0; trial < numTrials; trial++) {
          // shuffle the array
          shuffle(a);
          // determine the time right before calling insertion sort
          long start = System.nanoTime();
          // call quicksort with the definied base case of 5
          quickSort(a, 10);
          insertionSort(a);
          // determine the time that's elapsed
          time += System.nanoTime() - start;
        }
        return time/numTrials;
    }

    /**
     * time insertion sort for given number of trials
     */
    /**
     * Time insertion sort for given number of trials
     * @param  numTrials The number of times insertion sort
     * @return     [description]
     */
    public static long timeInsertionSort(int numTrials) {
        long time = 0;
        // repeat the test for the specified number of trials
        for (int trial = 0; trial < numTrials; trial++) {
            // shuffle the array
            shuffle(a);
            // determine the time right before calling insertion sort
            long start = System.nanoTime();
            // call insertionSort
            insertionSort(a);
            // determine the time that's elapsed
            time += System.nanoTime() - start;
        }
        // return the average per trial
        return time/numTrials;
    }

    /**
     * time quicksort for given number of trials
     */
    public static long timeQuickSort(int numTrials) {
        long time = 0;
        // repeat the test for the specified number of trials
        for (int trial = 0; trial < numTrials; trial++) {
            // shuffle the array
            shuffle(a);
            // determine the time right before calling quickSort
            long start = System.nanoTime();
            // call quickSort
            quickSort(a);
            // determine the time that's elapsed
            time += System.nanoTime() - start;
        }
        // return the average per trial
        return time/numTrials;
    }

    /**
     * generate an int array with values 0 to cap
     */
    public static Integer[] buildArray(int cap) {
        Integer[] result = new Integer[cap];
        for (int i = 0; i < cap; i++) {
            result[i] = i;
        }
        return result;
    }

    /**
     * Shuffle an array to a random permutation using the Fisher-Yates shuffle
     */
    public static <T> void shuffle(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = rand.nextInt(i+1);
            swap(a, i, r);
        }
    }

    /**
     * basic swap method
     */
    private static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * regular old insertion sort
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] a) {
        insertionSort(a, 0, a.length);
    }

    private static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int start, int end) {
        for (int unsorted = start + 1; unsorted < end; unsorted++) {
            T nextUnsorted = a[unsorted];
            insertInOrder(nextUnsorted, a, start, unsorted);
        }
    }

    private static <T extends Comparable<? super T>>
    void insertInOrder(T entry, T[] a, int start, int end) {
        int index = end - 1;
        while (index >= start && entry.compareTo(a[index]) < 1) {
            a[index + 1] = a[index];
            index--;
        }
        a[index + 1] = entry;
    }

    /**
     * quicksort wrapper
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a) {
        quickSort(a, 0, a.length, 1);
    }

    /**
     * quicksort wrapper with base case size param
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a, int baseCase) {
        quickSort(a, 0, a.length, baseCase);
    }

    /**
     * quicksort helper method that accepts base case size param
     */
    private static <T extends Comparable<? super T>>
    void quickSort(T[] a, int start, int end, int baseCase) {
        if (end - start > baseCase) {
            int pivotIndex = partition(a, start, end);
            quickSort(a, start, pivotIndex, baseCase);
            quickSort(a, pivotIndex + 1, end, baseCase);
        }
    }

    /**
     * quicksort partition method
     */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int start, int end) {
        int pivotIndex = end - 1, lo = start, hi = end - 2;
        T pivot = a[pivotIndex];
        boolean done = false;

        while (!done) {
            while (a[lo].compareTo(pivot) < 0) {
                lo++;
            }

            while (a[hi].compareTo(pivot) > 0 && hi > start) {
                hi--;
            }

            if (lo < hi) {
                swap(a, lo, hi);
                lo++;
                hi--;
            } else {
                done = true;
            }
        }

        swap(a, pivotIndex, lo);
        pivotIndex = lo;

        return pivotIndex;
    }

}
