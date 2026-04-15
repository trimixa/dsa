package dsa.arrays.hard;

public class CountInversionsPractice {
    private long numberOfInversion(int[] array) {
        long countInversions;
        int[] temp = new int[array.length];
        countInversions = MergeSort(array, temp, 0, array.length-1);
        return countInversions;
    }

    private long MergeSort(int[] array, int[] temp, int low, int high) {
        long countInversions = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            countInversions += MergeSort(array, temp, low, mid);
            countInversions += MergeSort(array, temp, mid + 1, high);
            countInversions += Merge(array, temp, low, mid, high);
        }
        return countInversions;
    }

    private long Merge(int[] array, int[] temp, int low, int mid, int high) {
        System.arraycopy(array, low, temp, low, high - low + 1);

        int leftArrayPointer = low;
        int rightArrayPointer = mid + 1;
        int arrayPointer = low;

        long countInversion = 0;

        while (leftArrayPointer <= mid && rightArrayPointer <= high) {
            if (temp[leftArrayPointer] <= temp[rightArrayPointer]) {
                array[arrayPointer++] = temp[leftArrayPointer++];
            } else {
                array[arrayPointer++] = temp[rightArrayPointer++];
                countInversion += mid - leftArrayPointer + 1;
            }
        }

        int elementsLeft = mid - leftArrayPointer + 1;
        if (elementsLeft > 0) {
            System.arraycopy(temp, leftArrayPointer, array, arrayPointer, elementsLeft);
        }
        return countInversion;
    }
}
