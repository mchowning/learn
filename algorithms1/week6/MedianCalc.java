package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by matt on 3/2/15.
 */
public class MedianCalc {


    public static void main(String[] args) throws IOException {
        List<Integer> nums = getNumsFromFile(args[0]);
        trackMedians(nums);

//        PriorityQueue<Integer> lows = new PriorityQueue<Integer>(5000, Collections.reverseOrder());
//        for (int i = 0; i < 10; i++) {
//            lows.add(nums.get(i));
//            System.out.println("" + nums.get(i));
//        }
//        System.out.println("From queue");
//        for (int i = 0; i < 10; i++) {
//            System.out.println("" + lows.remove());
//        }
    }

    private static List<Integer> getNumsFromFile(String filename) throws IOException {
        List<Integer> result = new ArrayList<Integer>();

        Path path = Paths.get("/Users/matt/dev/courses/algorithms1/week6/median_testcases/" + filename);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String line;
        while ((line = br.readLine()) != null) {
            result.add(new Integer(line));
        }
        return result;
    }

    private static void trackMedians(List<Integer> nums) {
        PriorityQueue<Integer> lows = new PriorityQueue<Integer>(5000, Collections.reverseOrder());
        PriorityQueue<Integer> highs = new PriorityQueue<Integer>(5000);
        BigInteger medianSum = BigInteger.ZERO;
        for (int n : nums) {
            if (lows.isEmpty()) {
                lows.add(n);
                medianSum = medianSum.add(BigInteger.valueOf(n));
            } else {
                int median = lows.peek();
                if (lows.size() == highs.size()) {
                    if (n < median) {
                        lows.add(n);
                    } else {
                        highs.add(n);
                        lows.add(highs.remove());
                    }
                } else if (lows.size() > highs.size()) {
                    if (n < median) {
                        lows.add(n);
                        highs.add(lows.remove());
                    } else {
                        highs.add(n);
                    }
                } else {
                    throw new RuntimeException("This shouldn't happen");
                }
                medianSum = medianSum.add(BigInteger.valueOf(lows.peek()));
            }
        }
        System.out.println("Median sum: " + medianSum.toString());
        System.out.println("Mod 10000: " + medianSum.mod(BigInteger.valueOf(10000)));
    }
}
