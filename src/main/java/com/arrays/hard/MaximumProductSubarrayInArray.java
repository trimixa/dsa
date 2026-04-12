package com.arrays.hard;

public class MaximumProductSubarrayInArray {
    //Given an integer array nums. Find the subarray with the largest product, and return the product of the elements present in that subarray.
    //A subarray is a contiguous non-empty sequence of elements within an array.
    //Example 1
    //Input: nums = [4, 5, 3, 7, 1, 2]
    //Output: 840
    //Explanation:
    //The largest product is given by the whole array itself
    //Example 2
    //Input: nums = [-5, 0, -2]
    //Output: 0
    //Explanation:
    //The largest product is achieved with the following subarrays [0], [-5, 0], [0, -2], [-5, 0, -2].
    public int maxProductBrute(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // FIX 3: Initialize to the first element or MIN_VALUE
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            // FIX 1: Reset the product for every new starting position 'i'
            int prod = 1;

            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                // FIX 2: Check the max at EVERY step, not just at the end
                max = Integer.max(max, prod);
            }
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize all trackers with the first element
        int currentMax = nums[0];
        int currentMin = nums[0];
        int globalMax = nums[0];

        // Start looping from the second element
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // If the current number is negative, it will flip our min and max.
            // By swapping them now, the math below remains perfectly consistent!
            if (num < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }

            // Calculate the new Max: Is it better to start a new subarray here,
            // or multiply the current number by our running maximum?
            currentMax = Math.max(num, currentMax * num);

            // Calculate the new Min: Is it worse to start a new subarray here,
            // or multiply the current number by our running minimum?
            currentMin = Math.min(num, currentMin * num);

            // Update our global high score if necessary
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }
}
