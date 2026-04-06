package com.main;

import com.sorting.*;

import java.util.Arrays;

public class Main {
    static void main() {
        int[] array = DataGenerators.generatePositiveIntArray(20,10,99);
        System.out.println(Arrays.toString(array));

        Sort sorting = new Sort();
        sorting.mergeSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
}

