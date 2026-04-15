package dsa.main;

import dsa.sorting.Sort;
import dsa.utility.DataGenerators;

import java.util.Arrays;

public class Main {
    static void main() {
        int[] array = DataGenerators.generatePositiveIntArray(20,10,99);
        System.out.println(Arrays.toString(array));

        Sort sorting = new Sort();
        sorting.mergeSort(array);
        System.out.println(Arrays.toString(array));

    }
}

