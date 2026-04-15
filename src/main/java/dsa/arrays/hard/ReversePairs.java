package dsa.arrays.hard;

public class ReversePairs {
    public int MergeSort(int[] array) {
        int[] temp = new int[array.length];
        return countMergeSort(array, temp, 0, array.length-1);
    }

    public int countMergeSort(int[] array, int[] temp, int low, int high) {
        int count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;

            count += countMergeSort(array, temp, low, mid);
            count += countMergeSort(array, temp, mid + 1, high);
            count += Merge(array, temp, low, mid, high);
        }
        return count;
    }

    public int Merge(int[] array, int[] temp, int low, int mid, int high) {
        System.arraycopy(array, low, temp, low, high - low + 1);
        int leftPnt = low;
        int rightPnt = mid + 1;
        int arrayPnt = low;
        //@param low already acts like left pointer
        //initializing the right pointer
        int j = mid + 1;
        int count = 0;
        //the logic to find out the Reverse pairs with current two sub arrays.
        for (int i = low; i <= mid; i++)
            while (j <= high && temp[i] > 2L * temp[j]) {
                j++;
                count++;
            }

        while (leftPnt <= mid && rightPnt <= high) {
            if (temp[leftPnt] < temp[rightPnt]) {
                array[arrayPnt++] = temp[leftPnt++];
            } else {
                array[arrayPnt++] = temp[rightPnt++];
            }
        }

        int leftovers = mid - leftPnt + 1;
        if (leftovers > 0) System.arraycopy(temp, leftPnt, array, arrayPnt, leftovers);
        return count;
    }
}
