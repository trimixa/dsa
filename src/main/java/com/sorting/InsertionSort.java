package com.sorting;

public class InsertionSort {

    /**
     * Insertion sort builds a sorted array one element at a time by repeatedly picking
     * the next element and inserting it into its correct position within the already
     * sorted part of the array.
     *
     * Approach:
     * 1. In each iteration, select an element from the unsorted part of the array.
     * 2. Place this element in its correct position within the sorted part.
     * 3. Use an inner loop to shift the remaining larger elements to the right
     * until the correct spot for the selected element is found.
     *
     * @param arr The integer array to be sorted.
     */
    public void insertionSort(int[] arr) {
        int n = arr.length;

        // OUTER LOOP: Start at index 1.
        // We assume the first element (index 0) is already "sorted" in its own sub-list.
        for (int i = 1; i < n; i++) {

            // Step 1: Select the element we want to position in this iteration.
            // We save it in 'key' because we might overwrite its original spot during shifting.
            int key = arr[i];

            // Step 2: Set 'j' to point to the last element of the ALREADY SORTED section.
            // This is the element immediately to the left of our key.
            int j = i - 1;

            // INNER LOOP: Compare the 'key' with elements in the sorted section (moving right to left).
            // Keep going as long as we haven't reached the start of the array (j >= 0)
            // AND the current sorted element is larger than our key.
            while (j >= 0 && arr[j] > key) {

                // The element is larger than the key, so we shift it one spot to the right
                // to make room for our key.
                arr[j + 1] = arr[j];

                // Move one spot further left to check the next element in the sorted section.
                j--;
            }

            // Step 3: We found the correct spot! (Either we reached the start of the array,
            // or we found an element smaller than the key).
            // Insert the key into its final sorted position.
            arr[j + 1] = key;
        }
    }
}