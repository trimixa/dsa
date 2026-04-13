package com.arrays.hard;

class ReversePairs {
    //    Given an integer array nums. Return the number of reverse pairs in the array.
    //    An index pair (i, j) is called a reverse pair if:
    //            0 <= i < j < nums.length
    //    nums[i] > 2 * nums[j]
    //    Example 1
    //    Input: nums = [6, 4, 1, 2, 7]
    //    Output: 3
    //    Explanation:
    //    The reverse pairs are:
    //            (0, 2) : nums[0] = 6, nums[2] = 1, 6 > 2 * 1
    //            (0, 3) : nums[0] = 6, nums[3] = 2, 6 > 2 * 2
    //            (1, 2) : nums[1] = 4, nums[2] = 1, 4 > 2 * 1
    //    Example 2
    //    Input: nums = [5, 4, 4, 3, 3]
    //    Output: 0
    //    Explanation:
    //    No pairs satisfy both the conditions.
    long reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSort(nums, temp, 0, nums.length - 1);
    }

    long mergeSort(int[] array, int[] temp, int low, int high) {
        long count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            count += mergeSort(array, temp, low, mid);
            count += mergeSort(array, temp, mid + 1, high);
            count += merge(array, temp, low, mid, high);
        }
        return count;
    }

    long merge(int[] array, int[] temp, int low, int mid, int high) {
        System.arraycopy(array, low, temp, low, high - low + 1);
        //counting Reverse Pairs
        int count = 0;
        int j = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (j <= high && array[i] > (2L * array[j])) j++;
            //the count logic explained:
            //j pointer moves ahead looking for all possible elements that can be paired with array[i].
            //When the while loop ends we will have j index moved to certain distance.
            //the difference in distance between the jth index and mid gives the count of elements suitable.
            //then we add these pairs to count variable. The extra 1 is added for the sake of indexing starting from 0.
            count = count + j - mid - 1;
        }
        //sorting array here
        int arrLeftPnt = low;
        int arrRightPnt = mid + 1;
        int arrPnt = low;

        while (arrLeftPnt <= mid && arrRightPnt <= high) {
            if (temp[arrLeftPnt] <= temp[arrRightPnt]) array[arrPnt++] = temp[arrLeftPnt++];
            else array[arrPnt++] = temp[arrRightPnt++];
        }
        int leftovers = mid - arrLeftPnt + 1;
        if (leftovers > 0) System.arraycopy(temp, arrLeftPnt, array, arrPnt, leftovers);
        return count;
    }
}
