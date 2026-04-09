package com.arrays.hard;

public class CountInversions {

    // 1. The Public API
    public long numberOfInversions(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        // Use the optimal single-temp-array trick
        int[] temp = new int[nums.length];
        return mergeSort(nums, temp, 0, nums.length - 1);
    }

    // 2. The Recursive Engine
    private long mergeSort(int[] nums, int[] temp, int left, int right) {
        long inversions = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Accumulate inversions from left, right, and the merge step
            inversions += mergeSort(nums, temp, left, mid);
            inversions += mergeSort(nums, temp, mid + 1, right);
            inversions += merge(nums, temp, left, mid, right);
        }
        return inversions;
    }

    // 3. The Merge & Count Logic
    private long merge(int[] nums, int[] temp, int left, int mid, int right) {
        int lengthToCopy = right - left + 1;
        System.arraycopy(nums, left, temp, left, lengthToCopy);

        int leftPtr = left;
        int rightPtr = mid + 1;
        int arrayCounter = left;

        long inversions = 0; // Local counter for this specific merge

        while (leftPtr <= mid && rightPtr <= right) {
            if (temp[leftPtr] <= temp[rightPtr]) {
                nums[arrayCounter++] = temp[leftPtr++];
            } else {
                nums[arrayCounter++] = temp[rightPtr++];

                // THE MAGIC MATH:
                // All remaining elements in the left half are greater than temp[rightPtr]
                // Number of remaining elements = (mid - leftPtr + 1)
                inversions += (mid - leftPtr + 1);
            }
        }

        // Copy leftovers
        int elementsLeft = mid - leftPtr + 1;
        if (elementsLeft > 0) {
            System.arraycopy(temp, leftPtr, nums, arrayCounter, elementsLeft);
        }

        return inversions;
    }
}