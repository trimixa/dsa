package com.arrays.hard;

public class CountInversions {
    public long numberOfInversions(int[] nums) {
        //THE BEST WAY TO COUNT INVERSIONS IS TO USE MERGE SORT AND KEEP TRACK OF THE INVERSIONS.
        //WE THROW IN THE NUMS ARRAY IN A MODIFIED MERGE SORT.
        return mergeSort(nums, 0, nums.length);
    }


    private void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int leftArraySize = mid - left + 1;
        int rightArraySize = right - mid;
        int[] arrayLeft = new int[leftArraySize];
        int[] arrayRight = new int[rightArraySize];
        for (int i = 0; i < leftArraySize; i++) arrayLeft[i] = nums[left + i];
        for (int i = 0; i < rightArraySize; i++) arrayRight[i] = nums[mid + i + 1];
        int leftCounter = 0, rightCounter = 0, arrayCounter = left;
        while (leftCounter < leftArraySize && rightCounter < rightArraySize) {
            if (arrayLeft[leftCounter] <= arrayRight[rightCounter]) nums[arrayCounter++] = arrayLeft[leftCounter++];
            else nums[arrayCounter++] = arrayRight[rightCounter++];
        }
        while (leftCounter < leftArraySize) nums[arrayCounter++] = arrayLeft[leftCounter++];
        while (rightCounter < rightArraySize) nums[arrayCounter++] = arrayRight[rightCounter++];
    }
}
