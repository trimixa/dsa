package dsa.arrays.hard;

public class CountInversions {
    long numberOfInversions(int[] array) {
        int[] temp = new int[array.length];
        return mergeSort(array, temp, 0, array.length - 1);
    }


    long mergeSort(int[] nums, int[] temp, int low, int high) {
        long inversions = 0;                                                    //Inversions Counter
        if (low < high) {                                                       //Base Case
            int mid = low + (high - low) / 2;                                   //Calculating Mid-Value
            inversions += mergeSort(nums, temp, low, mid);                      //Left SubArray Division
            inversions += mergeSort(nums, temp, mid + 1, high);            //Right SubArray Division
            inversions += merge(nums, temp, low, mid, high);                    //Merging Back Left & Right SubArrays
        }
        return inversions;                                                      //Returning sum of all inversions.
    }

    long merge(int[] array, int[] temp, int low, int mid, int high) {
        System.arraycopy(array, low, temp, low, high - low + 1);        //Copy the current sub array into temp array
        long inversions = 0;                                                    //Inversions Counter
        int left = low;                                                         //Left Subarray Pointer
        int right = mid + 1;                                                    //Right Subarray Pointer
        int k = low;                                                            //Array Pointer

        //sorting elements
        while (left <= mid && right <= high) {                                  //Running unless one of the sub array exhausts
            if (temp[left] <= temp[right])
                array[k++] = temp[left++];                                      //if left subarray element is smaller, then copy it to main array
            else {
                array[k++] = temp[right++];                                     //if right subarray element is smaller, then copy it to main array
                inversions += mid - left + 1;                                   //inversion happens when left sorted array have elements greater than right sorted array
            }                                                                   //since the current element is greater, then rest of left sub array will form inversions
        }

        int leftovers = mid - left + 1;                                         //we find if there are any elements remaining in left subarray to be included in main array
        if (leftovers > 0) System.arraycopy(temp, left, array, k, leftovers);   //if leftovers are there we copy them in main array
        return inversions;
    }
}
