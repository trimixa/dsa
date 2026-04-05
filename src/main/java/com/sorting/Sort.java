package com.sorting;

public class Sort {
    public void SelectionSort(int[] array) {

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

    public void InsertionSort(int[] arr) {
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

    public void BubbleSort(int[] arr) {
        int n = arr.length;

        // OUTER LOOP: 'i' is the boundary of our unsorted section.
        // It starts at the last index and shrinks backward by 1 after each full pass.
        // We stop at i > 0 because a single element left at index 0 is already "sorted".
        for (int i = n - 1; i > 0; i--) {

            // OPTIMIZATION: Keep track of whether any swaps happened during this pass.
            // If we go through a whole pass without swapping, the array is perfectly sorted!
            boolean swapped = false;

            // INNER LOOP: Scan from the beginning up to our unsorted boundary 'i'.
            for (int j = 0; j < i; j++) {

                // Compare the current element with its direct right-hand neighbor.
                if (arr[j] > arr[j + 1]) {

                    // The left element is heavier, so we perform a SWAP to "bubble" it right.
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // We made a swap, so flip our tracker to true.
                    swapped = true;
                }
            }

            // If the inner loop finished and NO swaps occurred, we can stop early.
            // This prevents the algorithm from doing unnecessary work on a sorted array.
            if (!swapped) {
                break;
            }
        }
    }

    public void MergeSort(int[] arr) {
        int n = arr.length;

    }
}
