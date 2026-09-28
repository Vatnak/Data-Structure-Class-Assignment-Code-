package sorts;
import utils.SortedResult;

public class SelectionSort {
    public static SortedResult sorting(int[] arr) {
        SortedResult result = new SortedResult();
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            // store the very first number index as the minimum
            int min = i;

            // start the searching for number smaller than the minimum (start after index zero)
            for (int j = i + 1; j < arr.length; j++) {
                // counting the comparison
                result.comparison++;

                // if there is a number smaller than the minimum we assign that value as the minimum instead
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            // once the inner loop end we swap the value place on the left with the number that is the minimum
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                result.swap++;
            }

        }
        // stop the run time recording
        long endTime = System.nanoTime();
        result.runtimeNano = endTime - startTime;
        // return the result to SortedResult.java
        return result;
    }
}