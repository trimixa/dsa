package dsa.arrays.hard;

public class MergeTwoSortedArraysWithoutExtraSpace {
    //Given two integer arrays nums1 and nums2. Both arrays are sorted in non-decreasing order.
    //merge both the arrays into a single array sorted in non-decreasing order.
    //The final sorted array should be stored inside the array nums1 and it should be done in-place.
    //nums1 has a length of m + n, where the first m elements denote the elements of nums1 and rest are 0s.
    //nums2 has a length of n.
    //Example 1
    //Input: nums1 = [-5, -2, 4, 5], nums2 = [-3, 1, 8]
    //Output: [-5, -3, -2, 1, 4, 5, 8]
    //Explanation:
    //The merged array is: [-5, -3, -2, 1, 4, 5, 8], where [-5, -2, 4, 5] are from nums1 and [-3, 1, 8] are from nums2
    //Example 2
    //Input: nums1 = [0, 2, 7, 8], nums2 = [-7, -3, -1]
    //Output: [-7, -3, -1, 0, 2, 7, 8]
    //Explanation:
    //The merged array is: [-7, -3, -1, 0, 2, 7, 8], where [0, 2, 7, 8] are from nums1 and [-7, -3, -1] are from nums2

    public static void Merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;     //pointer for array @param num1 last element
        int p2 = n - 1;     //pointer for array @param num2 last element
        int p0 = m + n - 1; //pointer for array @param num1 last index
        // since we're merging both the arrays we only need all the elements from num2 into num1
        // hence we run the loop till num2's exhaustion
        while (p2 >= 0) {
            if (p1<0 || nums2[p2] > nums1[p1]) nums1[p0--] = nums2[p2--];
            else nums1[p0--] = nums1[p1--];
        }

    }

}
