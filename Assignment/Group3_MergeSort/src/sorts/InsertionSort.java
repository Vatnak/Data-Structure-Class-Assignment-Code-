package sorts;
import utils.SortedResult;

public class InsertionSort {
    public static SortedResult sorting(int[] arr) {
        SortedResult result = new SortedResult();
        // record the run time
        long startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;  // j is the left of the array (before the temp)

            // check if there is number bigger than the temp on the left of the array
            while (j >= 0 && arr[j] > temp) {
                // counting the comparison
                result.comparison++;

                // continue swapping them to the right
                arr[j + 1] = arr[j];

                // count the swap
                result.swap++;
                // decrement j so that we keep checking the left of the array with the temp
                j--;
            }
            arr[j + 1] = temp;
        }
        // stop the run time recording
        long endTime = System.nanoTime();
        result.runtimeNano = endTime - startTime;
        // return the result to SortedResult.java
        return result;
    }
}