import sorts.*;
import utils.*;

public class Main {
    public static void main(String[] args) {

        // initial the size of how many sizes we're going to save in the output (for this project only 3 types)
        int[] sizes = {5, 1_000, 1000_000};

        // give a specific seed u like (seed is like a pesudo-random number generator so it means 
        // it will generate a random set of number that is always the same pattern (good for comparing))
        long seed = 5;
        // This part is where the output will be stored in an output folder
        String[] filenames = {"./output/sample_5.txt", "./output/sample_1000.txt", "./output/sample_1000_000.txt"};
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            String filename = filenames[i];
            // call the DataSetGenerator
            int[] DataSet = DataSetGenerator.generate(size, seed);

            // with the dataset given we clone 4 of them so that our sorting algorithm can use 1 each
            int[] sample1 = DataSet.clone();
            int[] sample2 = DataSet.clone();
            int[] sample3 = DataSet.clone();
            int[] sample4 = DataSet.clone();

            // store the sorted output and performance in SortedResult so we can save it in a text file
            SortedResult r1 = MergeSort.sorting(sample1);
            r1.SaveOutputToFile("Merge Sort", filename, sample1, false);

            SortedResult r2 = BubbleSort.sorting((sample2));
            r2.SaveOutputToFile("Bubble Sort", filename, sample2, true);

            SortedResult r3 = SelectionSort.sorting((sample3));
            r3.SaveOutputToFile("Selection Sort", filename, sample3, true);

            SortedResult r4 = InsertionSort.sorting((sample4));
            r4.SaveOutputToFile("Insertion Sort", filename, sample4, true);
        }

    }
}