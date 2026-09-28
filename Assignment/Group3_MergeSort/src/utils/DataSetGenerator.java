package utils;
import java.util.Random;

// This is where we generate random numbers to use as dataset for comparing performance
public class DataSetGenerator {
    public static int[] generate(int size, long seed) {
        Random random = new Random(seed);
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1_000_000);
        }
        return arr;
    }
}
