import java.util.Arrays;
public class MergeSort {
    //mergeSort class this take in an array and the first and last index number of those array
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) { //this is the base case if the left is bigger the the right meaning that it only have 1 number so no need to split it anymore
            int mid = left + (right - left) / 2; //calculate the middle for the split 

            mergeSort(array, left, mid); // then use this to recursive another one over and over till it reach the base case
            mergeSort(array, mid + 1, right); // same thing but for the right half

            merge(array, left, mid, right); // another class merge that combines both sorted halves back together
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1; // size of the left half
        int n2 = right - mid; // size of the right half

        int[] leftArr = new int[n1]; // temp array to hold the left half
        int[] rightArr = new int[n2]; // temp array to hold the right half

        for (int i = 0; i < n1; i++) leftArr[i] = array[left + i]; // copy left half into leftArr
        for (int j = 0; j < n2; j++) rightArr[j] = array[mid + 1 + j]; // copy right half into rightArr

        int i = 0, j = 0, k = left; // i for leftArr, j for rightArr, k for the main array

        while (i < n1 && j < n2) { // compare both halves and put the smaller one back into array
            array[k++] = (leftArr[i] <= rightArr[j]) ? leftArr[i++] : rightArr[j++];
        }
        while (i < n1) array[k++] = leftArr[i++]; // if leftArr still has leftovers, add them
        while (j < n2) array[k++] = rightArr[j++]; // if rightArr still has leftovers, add them
    }


// This is just a test you can delete it 
    public static void main(String[] args) {
        int[] testArr = {9,2,6,13,8,41,248,64};
        mergeSort(testArr, 0, 7);
        System.out.print(Arrays.toString(testArr)); //convert array to string for readabilty
    }
}