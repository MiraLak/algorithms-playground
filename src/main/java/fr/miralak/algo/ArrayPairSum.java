package fr.miralak.algo;

/**
 * Given an integer array, output all pairs that sum up to a specific value k.
 */
public class ArrayPairSum {

    public int pairSum(int[] myArray, int sum) {
        int numberOfPairs = 0;
        if (myArray.length > 2) {
            int[] sortedArray = ascSortTable(myArray);
            int left = 0;
            int right = sortedArray.length - 1;
            while (left < right) {
                int currentSum = sortedArray[left] + sortedArray[right];
                if (currentSum == sum) {
                    numberOfPairs++;
                    System.out.println("{" + sortedArray[left] + "," + sortedArray[right] + "}");
                    left++;
                } else if (currentSum < sum) {
                    left++;
                } else {
                    right--;
                }
            }
        } else {
            System.out.println("array doesnt contain a pair");
        }
        return numberOfPairs;
    }

    /**
     * Sorting table of int in ascendant order.
     * @return sorted table
     */
    public int[] ascSortTable(int[] myArray) {
        if (myArray.length > 1) {
            quickSort(myArray, 0, myArray.length - 1);
        }
        return myArray;
    }

    /**
     * Quick sort algorithm.
     * @param myArray
     */
    private void quickSort(int[] myArray, int start, int end) {
        if (start < end) {
            int i = partition(myArray, start, end);
            quickSort(myArray, start, i - 1);
            quickSort(myArray, i + 1, end);
        }

    }

    private int partition(int[] array, int minIndex, int maxIndex) {
        int pivot = array[maxIndex];
        int i = minIndex;
        for (int j = minIndex; j <= maxIndex; j++) {
            if (array[j] < pivot) {
                swapElements(array, i, j);
                i++;
            }
        }
        swapElements(array, maxIndex, i);

        return i;
    }

    private void swapElements(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
