package week4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SccFinderTest {

    private ByteArrayOutputStream outContent;

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
    public void testcase_35_7111() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_35_7111");
        assertEquals("35,7,1,1,1", outContent.toString());
    }

    @Test
    public void testcase_32000() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_32000");
        assertEquals("3,2", outContent.toString());
    }

    @Test
    public void testcase_32221() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_32221");
        assertEquals("3,2,2,2,1", outContent.toString());
    }

    @Test
    public void testcase_33110() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_33110");
        assertEquals("3,3,1,1", outContent.toString());
    }

    @Test
    public void testcase_33200() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_33200");
        assertEquals("3,3,2", outContent.toString());
    }

    @Test
    public void testcase_33300() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_33300");
        assertEquals("3,3,3", outContent.toString());
    }

    @Test
    public void testcase_61100() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_61100");
        assertEquals("6,1,1", outContent.toString());
    }

    @Test
    public void testcase_63210() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_63210");
        assertEquals("6,3,2,1", outContent.toString());
    }

    @Test
    public void testcase_71000() {
        System.setOut(new PrintStream(outContent));
        startMain("testcase_71000");
        assertEquals("7,1", outContent.toString());
    }

    @Test
    public void problem_test_input() {
        System.setOut(new PrintStream(outContent));
        startMain("problem_test_input");
        assertEquals("434821,968,459,313,211", outContent.toString());
    }

    private void startMain(String filename) {
        try {
            SccFinder.main(new String[]{filename});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}