package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by matt on 3/2/15.
 */
public class Summer {

//    got 427 result

    public static void main(String[] args) throws IOException {
        Set<BigInteger> ints = getVerticesFromFile(args[0]);
        Set<Integer> matches = new HashSet<Integer>();
        for (BigInteger i : ints) {
            for (int total = -10000; total <= 10000; total++) {
                BigInteger diff = BigInteger.valueOf(total).subtract(i);
                if (!diff.equals(i) && ints.contains(diff)) {
                    matches.add(total);
                }
            }
        }

        System.out.println(Integer.toString(matches.size()));
    }

    private static Set<BigInteger> getVerticesFromFile(String filename) throws IOException {
        Set<BigInteger> result = new HashSet<BigInteger>();

        Path path = Paths.get("/Users/matt/dev/courses/algorithms1/week6/sum_testcases/" + filename);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String line;
        while ((line = br.readLine()) != null) {
            result.add(new BigInteger(line));
        }
        return result;
    }
}
