package com.sorting;

/**
 * The bubble sort algorithm sorts an array by repeatedly swapping adjacent elements if they are in the wrong order. The largest elements "bubble" to the end of the array with each pass.
 * Approach
 * Run a loop i from n-1 to 0.
 * Run a nested loop from j from 0 to i-1.
 * If arr[j] > arr[j+1], swap them.
 * Continue until the array is sorted.
 * Note: Here, after each iteration, the array becomes sorted up to the last index of the range. That is why the last index of the range decreases by 1 after each iteration. This decrement is managed by the outer loop, where the last index is represented by the variable i. The inner loop (variable j) helps to push the maximum element of the range [0...i] to the last index (i.e., index i).
 */

public class BubbleSort {
    public void BubbleSort(int[] arr) {

    }
}
