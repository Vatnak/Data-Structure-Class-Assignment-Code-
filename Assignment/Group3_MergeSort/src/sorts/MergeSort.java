package sorts;
import utils.SortedResult;
public class MergeSort {
    public static SortedResult sorting(int[] arr) {
        // this will get the sorted result
        SortedResult result = new SortedResult();
        // record the runtime
        long startTime = System.nanoTime();

        // call the method
        sort(arr, result);

        // end of runtime
        long endTime = System.nanoTime();
        result.runtimeNano = endTime - startTime;
        // return the result to SortedResult.java
        return result;
    }

    private static void sort(int[] arr, SortedResult result) {
        int arrayLength = arr.length;

        // base case
        if (arrayLength <= 1) {
            return;     // return back once the array has only a single value
        }

        // split the array
        int midIndex = arrayLength / 2;
        // left array
        int[] leftArray = new int[midIndex];
        // right array
        int[] rightArray = new int[arrayLength - midIndex];

        int i = 0;  // i is for left array index
        int j = 0;  // j is for right array index

        //populate the left and right array
        for (; i < arrayLength; i++) {
            // add half of the array in leftArray
            if (i < midIndex) {
                leftArray[i] = arr[i];
            }
            // add the other half in rightArray
            else {
                rightArray[j] = arr[i];
                j++;
            }
        }
        // recursively call the method to keep splitting the array in half
        sort(leftArray, result);
        sort(rightArray, result);

        // call a helper method to merge the array back after comparison
        merge(leftArray, rightArray, arr, result);
    }

    // helper method for merging the array back
    private static void merge(int[] leftArray, int[] rightArray, int[] array, SortedResult result) {
        // initialise the size of each left and right array
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;

        int i = 0;      // initial pointer of the "array"
        int left = 0;   // initial index of the "leftArray"
        int right = 0;  // initial index of the "rightArray"

        // start comparing both the left and right array together
        while (left < leftSize && right < rightSize) {
            // count the comparison
            result.comparison++;
            if (leftArray[left] < rightArray[right]) {
                array[i] = leftArray[left];
                left++;
            }
            else {
                array[i] = rightArray[right];
                right++;
            }
            i++;
            // count the swap
            result.swap++;
        }

        // add the remaining left or right array into the "array"
        while (left < leftSize) {   
            array[i] = leftArray[left];
            i++;
            left++;
            // count the swap
            result.swap++;
        }
        while (right < rightSize) {
            array[i] = rightArray[right];
            i++;
            right++;
            // count the swap
            result.swap++;
        }
    }
}