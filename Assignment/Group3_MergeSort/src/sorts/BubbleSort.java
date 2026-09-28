package sorts;
import utils.SortedResult;

public class BubbleSort {
    public static SortedResult sorting(int[] arr) {
        SortedResult result = new SortedResult();
        // record the runtime
        long startTime = System.nanoTime();

        for (int i = 0; i < arr.length - 1; i++) {

            // initialise a boolean to stop the program if the array is already sorted O(n)
            boolean continueSorting = false;

            // inner loop: compare it with arr.length - i - 1 because once the biggest number are sorted at the very right end we don't have to look through it again
            for (int j = 0; j < arr.length - i - 1; j++) {
                // counting the comparison
                result.comparison++;

                // continue swapping the biggest number to the right until it reached the end
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // counting the amount of swap it take to finish
                    result.swap++;

                    continueSorting = true;
                }
            }
            // this condition is only true when the array is already sorted it breaks and stop looking through the array
            if (!continueSorting) {
                break;
            }
        }
        long endTime = System.nanoTime();
        // stop the run time recording
        result.runtimeNano = endTime - startTime;
        // return the result to SortedResult.java
        return result;
    }

}