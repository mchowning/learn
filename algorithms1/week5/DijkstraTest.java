package week5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by matt on 2/16/15.
 */
public class DijkstraTest {

    private ByteArrayOutputStream outContent;
    private static final String outputMessageFormat = "Shortest path from %s to %s is %s\n";

    @Before
    public void setUpStream() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }

    @Test
    public void length1Path() {
        String goalVertex = "80";
        String[] args = new String[] {"input_data.txt", goalVertex};
        startMain(args);
        String expectedOutput = getExpectedOutput(goalVertex, "982");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testCase1_2() {
        String goalVertex = "2";
        String[] args = new String[] {"test_case_1", goalVertex};
        startMain(args);
        String expectedOutput = getExpectedOutput(goalVertex, "3");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testCase1_3() {
        String goalVertex = "3";
        String[] args = new String[] {"test_case_1", goalVertex};
        startMain(args);
        String expectedOutput = getExpectedOutput(goalVertex, "3");
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testCase1_4() {
        String goalVertex = "4";
        String[] args = new String[] {"test_case_1", goalVertex};
        startMain(args);
        String expectedOutput = getExpectedOutput(goalVertex, "5");
        assertEquals(expectedOutput, outContent.toString());
    }

    private String getExpectedOutput(String goalVertex, String expectedShortestPathLength) {
        return String.format(outputMessageFormat, "1", goalVertex, expectedShortestPathLength);
    }

    private void startMain(String... args) {
        try {
            Dijkstra.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
