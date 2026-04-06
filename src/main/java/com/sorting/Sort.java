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

    public void mergeSort(int[] array, int left, int right) {

        // Base Condition: If left is greater than or equal to right, it means the array
        // has 1 or 0 elements, which is already sorted by definition. We stop dividing.
        if (left < right) {

            // Step 1: Find the middle point to divide the array into two halves.
            // (left + right) / 2 can cause integer overflow for huge arrays,
            // so left + (right - left) / 2 is the safer, standard approach.
            int mid = left + (right - left) / 2;

            // Step 2: Recursively sort the first half (Left side)
            mergeSort(array, left, mid);

            // Step 3: Recursively sort the second half (Right side)
            mergeSort(array, mid + 1, right);

            // Step 4: Merge the two sorted halves back together
            merge(array, left, mid, right);
        }
    }

    /**
     * This helper method takes two sorted sub-arrays and merges them into a single sorted array.
     * The first sub-array is array[left...mid]
     * The second sub-array is array[mid+1...right]
     */
    private void merge(int[] array, int left, int mid, int right) {

        // Find the sizes of the two sub-arrays to be merged
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;

        // Create temporary arrays to hold the data from the left and right halves
        int[] leftArray = new int[sizeLeft];
        int[] rightArray = new int[sizeRight];

        // Copy data into the temporary arrays
        for (int i = 0; i < sizeLeft; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < sizeRight; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Initial indexes for the left, right, and merged arrays
        int i = 0;    // Index for leftArray
        int j = 0;    // Index for rightArray
        int k = left; // Index for the main array

        // Compare elements from the temp arrays and copy the smaller one back into the main array
        while (i < sizeLeft && j < sizeRight) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // If the left array still has leftover elements, copy them over
        // (This happens if all elements in the right array were smaller and already placed)
        while (i < sizeLeft) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // If the right array still has leftover elements, copy them over
        while (j < sizeRight) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}

