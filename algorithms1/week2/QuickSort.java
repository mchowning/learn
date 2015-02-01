import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("WrongPackageStatement")
public class QuickSort {

    private static int numComparisons;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("qs_input.txt"), StandardCharsets.UTF_8);
        String[] strLines = lines.toArray(new String[lines.size()]);
        int[] intArray = new int[strLines.length];
        for (int i = 0; i < strLines.length; i++) {
            intArray[i] = Integer.parseInt(strLines[i]);
        }

        numComparisons = 0;
        partitionArray(intArray, 0, intArray.length-1);
        System.out.println(Arrays.toString(intArray));
        System.out.println("Number of comparisons: " + numComparisons);
    }

    private static void partitionArray(int[] inputArray, int min, int max) {
        partitionArray(inputArray, min+1, min+1, min, max);
    }

    private static void partitionArray(int[] inputArray, int partitionIndex, int endIndex, int min, int max)
    {
        // if only one value to be considered, nothing to sort
        if (max - min > 0) {

//            System.out.println(Arrays.toString(inputArray));
//            System.out.println("min = " + min + ", max = " + max);
            
            // Use Last element
            // swap last and first elements in sub-array
//            swapElements(inputArray, min, max);

            // Use median of first, middle, last elements in sub-array
            putFistMidLastMedianFirst(inputArray, min, max);

            
            int pivot = inputArray[min];
            while (endIndex <= max) {
                numComparisons++;
                if (inputArray[endIndex] < pivot) {
                    swapElements(inputArray, partitionIndex, endIndex);
                    partitionIndex++;
                }
                endIndex++;
            }

            // Swap pivot into position
            swapElements(inputArray, min, partitionIndex-1);

            partitionArray(inputArray, min, partitionIndex-2);
            partitionArray(inputArray, partitionIndex, max);
        }
    }

    private static void putFistMidLastMedianFirst(int[] inputArray, int min, int max) {
        int midIndex = ((max - min) / 2) + min;

        int firstNum = inputArray[min];
        int midNum = inputArray[midIndex];
        int lastNum = inputArray[max];

        int pivotIndex;
        if (isMedian(firstNum, midNum, lastNum)) {
            pivotIndex = min;
        } else if (isMedian(midNum, firstNum, lastNum)) {
            pivotIndex = midIndex;
        } else {
            pivotIndex = max;
        }
//            System.out.println("index: " + pivotIndex + ", value: " + inputArray[pivotIndex]);
        swapElements(inputArray, min, pivotIndex);
    }

    private static boolean isMedian(int potentialMedian, int otherNum1, int otherNum2) {
        return (otherNum1 < potentialMedian && potentialMedian < otherNum2) || (otherNum2 < potentialMedian && potentialMedian < otherNum1);
    }

    private static void swapElements(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
