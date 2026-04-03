package com.sorting;

public class SelectionSort {

    /**
     * Sorts an array of integers in ascending order.
     * It compares the element at the current position with all subsequent elements,
     * swapping them immediately if a smaller element is found.
     *
     * @param array The integer array to be sorted.
     */
    public void selectionSortBruteForce(int[] array) {

        // OUTER LOOP: Selects the "target" position we are trying to fill.
        // As the loop progresses, the left side of the array becomes sorted.
        for (int i = 0; i < array.length; i++) {

            // INNER LOOP: Scans all elements to the right of our target position (i).
            for (int j = i + 1; j < array.length; j++) {

                // CONDITION: If the element at our target position is LARGER than the element we are checking...
                if (array[i] > array[j]) {

                    // ...we perform a SWAP to bring the smaller value to the front.

                    // Step 1: Temporarily store the larger value so it isn't erased
                    int temp = array[i];

                    // Step 2: Overwrite the target position with the newly found smaller value
                    array[i] = array[j];

                    // Step 3: Place the larger value into the spot we just pulled the smaller one from
                    array[j] = temp;
                }
            }
        }
    }
}