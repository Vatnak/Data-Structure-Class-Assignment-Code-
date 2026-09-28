package utils;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

// this file is for storing the runtime, amount of comparison, and amount of swap
public class SortedResult {
    public long comparison;
    public long swap;
    public long runtimeNano;

    // method to save the output performance
    public void SaveOutputToFile(String algorithmName, String filename, int[] sortedArray, boolean append) {    
        try (BufferedWriter output = new BufferedWriter(new FileWriter(filename, append))){
            output.write("=== " + algorithmName + " ===");
            output.newLine();
            output.write("+  Comparisons: " + comparison);
            output.newLine();
            output.write("+  Swaps: " + swap);
            output.newLine();
            output.write("+  Runtime (ns): " + runtimeNano);
            output.newLine();
            output.write("+  Sorted Array: " + Arrays.toString(sortedArray));
            output.newLine();
            output.newLine();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }

    }
}